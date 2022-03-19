package com.felix.file.content

import sun.rmi.runtime.Log
import java.io.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.random.Random


//文件夹处理
class DirContentUtil {

//    val resultString = replaceWith.replace("this picture is beautiful","awesome")
//        println(resultString)

    companion object{

        //读取文件内容
        fun readFileContent(strFilePath: String): String{
            //打开文件
            val file = File(strFilePath)
            //如果path是传递过来的参数，可以做一个非目录的判断
            return readFileContent(file)
        }

        //读取单个文件内容
        fun readFileContent(file: File):String{
            var content = "" //文件内容字符串
            if (file.isDirectory) {
            } else {
                try {
                    val instream: InputStream = FileInputStream(file)
                    if (instream != null) {
                        var line: String?
                        val buffreader = BufferedReader(
                            InputStreamReader(FileInputStream(file), "UTF-8")
                        )
                        //分行读取
                        while (buffreader.readLine().also { line = it } != null) {
                            content += "$line\n"
                        }
                        buffreader.close()
                        instream.close()
                    }
                } catch (e: FileNotFoundException) {
                    println("The File doesn't not exist.")
                } catch (e: IOException) {
                    println(e.message!!)
                }
            }
            return content
        }

        fun readDirContent(dirPath: String): HashMap<String,String>{
            val dirContent: HashMap<String,String> = HashMap()
            try {
                val filePath: MutableList<String> = mutableListOf()
                val fileTree: FileTreeWalk = File(dirPath).walk()
                fileTree.maxDepth(Int.MAX_VALUE) //需遍历的目录层次为1，即无须检查子目录
                    .filter { it.isFile } //只挑选文件，不处理文件夹
//        .filter { it.extension == "txt"  } //选择扩展名为txt的文本文件
//                    .filter { it.extension in listOf("txt","mp4") }//选择扩展名为txt或者mp4的文件
                    .forEach {
                        //处理并保存旧文件的名称  name.java---name
                        val fileAbsolutePath = it.absolutePath
                        val content = readFileContent(fileAbsolutePath)
                        dirContent[fileAbsolutePath] = content
                    }//循环 处理符合条件的文件
//                dirContent.forEach{
//                    println(it.key)
//                }
            }catch (e: Exception){
                println(e.message)
            }finally {
                return dirContent
            }
        }






        //修改文件名（类名）
        fun updateClassName(filePath:String){
            try {
                val fileNames: MutableList<String> = mutableListOf()
                val fileTree: FileTreeWalk = File(filePath).walk()
                fileTree.maxDepth(Int.MAX_VALUE) //需遍历的目录层次为1，即无须检查子目录
                    .filter { it.isFile } //只挑选文件，不处理文件夹
//        .filter { it.extension == "txt"  } //选择扩展名为txt的文本文件
//                    .filter { it.extension in listOf("txt","mp4") }//选择扩展名为txt或者mp4的文件
                    .forEach {
                        //处理并保存旧文件的名称  name.java---name
                        val split = it.name.split(".")
                        fileNames.add(split[0])
                        //修改文件前缀返回的文件
                        updateClassName(it)
//                        val updateFileName = updateFileName(it)
//                        //拿到文件内容
//                        val readFileContent = readFileContent(updateFileName)
//                        //修改类名后的字符串
//                        val replaceWith1 = replaceWith(readFileContent,updateFileName.name.substringBefore("."))
//                        //写进文件内
//                        val outFile  = FileOutputStream(updateFileName)
//                        outFile.write(replaceWith1.toByteArray())
//                        outFile.flush()
//                        outFile.close()

                    }//循环 处理符合条件的文件
                fileNames.forEach(::println)
            }catch (e:java.lang.Exception){
                println(e.message)
            }
        }
        //正则匹配类名
        val replaceWith = """class (\w+3) """.toRegex()

        val data = """\{""".toRegex()
        val data1 = """\) \{""".toRegex()
        val data2 = """\)\{""".toRegex()

        val replace = "_".toRegex()

        fun replaceWith(originalContent:String,fileName:String):String{
            return replaceWith.replace(originalContent, "class $fileName ")
        }


        fun updateFileContent(filePath: String){
            val content = readFileContent(filePath)
//            println(content)
//            println(generateStr(3))
            updateFileNameAndContent("F:\\WorkSpace\\YuChuang\\MaDana\\app\\src\\main\\java\\com\\creationtime\\madana\\entity\\databox")
        }

        //生成随机字符串
        fun generateStr(leght: Int): String{
            try {
                val ALPHA_NUMERIC = ('A'..'Z') + ('a'..'z')
                return List(leght) { Random.nextInt(0, ALPHA_NUMERIC.size) }
                    .map { ALPHA_NUMERIC[it] }
                    .joinToString(separator = "")
            }catch (e:Exception){
                println(e.message)
                return ""
            }
        }

        //生成类名前缀或后缀
        fun String.generateClassInfo(length: Int):String{
            try {
//                val ALPHA_NUMERIC = ('A'..'Z') + ('a'..'z')
                val prefix = ('A'..'Z').toList()
                val suffix = ('a'..'z').toList()

                return List(1) { Random.nextInt(0, prefix.size) }
                    .map { prefix[it] }
                    .joinToString(separator = "") +
                        List(length) { Random.nextInt(0, suffix.size) }
                            .map { suffix[it] }
                            .joinToString(separator = "") + this

            }catch (e:Exception){
                println(e.message)
                return ""
            }
        }

        //生成layout名称
        fun String.generateLayoutInfo(length: Int):String{
            try {
//                val ALPHA_NUMERIC = ('A'..'Z') + ('a'..'z')
//                val prefix = ('A'..'Z').toList()
                val suffix = ('a'..'z').toList()

                val split = split(replace,2)

                return split[0]+ "_" + List(length) { Random.nextInt(0, suffix.size) }
                    .map { suffix[it] }
                    .joinToString(separator = "") + "_" + split[1]
            }catch (e:Exception){
                println(e.message)
                return ""
            }
        }


        //修改文件名和类名(java类)
        fun updateFileNameAndContent(filePath:String){
            try {
                val fileNames: MutableList<String> = mutableListOf()
                val fileTree: FileTreeWalk = File(filePath).walk()
                fileTree.maxDepth(Int.MAX_VALUE) //需遍历的目录层次为1，即无须检查子目录
                    .filter { it.isFile } //只挑选文件，不处理文件夹
//        .filter { it.extension == "txt"  } //选择扩展名为txt的文本文件
//                    .filter { it.extension in listOf("txt","mp4") }//选择扩展名为txt或者mp4的文件
                    .forEach {
                        //处理并保存旧文件的名称  name.java---name
                        val split = it.name.split(".")
                        fileNames.add(split[0])
                        //修改文件前缀返回的文件
                        val updateFileName = updateClassName(it)
                        //拿到文件内容
                        val readFileContent = readFileContent(updateFileName)
                        //修改类名后的字符串
                        val replaceWith1 = replaceWith(readFileContent,updateFileName.name.substringBefore("."))
                        //写进文件内
                        val outFile  = FileOutputStream(updateFileName)
                        outFile.write(replaceWith1.toByteArray())
                        outFile.flush()
                        outFile.close()

                    }//循环 处理符合条件的文件
                fileNames.forEach(::println)
            }catch (e:java.lang.Exception){
                println(e.message)
            }
        }


        //java文件增加垃圾代码（实体类增加无用字段）
        fun updateFileContentWithClass(filePath: String){
            try {
                val fileTree: FileTreeWalk = File(filePath).walk()
                fileTree.maxDepth(Int.MAX_VALUE) //需遍历的目录层次为1，即无须检查子目录
                    .filter { it.isFile } //只挑选文件，不处理文件夹
//        .filter { it.extension == "txt"  } //选择扩展名为txt的文本文件
//                    .filter { it.extension in listOf("txt","mp4") }//选择扩展名为txt或者mp4的文件
                    .forEach {
                        var content = ""
                        val instream: InputStream = FileInputStream(it)
                        var line: String? = null
                        val buffreader = BufferedReader(
                            InputStreamReader(
                                FileInputStream(it), "UTF-8"
                            )
                        )
                        //分行读取
                        while (buffreader.readLine().also { line = it } != null) {
                            if (line!!.contains(data)&&!line!!.contains(data1)&&!line!!.contains(data2)){
                                line += GenerateStr.createJavaField(4, 6, Random.nextInt(3,6));
                            }
                            content += "$line\n"
                        }
                        instream.close()
                        //写进文件内
                        val outFile  = FileOutputStream(it)
                        outFile.write(content.toByteArray())
                        outFile.flush()
                        outFile.close()
                    }
            }catch (e:java.lang.Exception){
                println(e.message)
            }
        }

        //修改文件名（layout）
        fun updateLayoutName(filePath:String){
            try {
                val fileNames: MutableList<String> = mutableListOf()
                val fileTree: FileTreeWalk = File(filePath).walk()
                fileTree.maxDepth(Int.MAX_VALUE) //需遍历的目录层次为1，即无须检查子目录
                    .filter { it.isFile } //只挑选文件，不处理文件夹
//        .filter { it.extension == "txt"  } //选择扩展名为txt的文本文件
//                    .filter { it.extension in listOf("txt","mp4") }//选择扩展名为txt或者mp4的文件
                    .forEach {
                        //处理并保存旧文件的名称  name.java---name
                        val split = it.name.split(".")
                        fileNames.add(split[0])
                        //修改文件前缀返回的文件
                        updateLayoutName(it)
//                        val updateFileName = updateFileName(it)
//                        //拿到文件内容
//                        val readFileContent = readFileContent(updateFileName)
//                        //修改类名后的字符串
//                        val replaceWith1 = replaceWith(readFileContent,updateFileName.name.substringBefore("."))
//                        //写进文件内
//                        val outFile  = FileOutputStream(updateFileName)
//                        outFile.write(replaceWith1.toByteArray())
//                        outFile.flush()
//                        outFile.close()

                    }//循环 处理符合条件的文件
                fileNames.forEach(::println)
            }catch (e:java.lang.Exception){
                println(e.message)
            }
        }

        //修改文件名称(类名)
        fun updateClassName(oldFile:File):File{
            var length =  (2..4).random()
            val name = oldFile.parent+ "\\" + oldFile.name.generateClassInfo(length)
            var newFile = File(name)
            return if (oldFile.renameTo(newFile)){
                newFile
            }else {
                oldFile
            }
        }

        //修改文件名称(layout)
        fun updateLayoutName(oldFile:File):File{
            var length =  (4..6).random()

            val name = oldFile.parent+ "\\" + oldFile.name.generateLayoutInfo(length)
            var newFile = File(name)
            return if (oldFile.renameTo(newFile)){
                newFile
            }else {
                oldFile
            }
        }
    }




}