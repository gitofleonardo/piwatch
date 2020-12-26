package cn.huangchengxi.piwatch.config

import com.google.gson.Gson
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.GsonHttpMessageConverter

@Configuration
class GsonConfig {
    @Bean
    fun gsonConverter():GsonHttpMessageConverter{
        val converter=GsonHttpMessageConverter()
        converter.gson= Gson()
        return converter
    }
}