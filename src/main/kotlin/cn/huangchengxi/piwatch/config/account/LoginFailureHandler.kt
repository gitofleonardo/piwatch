package cn.huangchengxi.piwatch.config.account

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class LoginFailureHandler:AuthenticationFailureHandler {
    override fun onAuthenticationFailure(
        p0: HttpServletRequest,
        p1: HttpServletResponse,
        p2: AuthenticationException
    ) {
        p1.sendRedirect("/login?behavior=failure")
    }
}