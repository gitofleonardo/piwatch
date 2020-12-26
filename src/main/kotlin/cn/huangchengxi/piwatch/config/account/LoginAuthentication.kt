package cn.huangchengxi.piwatch.config.account

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class LoginAuthentication(@Autowired manager: LoginAuthenticationManager):AbstractAuthenticationProcessingFilter(AntPathRequestMatcher("/user/login"),manager) {
    override fun attemptAuthentication(p0: HttpServletRequest, p1: HttpServletResponse): Authentication {
        val username=obtainUsername(p0)?:throw UsernameNotFoundException("Username cannot be null.")
        val password=obtainPassword(p0)?:throw UsernameNotFoundException("Password cannot be null.")
        val token=UsernamePasswordAuthenticationToken(username,password)
        return authenticationManager.authenticate(token)
    }
    private fun obtainUsername(request: HttpServletRequest): String? {
        return request.getParameter("username")
    }
    private fun obtainPassword(request: HttpServletRequest):String?{
        return request.getParameter("password")
    }
}