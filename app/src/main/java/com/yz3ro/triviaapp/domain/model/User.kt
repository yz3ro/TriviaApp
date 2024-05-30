package com.yz3ro.triviaapp.domain.model

data class User(
    val email :String,
    val username : String,
    val age : String,
){
    constructor() : this ("","","")
}
