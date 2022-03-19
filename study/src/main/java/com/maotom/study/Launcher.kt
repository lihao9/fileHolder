package com.maotom.study

import com.sun.media.jfxmediaimpl.MediaDisposer
import jdk.nashorn.internal.runtime.regexp.joni.Config.log
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

fun main() = runBlocking<Unit>{

//    launch {
//        for (k in 1..3) {
//            println("I'm not blocked $k")
//            delay(1000)
//        }
//    }
//    // 收集这个流
//    simple().collect { value -> println(value) }
//

//    (1..3).asFlow().collect { value ->  println(value) }


//    (1..5).asFlow()
//        .filter {
//            println("Filter $it")
//            true
//        }
//        .map {
//            println("Map $it")
//            "string $it"
//        }.collect {
//            println("Collect $it")
//        }

    val channel = Channel<Int>()
    launch {
        channel.send(5)
    }
    println(channel.receive())
}

suspend fun simple(): Flow<Int> = flow { // 流构建器
    for (i in 1..3) {
        delay(1000) // 假装我们在这里做了一些有用的事情
        println("${Thread.currentThread().name}")
        emit(i) // 发送下一个值

    }
}.flowOn(Dispatchers.IO)



fun listTest(){
    val list = mutableListOf("one","two")
    list.add("three")
    list.map {
        println(it)
    }

    val list1 = listOf("one","two")
    val iterator = list1.iterator()
    while (iterator.hasNext()){
        println(iterator.next())
    }
}

//测试协成
suspend fun testCoroutines(){

    val launch = GlobalScope.launch {
        delay(1000)
        println("in coroutines")
    }





//
//    println("in out")
//
//    Thread.sleep(2000)

//    Thread{
//        Thread.sleep(1000)
//        println("in thread")
//    }.start()

//    launch.join()
    coroutineScope {
        delay(1500)
        println("in coroutines2")
    }


    delay(2000)
    println("in out")



}