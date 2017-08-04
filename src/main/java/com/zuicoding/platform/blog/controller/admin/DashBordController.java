package com.zuicoding.platform.blog.controller.admin;

import com.zuicoding.platform.blog.modal.WpUser;
import com.zuicoding.platform.blog.service.IDashBordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by Stephen.lin on 2017/8/4.
 * <p>
 * Description :<p></p>
 */
@Controller
@RequestMapping("/dashbord")
public class DashBordController {

    @Autowired
    private IDashBordService dashBordService;

    @RequestMapping("/index.html")
    public String index(HttpSession session,
                        Model model){
        WpUser user = (WpUser)session.getAttribute("user");
        model.addAttribute("dashbord",
                dashBordService.homeData(Long.valueOf(user.getId()).intValue()));
        return "admin/dashbord/index";
    }
}
