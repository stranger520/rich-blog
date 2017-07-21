package com.zuicoding.platform.blog.core;

import com.zuicoding.platform.blog.utils.LogUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Created by Stephen.lin on 2017/7/14.
 * <p>
 * Description :<p>spring-mybatis配置 相当于 spring-mybatis.xml整合配置文件</p>
 *  <code>
 * &lt;bean class="org.mybatis.spring.mapper.MapperScannerConfigurer"></code>
 *  <code> &nbsp;&lt;property name="basePackage" value="com.zuicoding.platform.blog.dao"/></code>
 * <code>&lt;/bean>
 * </code>
 */

//@ImportResource(locations = "classpath:mybatis-config.xml")
//@MapperScan("com.zuicoding.platform.blog.dao")
@Configuration
public class SpringMybatisConfig {

    private LogUtil log = LogUtil.newLogUtil(SpringMybatisConfig.class);
    //@SuppressWarnings("SpringJavaAutowiringInspection")//加这个注解让IDE 不报: Could not autowire
    @Autowired
    private DataSource dataSource;


    /**
     * <code>
     * &lt;bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
     *      &lt;property name="dataSource" ref="dataSource" />
     *      &lt;property name="configLocation" value="classpath:mybatis-config.xml" />
     *      &lt;!-- 若无上条就需要有该配置 -->
     *       &lt;property name="mapperLocations" value="classpath:mapper/*.xml"/>
     * &lt;/bean>
     * </code>
     * @return
     */
    @Bean("sqlSessionFactory")
    public SqlSessionFactory createSqlSessionFactory(){
        try {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);
            //配置文件路径解析器
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));


            sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
            return sqlSessionFactoryBean.getObject();
        }catch (Exception e){

            throw new RuntimeException(e);
        }

    }

    /**
     *  <code>
     * &lt;bean class="org.mybatis.spring.mapper.MapperScannerConfigurer"></code>
     *  <code>&nbsp;&lt;property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/></code>
     *  <code> &nbsp;&lt;property name="basePackage" value="com.zuicoding.platform.blog.dao"/></code>
     * <code>&lt;/bean>
     * </code>
     * 如果 创建MapperScannerConfigurer 和 sqlSessionFactory 在同一个Configuration下 ,
     * 则创建MapperScannerConfigurer必须是静态的，否则 放在另一个 配置类里面，可以自行调试。
     * @since https://stackoverflow.com/questions/24643426/java-config-bean-not-autowired-in-other-configuration-class
     * @return
     */
    @Bean
    public static MapperScannerConfigurer createMapperScannerConfigurer(){
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        configurer.setBasePackage("com.zuicoding.platform.blog.dao");
        return configurer;
    }



}
