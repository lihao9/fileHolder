package com.felix.file.androidhandle

import com.felix.file.content.FileUtil
import com.felix.file.content.GenerateStr
import java.io.*

object AndroidResourceUtils {
    //所有文件路径
    val ALL_PATH = "F:\\untitled\\sourceFile"
    //布局文件路径
    val ALL_LAYOUT_PATH = "F:\\untitled\\sourceFile\\res\\layout"

    //代码文件路径
    val ALL_CODE_PATH = "F:\\untitled\\sourceFile\\java"
    //资源文件路径
    val ALL_REQOURCE_PATH = "F:\\untitled\\sourceFile\\res"

    //请求URL文件路径
    val REQUEST_URL = "F:\\untitled\\resultFile\\java\\com\\simple\\convenientloan\\requestconfig\\IRequestCenter.kt"
    //请求参数文件路径
    val REQUEST_PARAMS = "F:\\untitled\\resultFile\\java\\com\\simple\\convenientloan\\tool\\ParamKey.kt"
    //资源文件anim文件夹的路径
    val RES_ANIM = "F:\\untitled\\sourceFile\\res\\anim"
    //资源文件drawable文件夹路径
    val RES_DRAWABLE = "F:\\untitled\\sourceFile\\res\\drawable"
    //资源文件drawable文件夹路径
    val RES_STRING = "F:\\untitled\\sourceFile\\res\\values\\strings.xml"

    //目标anim路径
    val RES_ANIM_RESULT = "F:\\untitled\\resultFile\\res\\anim"
    //目标drawable路径
    val RES_DRAWABLE_RESULT = "F:\\untitled\\resultFile\\res\\drawable"
    //目标mipmap路径1
    val RES_MIPMAPX_RESULT = "F:\\untitled\\resultFile\\res\\mipmap-xhdpi"
    //目标mipmap路径2
    val RES_MIPMAPXX_RESULT = "F:\\untitled\\resultFile\\res\\mipmap-xxhdpi"
    //目标mipmap路径3
    val RES_MIPMAPXXX_RESULT = "F:\\untitled\\resultFile\\res\\mipmap-xxxhdpi"
    //目标mipmap路径1
    val RES_MIPMAPX_RESOUR = "F:\\untitled\\mipmap\\mipmap-xhdpi"



    //匹配Id正则表达式
    val idResource = """android:id="@\+id/(\w+)"""".toRegex()
    val stringResource = """name="(\w+)"""".toRegex()


    //获取所有的ID值
    fun getId(map: HashMap<String,String>): ArrayList<String>{
        val array = ArrayList<String>()
        map.map {
            val findAll = idResource.findAll(it.value).toList()
            findAll.map { str ->
                val s = str.groupValues[0]
                val substring = s.substring(17, s.length - 1)
                array.add(substring)
            }
        }
//            array.map {
//                println(it)
//            }
        return array
    }

    //获取所有的ID值
    fun getName(map: HashMap<String,String>): ArrayList<String>{
        val array = ArrayList<String>()
        map.map {
            val findAll = stringResource.findAll(it.value).toList()
            findAll.map { str ->
                val s = str.groupValues[0]
                val substring = s.substring(6, s.length - 1)
                array.add(substring)
            }
        }
        array.map {
            println(it)
        }
        return array
    }


    val urlStart = """V4/(\w{3})""".toRegex()
    val urlEnd = """(\w{3})"\)""".toRegex()
    //修改文件内容（请求链接前3后3）
    fun changeUrl(version: String,strFilePath: String) {
        try {
            val file = File(strFilePath)

            if (file.isDirectory) {
            } else {
                // 内存流, 作为临时流
                var tempStream = CharArrayWriter()
                var line: String? = null
                val buffreader = BufferedReader(InputStreamReader(FileInputStream(file), "UTF-8"))
                //分行读取
                while (buffreader.readLine().also { line = it } != null) {
                    val replace = urlStart.replace(line!!, version+ GenerateStr.generateFixedLengthField(3))
                    val result = urlEnd.replace(replace, GenerateStr.generateFixedLengthField(3)+"\")")
                    tempStream.write(result)
                    tempStream.append("\n")
                }

                var out = FileWriter(strFilePath)
                tempStream.writeTo(out)
                out.flush()
                out.close()

                tempStream.flush()
                tempStream.close()

            }
        } catch (e: FileNotFoundException) {
            println("The File doesn't not exist.")
        } catch (e: IOException) {
            println(e.message!!)
        }
    }

    //修改请求参数（随机前3后2字符串）
    //匹配[ “xxx]字符
    val startReplaceWith = """ "(\w{3})""".toRegex()
    //匹配[xx"]
    val endReplaceWith = """(\w{2})"""".toRegex()
    fun changeParams(strFilePath: String) {
        try {
            val file = File(strFilePath)
            if (file.isDirectory) {
            } else {
                // 内存流, 作为临时流
                var tempStream = CharArrayWriter()
                var line: String? = null
                val buffreader = BufferedReader(InputStreamReader(FileInputStream(file), "UTF-8"))
                //分行读取
                while (buffreader.readLine().also { line = it } != null) {
                    val replace = startReplaceWith.replace(line!!, GenerateStr.generateStr(" \"",3))
                    val result = endReplaceWith.replace(replace, GenerateStr.generateEndStr("\"",2))
                    tempStream.write(result)
                    tempStream.append("\n")
                }

                var out = FileWriter(strFilePath)
                tempStream.writeTo(out)
                out.flush()
                out.close()

                tempStream.flush()
                tempStream.close()
            }
        } catch (e: FileNotFoundException) {
            println("The File doesn't not exist.")
        } catch (e: IOException) {
            println(e.message!!)
        }
    }

}