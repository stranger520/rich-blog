package com.zuicoding.platform.blog.core.plugin;

import com.zuicoding.platform.blog.base.Pager;
import com.zuicoding.platform.blog.core.plugin.dialect.IDialect;
import com.zuicoding.platform.blog.utils.LogUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * Created by Stephen.lin on 2017/8/31.
 * <p>
 * Description :<p>mybatis 分页插件</p>
 */
@Intercepts({
        @Signature(type = Executor.class,method = "query", args = { MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class })
})
public class PageHelper implements Interceptor {

    private LogUtil log = LogUtil.newLogUtil(PageHelper.class);

    public static final ThreadLocal<Pager> localPage = new ThreadLocal<Pager>();

    private IDialect dialect;
    private static final List<ResultMap> COUNT_RESULTMAPS = new ArrayList<>(1);

    private static final String COUNT_SQL_SUFFIX = "-SELECT-COUNT";

    /**
     * 以此方法名字结尾的查询
     */
    private String methodSuffix = "ByPager";

    public static void pageStart(int pageNum,int pageSize){
        if (pageNum <=0) pageNum = 1;
        localPage.set(new Pager(pageNum,pageSize));
    }
    public static void pageStart(Pager pager){
        if (pager == null) pager = new Pager();
        localPage.set(pager);
    }

    public static void pageStart(){
        pageStart(null);
    }

    public static Pager pageEnd(){
        Pager pager = localPage.get();
        localPage.remove();
        return pager;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
        log.i("sql Command Type:{}",ms.getSqlCommandType());

        if (ms.getSqlCommandType() != SqlCommandType.SELECT) return invocation.proceed();
        if (log.isDebugEnabled()){
            log.d("mappedStatement id : {}",ms.getId());
        }
        if (!ms.getId().endsWith(methodSuffix)) return invocation.proceed();

        Pager pager = localPage.get();
        if(pager == null){
            log.w("page message is null");
            return invocation.proceed();
        }

        Object parameter = invocation.getArgs()[1];
        BoundSql originSql = ms.getBoundSql(parameter);
        if (log.isDebugEnabled()){
            log.d("origin sql is:{}",originSql.getSql());
        }

        String countSql = this.dialect.buildCountSql(originSql.getSql());
        if (log.isDebugEnabled()){
            log.d("count sql is : {}",countSql);
        }
        //有些查询不需要查询总数
        if (countSql != null && !"".equals(countSql.trim())){

            if (COUNT_RESULTMAPS.size() == 0){
                COUNT_RESULTMAPS.add(new ResultMap.Builder(ms.getConfiguration(),
                        ms.getId()+COUNT_SQL_SUFFIX,Integer.class,
                        Collections.<ResultMapping>emptyList()).build());
            }

            //构建 count bound sql
            BoundSql countBoundSql = new BoundSql(ms.getConfiguration(),
                    countSql,originSql.getParameterMappings(),
                    originSql.getParameterObject());

            Executor executor = (Executor) invocation.getTarget();
            //构建 count MappedStatement
            MappedStatement countMs = reBuildMappedStatement(ms,countBoundSql,true);
            List<Integer> list = executor.query(countMs,parameter,RowBounds.DEFAULT,null);
            if (list!=null && !list.isEmpty()){
                int total = list.get(0);
                pager.setTotal(total);
            }

        }

        String limitSql = this.dialect.buildPaginationSql(originSql.getSql(),pager.getOffset(),pager.getPageSize());
        if(log.isDebugEnabled()){
            log.d("limit sql is : {}",limitSql);
        }
        BoundSql limitBoundSql = new BoundSql(ms.getConfiguration(),
                limitSql,originSql.getParameterMappings(),originSql.getParameterObject());

        MappedStatement newMs = reBuildMappedStatement(ms,limitBoundSql,false);

        invocation.getArgs()[0] = newMs;
        log.i(" reBuild limit sql finished...");
        return invocation.proceed();
    }

    /**
     * 构造新的MappedStatement 对象
     * @param ms
     * @param newBoundSql
     * @param isCount
     * @return
     */
    private MappedStatement reBuildMappedStatement(MappedStatement ms,
                                                   BoundSql newBoundSql,boolean isCount){
        String id = ms.getId();
        if (isCount){
            id += COUNT_SQL_SUFFIX;
        }
        if (log.isDebugEnabled()){
            log.d("sql id is:{}",id);
        }
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(),
                id,
                new StaticSqlSource(ms.getConfiguration(),
                        newBoundSql.getSql(),newBoundSql.getParameterMappings()), ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null){
            builder.keyProperty(StringUtils.join(ms.getKeyProperties(),","));
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());

        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        builder.resultMaps(ms.getResultMaps());
        //如果是构建 count mappedStatement,将 查询 count result map 设置进去
        if (isCount){
            builder.resultMaps(COUNT_RESULTMAPS);

        }

        return builder.build();
    }


    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor){
           return Plugin.wrap(target,this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
        if (!properties.containsKey("dialect")){
            throw new IllegalArgumentException("dialect property is required");
        }
        this.methodSuffix = properties.getProperty("methodSuffix",this.methodSuffix);
        String dialect = properties.getProperty("dialect");

        IDialect.DefaultDialect defaultDialect = IDialect.DefaultDialect.valueOf(dialect.toUpperCase());

        if (defaultDialect == null) throw new IllegalArgumentException("Undefined dialect " + dialect);
        this.dialect = defaultDialect;
        log.i(" init properties finished, dialect value is : {},methodSuffix",dialect,methodSuffix);
    }

    public IDialect getDialect() {
        return dialect;
    }

    public void setDialect(IDialect dialect) {
        this.dialect = dialect;
    }
}
