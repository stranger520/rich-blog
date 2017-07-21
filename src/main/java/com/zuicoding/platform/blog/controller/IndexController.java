package com.zuicoding.platform.blog.controller;

import com.zuicoding.platform.blog.service.IIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by <a href="mailto:linyajun@yinker.com">林亚军</a> on 2017/7/13.
 * <p>
 * Description :<p></p>
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IIndexService indexService;

    @RequestMapping("/index.html")
    public String index(Model model){
        model.addAttribute("posts",indexService.selectPosts());
        return "index/index";
    }
}
