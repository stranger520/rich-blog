package com.zuicoding.platform.blog.service.impl;

import com.zuicoding.platform.blog.dao.WpCommentMapper;
import com.zuicoding.platform.blog.service.IPostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Stephen.lin on 2017/8/4.
 * <p>
 * Description :<p></p>
 */
@Service
public class PostCommentServiceImpl implements IPostCommentService {

    @Autowired
    private WpCommentMapper wpCommentMapper;



}
