package com.zuicoding.platform.blog.utils;

import java.util.concurrent.*;

/**
 * Created by Stephen.lin on 2017/8/4.
 * <p>
 * Description :<p>线程池工具类</p>
 */
public class ThreadPools {

    private static LogUtil log = LogUtil.newLogUtil(ThreadPools.class);
    private static ExecutorService executor;

    static {
        try {

            executor = Executors.newFixedThreadPool(5);
        }catch (Exception e){
            log.e("线程池初始化失败!",e);
        }
    }

    private ThreadPools(){}

    public static void submit(Runnable task){
        executor.submit(task);

    }

    public static <T> Future<T> submit(Callable<T> task){

        return executor.submit(task);
    }


}
