package com.zuicoding.platform.blog.service;

import com.zuicoding.platform.blog.modal.WpPost;

import java.util.List;

/**
 * Created by Stephen.lin on 2017/7/17.
 * <p>
 * Description :<p></p>
 */
public interface IIndexService {

    public List<WpPost> selectPosts();
}
