package com.zuicoding.platform.blog.controller.admin;

import com.zuicoding.platform.blog.modal.WpPost;
import com.zuicoding.platform.blog.modal.WpPostWithBLOBs;
import com.zuicoding.platform.blog.service.IPostService;
import com.zuicoding.platform.blog.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Stephen.lin on 2017/8/30.
 * <p>
 * Description :<p>文章管理</p>
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    private LogUtil log = LogUtil.newLogUtil(ArticleController.class);

    @Autowired
    private IPostService postService;


    @RequestMapping(value = "/index.html",method = RequestMethod.GET)
    public String index(){

        return "admin/article/article_write";
    }

    @RequestMapping(value = "/create.json",method = RequestMethod.POST)
    public void save(WpPostWithBLOBs post){
        try {
            postService.createOrUpdate(post);
        }catch (Exception e){
            log.e("保存文章失败",e);
        }
    }

}
