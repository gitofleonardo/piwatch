package cn.huangchengxi.piwatch.repo

import cn.huangchengxi.piwatch.entity.PiUser
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository:JpaRepository<PiUser,Long> {
    fun findByPiUsername(piUsername: String):PiUser?
}