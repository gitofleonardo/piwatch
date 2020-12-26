package cn.huangchengxi.piwatch.config.account

import cn.huangchengxi.piwatch.service.PiUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class LoginAuthenticationManager:AuthenticationManager {
    @Autowired
    private lateinit var mUserService: PiUserService

    override fun authenticate(p0: Authentication): Authentication {
        val user = mUserService.loadUserByUsername(p0.principal.toString())
        val encoder=BCryptPasswordEncoder()
        if (encoder.matches(p0.credentials.toString(),user.password)){
            return UsernamePasswordAuthenticationToken(user.username, user.password, user.authorities)
        }
        //p0.isAuthenticated=true
       throw UsernameNotFoundException("Password not correct.")
    }
}