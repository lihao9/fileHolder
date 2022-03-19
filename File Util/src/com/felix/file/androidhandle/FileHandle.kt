package com.felix.file.androidhandle

import java.io.File
import java.io.FileWriter
import java.io.IOException

object FileHandle {

    //将新内容写进文件中去

    val sourceFile = """sourceFile""".toRegex()

    fun writeContent(fileMap: HashMap<String,String>) {

        val resultFileMap = HashMap<String,String>()

        fileMap.map {
            var key = it.key.replace(sourceFile,"resultFile")

            resultFileMap[key] = it.value
        }


        resultFileMap.map {
            try {
                val name = it.key.substring(it.key.lastIndexOf("\\")+1,it.key.length)
                val parent = it.key.substring(0,it.key.lastIndexOf("\\"))

                var parentFile = File(parent)
                if (!parentFile.exists()){
                    parentFile.mkdirs()
                }
                var nameFile = File(parentFile,name)
                if (!nameFile.exists()){
                    nameFile.createNewFile()
                }

                if (nameFile.isDirectory) {
                } else {
                    val fw = FileWriter(nameFile, false)
                    fw.write(it.value)
                    fw.flush()
                    fw.close()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun getFileName(fileMap: HashMap<String,String>):ArrayList<String>{

        val resultList = ArrayList<String>()
        fileMap.map {
            val name = it.key.substring(it.key.lastIndexOf("\\")+1,it.key.length)
            resultList.add(name)
        }
        return resultList
    }



}