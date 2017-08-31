package com.zuicoding.platform.blog.core.plugin.dialect.impl;

import com.zuicoding.platform.blog.core.plugin.dialect.IDialect;

/**
 * Created by Stephen.lin on 2017/8/31.
 * <p>
 * Description :<p></p>
 */
@Deprecated
public class MysqlDialect implements IDialect {
    @Override
    public String buildPaginationSql(String sql, int offset, int limit) {
        StringBuilder limitSql = new StringBuilder(sql);
        limitSql.append("   LIMIT   ").append(offset).append(",").append(limit);
        return limitSql.toString();
    }

    @Override
    public String buildCountSql(String sql) {
        return null;
    }
}
