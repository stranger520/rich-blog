package com.zuicoding.platform.blog.service.impl;

import com.zuicoding.platform.blog.base.Pager;
import com.zuicoding.platform.blog.core.UserHolder;
import com.zuicoding.platform.blog.core.plugin.PageHelper;
import com.zuicoding.platform.blog.dao.WpPostMapper;
import com.zuicoding.platform.blog.modal.WpPost;
import com.zuicoding.platform.blog.modal.WpPostWithBLOBs;
import com.zuicoding.platform.blog.modal.WpUser;
import com.zuicoding.platform.blog.service.IPostService;
import com.zuicoding.platform.blog.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Stephen.lin on 2017/8/4.
 * <p>
 * Description :<p></p>
 */
@Service
public class PostServiceImpl implements IPostService {

    private LogUtil log = LogUtil.newLogUtil(PostServiceImpl.class);

    @Autowired
    private WpPostMapper wpPostMapper;
    /**
     * 本周热榜
     *
     * @param pager
     * @return
     */
    @Override
    public List<WpPost> selectWeekHostPostList(Pager pager) {
        Date current = new Date();
        Integer count = wpPostMapper.selectCountWithPage(current);
        if (count == null && count == 0) {
            log.w("can't find posts");
            return null;
        }

        List<WpPost> list = wpPostMapper.selectListWithPage(pager,current,null);
        pager.setTotal(count);

        return list;
    }

    @Transactional
    public long createOrUpdate(WpPostWithBLOBs post){
        post.setPostModified(new Date());
        if (post.getId() == null || post.getId() == 0){
            wpPostMapper.insertSelective(post) ;
            return post.getId();
        }
        wpPostMapper.updateByPrimaryKeySelective( post);
        return post.getId();
    }

    @Override
    public WpPostWithBLOBs selectPost(int id) {
        return null;
    }

    public List<WpPost> selectPostByPager(Pager pager,WpPost post){
        PageHelper.pageStart(pager);
        List<WpPost> list =  wpPostMapper.selectListByPager(post);
        PageHelper.pageEnd();
        return list;
    }
}
