package cn.huangchengxi.piwatch.service

import cn.huangchengxi.piwatch.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class PiUserService:UserDetailsService {
    @Autowired
    private lateinit var userRepository: UserRepository

    override fun loadUserByUsername(p0: String): UserDetails {
        return userRepository.findByPiUsername(p0)?:throw UsernameNotFoundException("User $p0 not found.")
    }
}