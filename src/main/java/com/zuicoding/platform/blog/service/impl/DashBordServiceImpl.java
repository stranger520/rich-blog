package com.zuicoding.platform.blog.service.impl;

import com.zuicoding.platform.blog.base.Pager;
import com.zuicoding.platform.blog.dao.WpCommentMapper;
import com.zuicoding.platform.blog.dao.WpPostMapper;
import com.zuicoding.platform.blog.modal.DashBord;
import com.zuicoding.platform.blog.service.IDashBordService;
import com.zuicoding.platform.blog.utils.LogUtil;
import com.zuicoding.platform.blog.utils.ThreadPools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Stephen.lin on 2017/8/4.
 * <p>
 * Description :<p></p>
 */
@Service
public class DashBordServiceImpl implements IDashBordService {

    private static final LogUtil LOG = LogUtil.newLogUtil(DashBordServiceImpl.class);

    @Autowired
    private WpCommentMapper wpCommentMapper;

    @Autowired
    private WpPostMapper wpPostMapper;

    /**
     * 首页数据
     *
     * @return
     */
    @Override
    public DashBord homeData(final int userId) {
        final  DashBord dashBord = new DashBord();
        final CountDownLatch latch = new CountDownLatch(5);

        ThreadPools.submit(new Runnable(){
            @Override
            public void run() {
                try {
                    dashBord.setPostCount(wpPostMapper.selectPostCount(userId));
                }catch (Exception e){
                    LOG.e("query post count error",e);
                }finally {
                    latch.countDown();
                }

            }
        });

        ThreadPools.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    dashBord.setCommentCount(wpCommentMapper.selectCountByUserId(userId));
                }catch (Exception e){
                    LOG.e("query comment count error",e);
                }finally {
                    latch.countDown();
                }
            }
        });

        ThreadPools.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    dashBord.setAppCommentCount(wpCommentMapper.selectAppCountByUserId(userId));
                }catch (Exception e){
                    LOG.e("select app comment count error",e);
                }finally {
                    latch.countDown();
                }
            }
        });

        ThreadPools.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    dashBord.setComments(wpCommentMapper.selectWithPager(
                            new Pager(1,5),userId,null));
                }catch (Exception e){
                    LOG.e("query comment with pager error",e);
                }finally {
                    latch.countDown();
                }
            }
        });

        ThreadPools.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    dashBord.setPosts(wpPostMapper.selectListWithPage(new Pager(1),null,userId));
                }catch (Exception e){
                    LOG.e("query post with pager error",e);
                }finally {
                    latch.countDown();
                }
            }
        });


        try {
            latch.wait();
        }catch (Exception e){
            LOG.e("latch wait error",e);
        }
        LOG.i("build dashbord success...");
        return dashBord;
    }
}
