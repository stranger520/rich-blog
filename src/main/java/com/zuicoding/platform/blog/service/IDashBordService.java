package com.zuicoding.platform.blog.service;

import com.zuicoding.platform.blog.modal.DashBord;

/**
 * Created by Stephen.lin on 2017/8/4.
 * <p>
 * Description :<p>仪表板service</p>
 */
public interface IDashBordService  {


    /**
     * 首页数据
     * @return
     */
    DashBord homeData(int userId);
}
