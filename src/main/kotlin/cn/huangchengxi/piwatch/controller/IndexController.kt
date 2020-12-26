package cn.huangchengxi.piwatch.controller

import cn.huangchengxi.piwatch.modelinfo.*
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.net.Inet4Address
import java.net.Inet6Address
import java.net.InetAddress
import java.net.NetworkInterface

@Controller
class IndexController {
    @GetMapping("/")
    fun index(model:Model):String{
        val interfaces=getNetState()
        model.addAttribute("interfaces",interfaces)
        return "index"
    }
    private fun getNetState():List<NetInterface>{
        val ints= NetworkInterface.getNetworkInterfaces().toList()
        for (inter in ints){
            println(inter)
        }
        return ints.map { networkInterface ->
            NetInterface(networkInterface?.name,StringBuilder().apply {
                if (networkInterface.hardwareAddress==null){
                    this.append("No mac address.")
                    return@apply
                }
                for (i in networkInterface.hardwareAddress.indices){
                    if(i!=0){
                        append(":")
                    }
                    val b=networkInterface.hardwareAddress[i]
                    val s=b.toInt().toString(16)
                    append(s)
                }
            }.toString(),networkInterface?.inetAddresses?.toList()?.map { getNetAddress(it) }?: emptyList())
        }
    }
    private fun getNetAddress(address: InetAddress):NetAddress{
        val type=when(address){
            is Inet4Address->{
                TYPE_IPV4
            }
            is Inet6Address->{
                TYPE_IPV6
            }
            else->{
                TYPE_UNKNOWN
            }
        }
        return NetAddress(type,address.hostName)
    }
}