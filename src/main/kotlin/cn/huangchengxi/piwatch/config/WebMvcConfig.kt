package cn.huangchengxi.piwatch.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig:WebMvcConfigurer{
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        //registry
        //    .addResourceHandler("/static/css/**","/static/icon/**","/static/script/**")
        //    .addResourceLocations("path:/static/css/","path:/static/icon/","path:/static/script/")
    }
}