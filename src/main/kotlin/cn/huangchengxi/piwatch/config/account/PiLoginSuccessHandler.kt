package cn.huangchengxi.piwatch.config.account

import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class PiLoginSuccessHandler:AuthenticationSuccessHandler {
    private val logger=LoggerFactory.getLogger(PiLoginSuccessHandler::class.java)
    override fun onAuthenticationSuccess(p0: HttpServletRequest, p1: HttpServletResponse, p2: Authentication) {
        p0.session.setAttribute("authenticated",true)
        p1.sendRedirect("/")
        logger.info("Login success.Redirecting to /")
    }
}