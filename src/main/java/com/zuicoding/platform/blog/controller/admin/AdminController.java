package com.zuicoding.platform.blog.controller.admin;

import com.zuicoding.platform.blog.modal.WpUser;
import com.zuicoding.platform.blog.service.IUserService;
import com.zuicoding.platform.blog.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Stephen.lin on 2017/8/4.
 * <p>
 * Description :<p>后台</p>
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private LogUtil log = LogUtil.newLogUtil(AdminController.class);

    @Autowired
    private IUserService userService;

    @RequestMapping(value = {"/",""},method = RequestMethod.GET)
    public String index(){

        return "admin/login";
    }

    @RequestMapping(value = "/login.html",method = RequestMethod.POST)
    public String login(HttpSession session, WpUser user, Model model){
        try {
            user = userService.login(user);
            if (user != null){
                session.setAttribute("user",user);
                return "redirect:/dashbord/index.html";
            }

        }catch (Exception e){
            log.e("用户登录失败!",e);
        }
        model.addAttribute("error","登录失败!");
        return "admin/login";
    }
}
