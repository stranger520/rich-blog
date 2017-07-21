package com.zuicoding.platform.blog.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Stephen.lin on 2017/7/14.
 * <p>
 * Description :<p></p>
 */
public final class LogUtil {

    private Logger logger;

    private  LogUtil(Class clazz){
        logger = LoggerFactory.getLogger(clazz);
    }
    private  LogUtil(String name){
        logger = LoggerFactory.getLogger(name);
    }

    public static LogUtil newLogUtil(Class clazz){

        return new LogUtil(clazz);
    }

    public static LogUtil newLogUtil(String name){
        return new LogUtil(name);
    }

    public boolean isDebugEnabled(){

        return logger.isDebugEnabled();
    }

    public boolean isInfoEnabled(){
        return logger.isInfoEnabled();
    }

    public boolean isWarnEnabled(){

        return logger.isWarnEnabled();
    }

    public void d(String msg){

        if (logger.isDebugEnabled()){
            logger.debug(msg);
        }
    }

    public void d(String msg,Object... args){
        if (logger.isDebugEnabled()){
            logger.debug(msg,args);
        }
    }

    public void d(String msg,Throwable thr){
        if (logger.isDebugEnabled()){
            logger.debug(msg,thr);

        }
    }

    public void i(String msg){
        logger.info(msg);
    }

    public void i(String msg ,Object... args){
        logger.info(msg,args);
    }

    public void i(String msg ,Throwable thr){
        logger.info(msg, thr);
    }

    public void w(String msg){
        logger.warn(msg);
    }
    public void w(String msg,Object... args){
        logger.warn(msg, args);
    }

    public void w(String msg,Throwable thr){
        logger.warn(msg, thr);
    }

    public void e(String msg){
        logger.error(msg);
    }

    public void e(String msg,Object... args){
        logger.error(msg, args);
    }

    public void e(String msg,Throwable thr){
        logger.error(msg, thr);
    }
}
