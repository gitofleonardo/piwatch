package cn.huangchengxi.piwatch.controller

import cn.huangchengxi.piwatch.config.ResultCode
import cn.huangchengxi.piwatch.entity.PiUser
import cn.huangchengxi.piwatch.repo.UserRepository
import cn.huangchengxi.piwatch.returnbean.BaseResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import javax.websocket.server.PathParam

@Controller
class UserController {
    @Autowired
    private lateinit var userRepository: UserRepository

    @RequestMapping("/login",method = [RequestMethod.GET])
    fun loginPage(@PathParam("behavior")behavior:String?,vm:Model):String{
        val cnt=userRepository.count()
        if (cnt<=0){
            return "init"
        }
        when (behavior){
            "logout"->{
                vm.addAttribute("status","注销成功")
            }
            "failure"->{
                vm.addAttribute("status","帐号或密码错误")
            }
        }
        return "login"
    }
    @RequestMapping("/init",method = [RequestMethod.POST])
    @ResponseBody
    fun init(@PathParam("username") username:String,@PathParam("password")password:String):BaseResult{
        val cnt=userRepository.count()
        if (cnt>0){
            //has been init
            return BaseResult(ResultCode.ALREADY_INIT)
        }
        val encoder=BCryptPasswordEncoder()
        val user=PiUser().apply {
            this.piPassword=encoder.encode(password)
            this.piUsername=username
            this.roles=ArrayList()
        }
        userRepository.save(user)
        return BaseResult(ResultCode.SUCCESS)
    }
}