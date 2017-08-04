package com.zuicoding.platform.blog.core;

import org.springframework.boot.autoconfigure.velocity.VelocityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.velocity.VelocityLayoutView;
import org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver;

/**
 * Created by Stephen.lin on 2017/7/11.
 * <p>
 * Description :<p></p>
 */
@Configuration
public class VelocityConfig {

    @Bean(name="velocityViewResolver")
    public VelocityLayoutViewResolver createVelocityLayoutViewResolver(VelocityProperties properties){
        VelocityLayoutViewResolver resolver = new VelocityLayoutViewResolver();
        resolver.setPrefix(properties.getPrefix());
        resolver.setSuffix(properties.getSuffix());
        resolver.setDateToolAttribute(properties.getDateToolAttribute());
        resolver.setNumberToolAttribute(properties.getNumberToolAttribute());
        resolver.setContentType(properties.getContentType().toString());
        resolver.setExposeSpringMacroHelpers(properties.isExposeSpringMacroHelpers());
        resolver.setExposeRequestAttributes(properties.isExposeRequestAttributes());
        resolver.setRequestContextAttribute(properties.getRequestContextAttribute());
        resolver.setViewClass(VelocityLayoutView.class);
        resolver.setLayoutUrl("/pages/layout/layout.vm");

        return resolver;
    }
}
