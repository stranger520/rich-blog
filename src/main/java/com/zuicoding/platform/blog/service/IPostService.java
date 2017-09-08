package com.zuicoding.platform.blog.service;

import com.zuicoding.platform.blog.base.Pager;
import com.zuicoding.platform.blog.modal.WpPost;
import com.zuicoding.platform.blog.modal.WpPostWithBLOBs;
import com.zuicoding.platform.blog.modal.WpTerm;

import java.util.List;

/**
 * Created by Stephen.lin on 2017/8/4.
 * <p>
 * Description :<p></p>
 */
public interface IPostService {

    /**
     * 本周热榜
     * @return
     */
    List<WpPost> selectWeekHostPostList(Pager pager);

    /**
     * 查询 文章详情
     * @param id
     * @return
     */
    WpPostWithBLOBs selectPost(int id);

    long createOrUpdate(WpPostWithBLOBs post,List<WpTerm> terms);

    List<WpPost> selectPostByPager(Pager pager,WpPost post);
}
