package com.zuicoding.platform.blog.core;

import com.zuicoding.platform.blog.modal.WpUser;
import com.zuicoding.platform.blog.utils.LogUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Stephen.lin on 2017/8/5.
 */
@Configuration
public class BlogWebConfig extends WebMvcConfigurerAdapter {

    private LogUtil log =LogUtil.newLogUtil(BlogWebConfig.class);

    /**
     * 静态文件处理
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (log.isDebugEnabled()){
            log.d("config static files");
        }
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request,
                                     HttpServletResponse response,
                                     Object handler) throws Exception {

                HttpSession session = request.getSession(true);
                WpUser user = (WpUser) session.getAttribute("user");
                if (user == null){
                    response.sendRedirect("/admin/");
                    return false;
                }

                return true;
            }
        }).addPathPatterns("/**")
                //过滤到登录的url,不拦截
                .excludePathPatterns("/admin","/admin/","/admin/login.html");
        super.addInterceptors(registry);
    }
}
