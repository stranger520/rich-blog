package com.zuicoding.platform.blog.core;

import com.zuicoding.platform.blog.modal.WpUser;

/**
 * Created by Stephen.lin on 2017/8/31.
 * <p>
 * Description :<p></p>
 */
public final class UserHolder {
    private UserHolder(){}
    private static final ThreadLocal<WpUser> USERLOCAL = new ThreadLocal<>();

    public static void set(WpUser user){
        USERLOCAL.set(user);
    }

    public static WpUser get(){
        return USERLOCAL.get();
    }
    public static void clear(){
        USERLOCAL.remove();
    }

}
