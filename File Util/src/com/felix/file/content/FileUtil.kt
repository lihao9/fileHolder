package com.felix.file.content

import com.felix.file.content.GenerateStr.generateStr
import java.io.*
import java.lang.StringBuilder
import java.util.regex.Pattern
import kotlin.random.Random


//修改文件内容
class FileUtil {

    companion object {

        //定义的请求借口路径修改 @GET("app/v2/pPqgetPageSetinguMG") 修改前3后3
        val urlStart = """v2/(\w{3})""".toRegex()
        val urlEnd = """(\w{3})"\)""".toRegex()
        //修改文件内容（请求链接前3后3）
        fun changeUrl(strFilePath: String) {
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
                        val replace = urlStart.replace(line!!, "v2/"+GenerateStr.generateFixedLengthField(3))
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
        //修改文件内容（在[ "]后面新增固定长度字符串）
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

        val idResource = """android:id="@\+id/(\w+)"""".toRegex()
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












        //匹配 [["xxx]
        val hashMapStart = """\["(\w{3})""".toRegex()
        //匹配[xx"]]
        val hashMapEnd = """(\w{2})"]""".toRegex()
        //匹配 _字符串_
        val layoutName = """_(.+)_""".toRegex()
        //匹配 act
        val layoutStartName = """act""".toRegex()




        //匹配 ">
        val strRegex = """" >(\w{4})""".toRegex()

        //匹配 ">
        val strEndRegex = """">(\w{4})""".toRegex()

        var rex = "\"[\\w{3}]" //获取双引号中所有非引号内容


        var p: Pattern = Pattern.compile(rex)










        //生成随机字符串（以["开头）
        fun generateHashStartStr(leght: Int): String{
            try {
                val ALPHA_NUMERIC = ('A'..'Z') + ('a'..'z')
                return "[\"" + List(leght) { Random.nextInt(0, ALPHA_NUMERIC.size) }
                    .map { ALPHA_NUMERIC[it] }
                    .joinToString(separator = "")
            }catch (e:Exception){
                println(e.message)
                return ""
            }
        }

        //生成随机字符串（以"]结尾）
        fun generateHashStartEnd(leght: Int): String{
            try {
                val ALPHA_NUMERIC = ('A'..'Z') + ('a'..'z')
                return List(leght) { Random.nextInt(0, ALPHA_NUMERIC.size) }
                    .map { ALPHA_NUMERIC[it] }
                    .joinToString(separator = "") + "\"]"
            }catch (e:Exception){
                println(e.message)
                return ""
            }
        }


        //修改网络请求参数值（随机前3后2）
        fun readAndChangeFileContent(strFilePath: String) {
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
                        var replace = startReplaceWith.replace(line!!, generateStr("\"",3))
                        var stringBuilder = StringBuilder(replace)
                        if (replace.contains(endReplaceWith)){
                            stringBuilder.insert(replace.length-1,GenerateStr.generateChar()).insert(replace.length-1,GenerateStr.generateChar())
                        }
                        tempStream.write(stringBuilder.toString())
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

        //修改网络请求参数值（随机前3后2）hashMap中的key修改
        fun readAndChangeHashContent(strFilePath: String) {
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
                        val replace = hashMapEnd.replace(hashMapStart.replace(line!!, generateHashStartStr(3)),generateHashStartEnd(2))
                        tempStream.write(replace)
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

        //修改字符资源中的内容（随机增加前x位）
        fun ChangeFileContentFont4(strFilePath: String) {
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
                        val replace = strRegex.replace(line!!, GenerateStr.stringsValue("\" >",4))
                        tempStream.write(replace)
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

        //修改字符资源中的内容（随机增加后x位）
//        fun ChangeFileContentBack(strFilePath: String) {
//            try {
//                val file = File(strFilePath)
//
//                if (file.isDirectory) {
//                } else {
//
//                    // 内存流, 作为临时流
//                    var tempStream = CharArrayWriter()
//                    var line: String? = null
//                    val buffreader = BufferedReader(InputStreamReader(FileInputStream(file), "UTF-8"))
//                    //分行读取
//                    while (buffreader.readLine().also { line = it } != null) {
////                        println(p.matcher(line).find())
////                        println(endReplaceWith.find(line!!,0))
////                        val replace = endReplaceWith.replace(line!!, generateEndStr(3))
////                        println(replace)
//                        val replace = strRegex.replace(line!!, GenerateStr.stringsValue(4,6))
////                        println(strRegex.find(line!!,0))
//                        tempStream.write(replace)
//                        tempStream.append("\n")
//                    }
//
//                    var out = FileWriter(strFilePath)
//                    tempStream.writeTo(out)
//                    out.flush()
//                    out.close()
//
//                    tempStream.flush()
//                    tempStream.close()
//
//                }
//            } catch (e: FileNotFoundException) {
//                println("The File doesn't not exist.")
//            } catch (e: IOException) {
//                println(e.message!!)
//            }
//        }


        //替换layout中间字符
        fun pipeiLayoutName(dirPath: String){
            try {
                val file = File(dirPath)
                if (file.isDirectory) {
                    val files = file.list()
                    for (path in files) {
                        val f = File(path)
                        println(layoutName.replace(layoutStartName.replace(f.name,"activity"), GenerateStr.layoutName(4,8)))
                    }
                }
            } catch (e: FileNotFoundException) {
                println("The File doesn't not exist.")
            } catch (e: IOException) {
                println(e.message!!)
            }
        }
    }

}