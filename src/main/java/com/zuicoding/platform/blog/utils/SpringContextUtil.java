package com.zuicoding.platform.blog.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by Stephen.lin on 2017/7/17.
 * <p>
 * Description :<p></p>
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private SpringContextUtil(){}

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static Object getBean(String name){
       return applicationContext.getBean(name);
    }

    public static <T> Object getBean(Class<T> clazz){

        return applicationContext.getBean(clazz);
    }

    public static void main(String[] args) {

    }
}
