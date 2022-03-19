package com.felix.file

import com.felix.file.androidhandle.*
import com.felix.file.content.DirContentUtil
import com.felix.file.content.DirContentUtil.Companion.updateFileContentWithClass
import com.felix.file.content.FileUtil
import com.felix.file.content.FileUtil.Companion.ChangeFileContentFont4
import com.felix.file.content.GenerateStr.createSingleId


import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope.coroutineContext
import java.io.*
import java.lang.NullPointerException
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.experimental.coroutineContext

fun main(){




    //修改Id
//    AndroidHandleUtil.changeId(AndroidResourceUtils.ALL_ID_PATH,AndroidResourceUtils.ALL_CODE_PATH)

    //修改anim文件名称
//    AndroidHandleUtil.changeAnimName(AndroidResourceUtils.RES_ANIM,AndroidResourceUtils.ALL_PATH)

    //修改drawable文件名称
//    AndroidHandleUtil.changeDrawableName(AndroidResourceUtils.RES_DRAWABLE,AndroidResourceUtils.ALL_PATH)




//    AndroidHandleUtil.changeResource()
//    AndroidHandleUtil.changeStringName()




    //请求链接前三后三
//    AndroidResourceUtils.changeUrl("V4/",AndroidResourceUtils.REQUEST_URL)
    //修改请求参数前三后二
//    AndroidResourceUtils.changeParams(AndroidResourceUtils.REQUEST_PARAMS)

    updateFileContentWithClass("F:\\untitled\\resultFile\\java\\com\\simple\\convenientloan\\dataclass")

    //修改string前
//    ChangeFileContentFont4("F:\\untitled\\resultFile\\res\\values\\strings.xml")



//    var str = "@mipmap/info_schedule_four"
//    var str1 = "info_schedule_four"
//    val idResource = Regex("""\b$str1\b""")
//
//    val replace = str.replace(idResource, "sjaieg")
//    println(replace)

//
//    AndroidHandleUtil.changeMipmapName(AndroidResourceUtils.RES_MIPMAPX_RESULT,AndroidResourceUtils.ALL_PATH)

    //修改mipmap文件名称
//    AndroidHandleUtil.changeDrawableName(AndroidResourceUtils.RES_DRAWABLE,AndroidResourceUtils.ALL_PATH)

    //修改请求URL前三后三
//    AndroidResourceUtils.changeUrl("V4/",AndroidResourceUtils.REQUEST_URL)

    //修改请求参数前三后二
//    AndroidResourceUtils.changeParams(AndroidResourceUtils.REQUEST_PARAMS)



}

fun changeId(){
    val xmlContent = DirContentUtil.readDirContent("C:\\Users\\Administrator\\Desktop\\replaceContent\\layout")
    val kotlinContent = DirContentUtil.readDirContent("C:\\Users\\Administrator\\Desktop\\replaceContent\\kotlin")
    val idArray = FileUtil.getId(xmlContent)

    var changeId =  ArrayList<String>()

    for (i in 0 until idArray.size){
        changeId.add(createSingleId("id_",3,8,4))
    }

//    changeId.map {
//        println(it)
//    }
//    println(changeId.size)
//    println(idArray.size)
//
//    idArray.map {
//        println(it)
//    }

    idArray.map { id ->
        val idResource = id.toRegex()
        xmlContent.map { tempXmlMap ->
            xmlContent[tempXmlMap.key] = idResource.replace(tempXmlMap.value, changeId[idArray.indexOf(id)])
        }

        kotlinContent.map { tempKotMap ->
            kotlinContent[tempKotMap.key] = tempKotMap.value.replace(idResource,changeId[idArray.indexOf(id)])
        }
    }

    writeContent(xmlContent)
    writeContent(kotlinContent)

}


fun writeContent(fileMap: HashMap<String,String>){

    fileMap.map {

        try {
            val file = File(it.key)
            if (file.isDirectory) {
            } else {
                val fw = FileWriter(file, false)
                fw.write(it.value)
                fw.close()
            }
        }catch (e: IOException) {
            e.printStackTrace()
        }
    }



}




//fun main() = runBlocking {
//    val startTime = System.currentTimeMillis()
//    val job = launch(Dispatchers.Default) {
//        var nextPrintTime = startTime
//        var i = 0
//        try {
//            while (i < 5) { // 一个执行计算的循环，只是为了占用 CPU
//                // 每秒打印消息两次
//                if (System.currentTimeMillis() >= nextPrintTime) {
//                    println("job: I'm sleeping ${i++} ...")
//                    nextPrintTime += 500L
//                }
//            }
//        }finally {
//            println("job: finally")
//        }
//    }
//
//    delay(1300L) // 等待一段时间
//    println("main: I'm tired of waiting!")
//    job.cancelAndJoin() // 取消一个作业并且等待它结束
//    println("main: Now I can quit.")
//}
//fun main() = runBlocking { // this: CoroutineScope
//
//    println(Thread.currentThread().name)
////    println(coroutineContext.toString())
//
//    val start = System.currentTimeMillis()
//
//    val job = launch {
//        println(Thread.currentThread().name)
////        println(coroutineContext.toString())
//
////        try {
////            repeat(1000) { i ->
////                println("job: I'm sleeping $i ...")
////                delay(500L)
////            }
////        } finally {
////            println("job: I'm running finally")
////        }
//
//        try {
//            var nextPrintTime =start
//            var i = 0
//            while (i<5) { // 可以被取消的计算循环
//                // 每秒打印消息两次
//
//                if (System.currentTimeMillis() >= nextPrintTime) {
//                    println("job: I'm sleeping ${i++} ...")
//                    nextPrintTime += 500L
//                }
//
////                delay(500L)
////                println("job: I'm sleeping ${i++} ...")
//
//
//            }
//
//        }catch (e:Exception){
//            println(e.message?:e.toString())
//        }finally {
//            println("finally")
//        }
//    }
//    println("main: I'm tired of Starting!")
//    delay(800L) // 等待一段时间
//    println("main: I'm tired of waiting!")
//    job.cancelAndJoin() // 取消一个作业并且等待它结束
//    println("main: Now I can quit.")
//
//
//
//
////    coroutineScope { // 创建一个协程作用域
////        launch {
////            delay(500L)
////            println("Task from nested launch")
////        }
////        println(coroutineContext.toString())
////        delay(100L)
////        println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
////    }
////
//////    testOne()
////
////    println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
//}

suspend fun testOne(){
    delay(30L)
    println("Task from suspend fun")
}





//    val path = "F:/untitled/sourceFile/hello/word.txt"
//
//    var file = File(path)
//    file.mkdirs()
//    if (file.exists()){
//        println("文件存在")
//    }else{
//
//        println("文件不存在")
//    }



//    val start= System.currentTimeMillis()
//    println(System.currentTimeMillis().toString())
//
//    async {
//        doSomethingUsefulOne()
//    }.await()
//    async {
//        doSomethingUsefulTwo()
//    }.await()
/* val async2 = async {
     doOne(async.await())
 }


 val async3 = async {
     doTow(async1.await())
 }*/
//    async.await()
//    async1.await()

//    println((System.currentTimeMillis()-start))


private suspend fun test() = coroutineScope{
    delay(1000L)
    println("World!")
}

private suspend fun doTow(temp:Int){
    try {
        throw NullPointerException("null")
        delay(1000)
        println("async1+")
        temp
    }catch (e:java.lang.Exception){
        println(e.message?:e.toString())
    }

}

private suspend fun doOne(temp:Int){
    delay(1000)
    println("async+")
    temp
}


suspend fun doSomethingUsefulOne(): Int {
    delay(1000)
    println("doSomethingUsefulOne")
    return 5
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000)
    println("doSomethingUsefulTwo")
    return 8
}

