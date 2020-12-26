package cn.huangchengxi.piwatch.config

import cn.huangchengxi.piwatch.config.account.LoginAuthentication
import cn.huangchengxi.piwatch.config.account.LoginFailureHandler
import cn.huangchengxi.piwatch.config.account.PiLoginSuccessHandler
import cn.huangchengxi.piwatch.service.PiUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class PiWatchSecurity:WebSecurityConfigurerAdapter() {
    @Autowired
    private lateinit var userService:PiUserService
    @Autowired
    private lateinit var mLoginAuthFilter:LoginAuthentication
    @Autowired
    private lateinit var mSuccessHandler:PiLoginSuccessHandler
    @Autowired
    private lateinit var mFailureHandler:LoginFailureHandler

    override fun configure(auth: AuthenticationManagerBuilder) {
        //super.configure(auth)
        auth.userDetailsService(userService).passwordEncoder(BCryptPasswordEncoder())
    }

    override fun configure(web: WebSecurity) {
        super.configure(web)
        //web.ignoring().antMatchers("/css/**","/script/**","/icon/**","/login","/logout","/init","/user/login","/error","favicon.ico")
    }

    override fun configure(http: HttpSecurity) {
        //super.configure(http)
        http
            .authorizeRequests()
            .antMatchers("/css/**","/script/**","/icon/**","/login","/logout","/init","/user/login","/error","favicon.ico")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            //.addFilterBefore(mLoginAuthFilter,UsernamePasswordAuthenticationFilter::class.java)
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/user/login")
            .successHandler(mSuccessHandler)
            .failureHandler(mFailureHandler)
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?behavior=logout")
            .clearAuthentication(true)
            .invalidateHttpSession(true)
            .and()
            .csrf()
            .disable()
    }
}