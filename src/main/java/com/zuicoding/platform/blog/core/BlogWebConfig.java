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
import java.io.PrintWriter;

/**
 * Created by Stephen.lin on 2017/8/5.
 */
@Configuration
public class BlogWebConfig extends WebMvcConfigurerAdapter {

    private LogUtil log =LogUtil.newLogUtil(BlogWebConfig.class);

    private static final String AJAX_HEADER_VALUE = "XMLHttpRequest";

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
    public void addInterceptors(final InterceptorRegistry registry) {

        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request,
                                     HttpServletResponse response,
                                     Object handler) throws Exception {

                HttpSession session = request.getSession(true);
                WpUser user = (WpUser) session.getAttribute("user");
                boolean isAjax = false;
                String ajaxHeaderVal = request.getHeader("X-Requested-With");
                if (log.isDebugEnabled()){
                    log.d("X-Requested-With header Value : {}",ajaxHeaderVal);
                }
                if (AJAX_HEADER_VALUE.equalsIgnoreCase(ajaxHeaderVal)){
                    isAjax =true;
                }
                if (user == null){
                    if (log.isDebugEnabled()){
                        log.d("isAjax Value: {}",isAjax);
                    }
                    if (isAjax){
                        PrintWriter writer = response.getWriter();
                        writer.write("loginOut");
                        writer.flush();
                        writer.close();
                        return false;
                    }
                    response.sendRedirect("/admin/");
                    return false;
                }
                UserHolder.set(user);
                return true;
            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
                UserHolder.clear();
            }
        }).addPathPatterns("/**")
                //过滤到登录的url,不拦截
                .excludePathPatterns("/admin","/admin/","/admin/login.html");
        super.addInterceptors(registry);
    }
}
