package com.zuicoding.platform.blog.service;

import com.zuicoding.platform.blog.modal.WpUser;

/**
 * Created by Stephen.lin on 2017/8/4.
 * <p>
 * Description :<p>用户service</p>
 */
public interface IUserService {

    WpUser login(WpUser user);
}
