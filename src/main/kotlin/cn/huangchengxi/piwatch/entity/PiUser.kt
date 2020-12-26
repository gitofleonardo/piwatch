package cn.huangchengxi.piwatch.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList

@Entity
class PiUser:UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long=0
    lateinit var piUsername:String
    lateinit var piPassword:String
    @OneToMany(fetch = FetchType.EAGER)
    var roles:Collection<PiRole> = ArrayList()

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val auths=roles.map {
            SimpleGrantedAuthority(it.roleName)
        }
        return LinkedList(auths)
    }

    override fun getPassword(): String {
        return piPassword
    }

    override fun getUsername(): String {
        return piUsername
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}