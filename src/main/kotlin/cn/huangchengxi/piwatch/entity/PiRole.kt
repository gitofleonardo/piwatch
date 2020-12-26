package cn.huangchengxi.piwatch.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class PiRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long=0
    lateinit var roleName:String
}