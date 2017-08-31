package com.zuicoding.platform.blog.controller.admin;

import com.zuicoding.platform.blog.base.Pager;
import com.zuicoding.platform.blog.base.PostEnum;
import com.zuicoding.platform.blog.base.ResponseResult;
import com.zuicoding.platform.blog.modal.WpPost;
import com.zuicoding.platform.blog.modal.WpPostWithBLOBs;
import com.zuicoding.platform.blog.modal.WpUser;
import com.zuicoding.platform.blog.service.IPostService;
import com.zuicoding.platform.blog.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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
    @ResponseBody
    public ResponseResult save(WpPostWithBLOBs post, HttpSession session){
        ResponseResult<Long> result = ResponseResult.success();
        try {
           WpUser user= (WpUser) session.getAttribute("user");
           post.setPostAuthor(user.getId());
            long id = postService.createOrUpdate(post);
            result.setData(id).setSuccess(true);
        }catch (Exception e){
            log.e("保存文章失败",e);
            result.setSuccess(false).setCode(-500);
        }
        return result;
    }
    @RequestMapping(value = "/list.html",method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("postStatuses", PostEnum.values());
        return "admin/article/article_manage";
    }
    @RequestMapping(value = "/list.html",method = RequestMethod.POST)
    public String list(Pager pager, WpPost post, Model model){

        try {
            model.addAttribute("articles",postService.selectPostByPager(pager,post));
        }catch (Exception e){
            log.e("查询文章列表error",e);
        }
        return "admin/article/article_list";
    }

}
