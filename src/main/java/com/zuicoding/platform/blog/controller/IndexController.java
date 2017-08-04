package com.zuicoding.platform.blog.controller;

import com.zuicoding.platform.blog.base.Pager;
import com.zuicoding.platform.blog.service.IPostService;
import com.zuicoding.platform.blog.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Stephen.lin on 2017/7/13.
 * <p>
 * Description :<p></p>
 */
@Controller
public class IndexController {

    private LogUtil log = LogUtil.newLogUtil(IndexController.class);

    @Autowired
    private IPostService postService;


    @RequestMapping("/")
    public String index(){

        return "index/index";
    }

    /**
     * 本周热榜
     *
     * @param pager
     * @param model
     * @return
     */
    @RequestMapping(value = "/week/hot/post/list.html")
    public String currentWeekHostPostList(Pager pager, Model model){
        try {
            model.addAttribute("posts",postService.selectWeekHostPostList(pager));
            model.addAttribute("pager",pager);
        }catch (Exception e){
            log.e("query current week hot post error",e);
        }

        return "index/post_list";
    }
}
