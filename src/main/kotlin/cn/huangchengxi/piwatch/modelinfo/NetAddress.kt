package cn.huangchengxi.piwatch.modelinfo

const val TYPE_IPV4="ipv4"
const val TYPE_IPV6="ipv6"
const val TYPE_UNKNOWN="unknown address"

data class NetAddress(val type:String,val address:String)