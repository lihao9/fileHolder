package com.maotom.study

class Book(name1:String = "") {


    init {
        println("logcat is in init")
    }

    constructor(name: String,i: Int):this(name){
        println("logcat in 次构造函数 i = $i")
    }

    val firstProperty = "logcat is in normal".also { println(it) }

    var name = name1.toUpperCase()

    fun showInfo(){
        println(name)
    }


}