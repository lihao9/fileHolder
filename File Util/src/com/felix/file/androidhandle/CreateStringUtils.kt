package com.felix.file.androidhandle

import kotlin.random.Random

object CreateStringUtils {
    //生成固定开头的字符串
    /**
     * startStr 固定字符串
     * wordLen单个单词长度
     * wordAccount单词个数
     * notic 单词中间用_连接
     */
    fun createSingleId(startStr: String,wordAccount:Int): String{
        var resultStr: String = ""
        for (i in 0 until wordAccount){
            val len = createRandomNum(3,8)
            if (i==0){
                resultStr = startStr + List(len) { CharRange('a','z').random() }
                    .joinToString(separator = "")
            }else{
                resultStr = resultStr+"_"+List(len) { CharRange('a', 'z').random() }
                    .joinToString(separator = "")
            }
        }
        return resultStr
    }

    fun createString(length:Int):String{
        return List(length) { CharRange('a','z').random() }
            .joinToString(separator = "")
    }

    //创建多个id
    fun createStringIds(num: Int): ArrayList<String>{
        val resultList = ArrayList<String>()
        for (i in 0 until num){
            resultList.add(createSingleId("id_",createRandomNum(2,5)))
        }
        return resultList
    }

    //生成随机数
    fun createRandomNum(start: Int,end:Int): Int{
       return Random.nextInt(start, end)
    }

    //生成文件名称
    fun createFileNames(oldNames:ArrayList<String>):ArrayList<String>{
        val resultList = ArrayList<String>()
        oldNames.map {
            resultList.add(createSingleId("",createRandomNum(2,5)))
        }
        return resultList
    }
}