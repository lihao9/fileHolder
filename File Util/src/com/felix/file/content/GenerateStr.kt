package com.felix.file.content

import kotlin.math.max
import kotlin.random.Random

//生成字符串
object GenerateStr{

    //生成固定长度的随机大小写字符串
    fun generateFixedLengthField(leght: Int): String{
        try {
            val ALPHA_NUMERIC = ('A'..'Z') + ('a'..'z')
            val reqult = List(leght) { Random.nextInt(0, ALPHA_NUMERIC.size) }
                .map { ALPHA_NUMERIC[it] }
                .joinToString(separator = "")
            return reqult
        }catch (e:Exception){
            println(e.message)
            return ""
        }
    }
    //生成单个字符
    fun generateChar(): Char{
        try {
            val ALPHA_NUMERIC = ('A'..'Z') + ('a'..'z')
            return ALPHA_NUMERIC[Random.nextInt(0, ALPHA_NUMERIC.size)]
        }catch (e:Exception){
            println(e.message)
            return 'a'
        }
    }
    //生成随机字符串(以 "开头)
    fun generateStr(start: String,leght: Int): String{
        try {
            val ALPHA_NUMERIC = ('A'..'Z') + ('a'..'z')
            val reqult =  start + List(leght) { Random.nextInt(0, ALPHA_NUMERIC.size) }
                .map { ALPHA_NUMERIC[it] }
                .joinToString(separator = "")
            return reqult
        }catch (e:Exception){
            println(e.message)
            return ""
        }
    }
    //生成随机字符串（以"结尾）
    fun generateEndStr(ensStr: String,leght: Int): String{
        try {
            val ALPHA_NUMERIC = ('A'..'Z') + ('a'..'z')
            return List(leght) { Random.nextInt(0, ALPHA_NUMERIC.size) }
                .map { ALPHA_NUMERIC[it] }
                .joinToString(separator = "") + ensStr
        }catch (e:Exception){
            println(e.message)
            return ""
        }
    }
    //随机生成字符串（长度范围内的小写字符串）
    fun stringsKey(minLen:Int, maxLen:Int): String{
        val len = Random.nextInt(minLen,maxLen)
        return List(len) { CharRange('a','z').random() }
            .joinToString(separator = "")
    }
    //生成java实体类的无用变量
    fun createJavaField(minLen:Int, maxLen:Int,count:Int): String{
        val listOf = listOf("String", "Integer", "Long","ArrayList<String>","ArrayList<Integer>","Float")

        var result: ArrayList<String> = ArrayList()
        var i = 0
        while (i<count){
            var x = Random.nextInt(0,listOf.size)
            val s = listOf[x]
            result.add("private "+ s + " "+stringsKey(minLen,maxLen)+";")
            i++
        }
        var str = "";
        result.map {
            str = "\n"+it+str
        }
        return str;
    }

    //创建Id字符串（以固定内容字符串开头）
    fun createIdStr(start: String,minLen: Int,maxLen: Int,account: Int,maxAccount: Int): ArrayList<String>{
        var result: ArrayList<String> = ArrayList()
        var i = 0
        while (i<account){
            try {
                val endLen1 = Random.nextInt(1,maxAccount)
                var x = 0
                var str = ""
                while (x<endLen1){
                    val len = Random.nextInt(minLen,maxLen)
                    if (x==0){
                        str = start+"_"+List(len) { CharRange('a','z').random() }.joinToString(separator = "")
                    }else{
                        str = str + "_" + List(len) { CharRange('a','z').random() }.joinToString(separator = "")
                    }
                    result.add(str)
                    x++
                }

            }catch (e:Exception){
                println(e.message)
            }
            i++
        }
        result.map {
            println(it)
        }
        return result
    }

    fun createIdStr1(minLen: Int,maxLen: Int,lenght: Int,account: Int): String{

        for (i in 0..account){
            var str = ""
            val endLen1 = Random.nextInt(1,lenght)
            val strLen = Random.nextInt(minLen,maxLen)
            for (i in 1..endLen1){
                str = str+List(strLen) { CharRange('a','z').random() }.joinToString(separator = "")+"_"
            }
            val substring = str.substring(0, str.length - 1)
            println(substring)
        }

        return ""
    }



    //生成单个ID
    fun createSingleId(startStr: String,wordMinLen:Int,wordMaxLen: Int,wordAccount:Int): String{
        var resultStr: String = ""

        var curWordAccount = Random.nextInt(wordMinLen,wordMaxLen)

        for (i in 0 until wordAccount){
            val len = Random.nextInt(wordMinLen,wordMaxLen)
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

    //随机生成字符串资源文件key值
    fun stringsKey(minLen:Int, maxLen:Int,count: Int){
        var result: ArrayList<String> = ArrayList()
        var i = 0
        while (i<count){
            try {
                val joinToStr1 = List(1) { CharRange('A','Z').random() }.joinToString(separator = "")
                val joinToStr2 = List(1) { CharRange('A','Z').random() }.joinToString(separator = "")

                val len = Random.nextInt(minLen,maxLen)
                if ( i%3 == 0 ){
                    result.add( List(len) { CharRange('a','z').random() }
                        .joinToString(separator = "")+"_" + List(len) { CharRange('a','z').random() }
                        .joinToString(separator = ""))
                }else{
                    result.add(List(len) { CharRange('a','z').random() }
                        .joinToString(separator = ""))
                }
            }catch (e:Exception){
                println(e.message)
            }
            i++
        }
        result.map {
            println(it)
        }
    }

    //生成固定长度的小写字符串
    fun stringsKey(lenght:Int): String{
        return List(lenght) { CharRange('a','z').random() }
            .joinToString(separator = "")
    }

    //生成layout文件名称(activity)
    fun createFileName(minLen:Int, maxLen:Int,count: Int){
        var result: ArrayList<String> = ArrayList()
        var i = 0
        while (i<count){
            if(i%4 == 0){
                result.add("activity_"+stringsKey(minLen,maxLen)+"_"+stringsKey(minLen,maxLen)+"_"+stringsKey(minLen,maxLen))
            }else{
                result.add("activity_"+stringsKey(minLen,maxLen)+"_"+stringsKey(minLen,maxLen))
            }
            i++
        }
        result.map {
            println(it)
        }
    }

    //生成layout文件名称(fragment)
    fun createFileName1(minLen:Int, maxLen:Int,count: Int){
        var result: ArrayList<String> = ArrayList()
        var i = 0
        while (i<count){
            if(i%4 == 0){
                result.add("fragment_"+stringsKey(minLen,maxLen)+"_"+stringsKey(minLen,maxLen)+"_"+stringsKey(minLen,maxLen))
            }else{
                result.add("fragment_"+stringsKey(minLen,maxLen)+"_"+stringsKey(minLen,maxLen))
            }
            i++
        }
        result.map {
            println(it)
        }
    }

    //生成图片资源文件名称
    fun createImgName(minLen:Int, maxLen:Int,count: Int){
        var result: ArrayList<String> = ArrayList()
        var i = 0
        while (i<count){
            if(i%4 == 0){
                result.add("str_"+stringsKey(minLen,maxLen)+"_"+stringsKey(minLen,maxLen)+"_"+stringsKey(minLen,maxLen))
            }else{
                result.add("str_"+stringsKey(minLen,maxLen)+"_"+stringsKey(minLen,maxLen))
            }
            i++
        }
        result.map {
            println(it)
        }

    }

    //生成java实体类的无用变量
    fun createStrWithJavaVariable(minLen:Int, maxLen:Int,count:Int){

        val listOf = listOf("String", "Integer", "Long","ArrayList<String>","ArrayList<Integer>")

        var result: ArrayList<String> = ArrayList()
        var i = 0
        while (i<count){
            var x = Random.nextInt(0,listOf.size)
            val s = listOf[x]
            result.add("private "+ s + " "+stringsKey(minLen,maxLen)+";")
            i++
        }
        result.map { println(it) }
    }

    //生成无用字符串资源
//    fun createResString(minLen:Int, maxLen:Int,count: Int){
//        var result: ArrayList<String> = ArrayList()
//        var i = 0
//
//        val stringsKey = stringsKey(minLen, maxLen, count)
//
////        stringsKey.map {
////            result.add("<string name=\"$it"+stringsValue(minLen,maxLen)+"</string>")
////        }
//
//        result.map {
//            println(it)
//        }
//
//    }

    //生成无用颜色资源
    fun createResColor(count: Int){
        var result: ArrayList<String> = ArrayList()
        var i = 0
        val ALPHA_NUMERIC = ('0'..'9') + ('a'..'f')
        while (i<count){
            var color = List(6){Random.nextInt(0, ALPHA_NUMERIC.size)}
                .map { ALPHA_NUMERIC[it] }
                .joinToString(separator = "")

            var str = "color_"+color


            result.add("<color name=\"$str\">#$color</color>")
            i++
        }

        result.map {
            println(it)
        }

    }

    //随机生成字符串（驼峰命名）
    fun stringsKey1(minLen:Int, maxLen:Int,count: Int): List<String>{
        var result: ArrayList<String> = ArrayList()
        var i = 0
        while (i<count){
            try {
                val joinToStr1 = List(1) { CharRange('A','Z').random() }.joinToString(separator = "")
                val joinToStr2 = List(1) { CharRange('A','Z').random() }.joinToString(separator = "")

                val len = Random.nextInt(minLen,maxLen)
                result.add( stringsKey(4,6)+joinToStr1 + List(len) { CharRange('a','z').random()}
                    .joinToString(separator = ""))

            }catch (e:Exception){
                println(e.message)
            }
            i++
        }
        return result
    }

    //生成首字母大写字符串
    fun stringsKey2(minLen:Int, maxLen:Int,count: Int): List<String>{
        var result: ArrayList<String> = ArrayList()
        var i = 0
        while (i<count){
            try {
                val joinToStr1 = List(1) { CharRange('A','Z').random() }.joinToString(separator = "")
                val len = Random.nextInt(minLen,maxLen)
                result.add(joinToStr1 + List(len) { CharRange('a','z').random()}
                    .joinToString(separator = ""))

            }catch (e:Exception){
                println(e.message)
            }
            i++
        }
        return result
    }

    //随机生成长度范围内的多个小写字符串
    fun lowercaseCharacters(minLen:Int, maxLen:Int,count: Int): List<String>{
        var result: ArrayList<String> = ArrayList()
        var i = 0
        while (i<count){
            try {
                val len = if (minLen == maxLen) minLen else Random.nextInt(minLen,maxLen)
                //生成固定长度的随机字符串
//                val len = Random.nextInt(minLen,maxLen)

                result.add(List(len) { CharRange('a','z').random() }
                    .joinToString(separator = ""))
            }catch (e:Exception){
                println(e.message)
            }
            i++
        }
        return result
    }

    //随机生成已>"开头与
    fun stringsValue(startStr: String,len:Int): String{
        var resultStr = ""
        try {
            val ALPHA_NUMERIC = ('A'..'Z') + ('a'..'z')
            resultStr = startStr + List(len) { Random.nextInt(0, ALPHA_NUMERIC.size) }
                .map { ALPHA_NUMERIC[it] }
                .joinToString(separator = "")

        }catch (e:Exception){
            println(e.message)
        }
        println(resultStr)
        return resultStr
    }

    //随机生成已 _字符_
    fun layoutName(min: Int,max:Int): String{
        try {
            val len = Random.nextInt(min,max)
            return "_" + List(len) { CharRange('a','z').random() }
                .joinToString(separator = "")+"_"
        }catch (e:Exception){
            println(e.message)
            return ""
        }
    }
}