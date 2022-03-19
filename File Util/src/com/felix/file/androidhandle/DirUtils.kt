package com.felix.file.androidhandle

import com.felix.file.content.DirContentUtil
import java.io.File

object DirUtils {



    //读取文件夹中文件的绝对目录和内容
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
                    val content = DirContentUtil.readFileContent(fileAbsolutePath)
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

    fun getFileName(){

    }

}