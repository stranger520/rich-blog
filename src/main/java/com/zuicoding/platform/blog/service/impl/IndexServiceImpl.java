package com.zuicoding.platform.blog.service.impl;

import com.zuicoding.platform.blog.dao.WpPostMapper;
import com.zuicoding.platform.blog.modal.WpPost;
import com.zuicoding.platform.blog.service.IIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Stephen.lin on 2017/7/17.
 * <p>
 * Description :<p>首页Service</p>
 */
@Service
public class IndexServiceImpl implements IIndexService {

    @Autowired
    private WpPostMapper wpPostMapper;
    @Override
    public List<WpPost> selectPosts() {
        return wpPostMapper.selectAll();
    }
}
