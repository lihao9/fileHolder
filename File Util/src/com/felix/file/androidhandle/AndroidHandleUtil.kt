package com.felix.file.androidhandle

import com.felix.file.content.FileUtil
import com.felix.file.content.GenerateStr
import com.felix.file.writeContent
import java.io.File
import java.util.ArrayList

object AndroidHandleUtil {



    fun changeResource(){
        //id
        val layoutDirContent = DirUtils.readDirContent(AndroidResourceUtils.ALL_LAYOUT_PATH)
        //anim
        val animDirContent = DirUtils.readDirContent(AndroidResourceUtils.RES_ANIM)
        //drawable
        val drawableDirContent = DirUtils.readDirContent(AndroidResourceUtils.RES_DRAWABLE)
        //mipmap
        val mipmapDirContent = DirUtils.readDirContent(AndroidResourceUtils.RES_MIPMAPX_RESOUR)
        //string
        val stringDirContent = DirUtils.readDirContent(AndroidResourceUtils.RES_STRING)
//        val allDirContent = DirUtils.readDirContent(AndroidResourceUtils.ALL_PATH)
//        val resourceDirContent = DirUtils.readDirContent(AndroidResourceUtils.ALL_REQOURCE_PATH)
        val codeDirContent = DirUtils.readDirContent(AndroidResourceUtils.ALL_PATH)

        val oldId = AndroidResourceUtils.getId(layoutDirContent)
        val oldStringName = AndroidResourceUtils.getName(stringDirContent)
        val oldLayoutName = FileHandle.getFileName(layoutDirContent)
        val oldAnimName = FileHandle.getFileName(animDirContent)
        val oldDrawableName = FileHandle.getFileName(drawableDirContent)
        val oldMipmapName = FileHandle.getFileName(mipmapDirContent)

        val newId = CreateStringUtils.createStringIds(oldId.size)
        val newAnimName = CreateStringUtils.createFileNames(oldAnimName)
        val newStringName = CreateStringUtils.createFileNames(oldStringName)
        val newDrawableName = CreateStringUtils.createFileNames(oldDrawableName)
        val newMipmapName = CreateStringUtils.createFileNames(oldMipmapName)
        val newLayoutName = CreateStringUtils.createFileNames(oldLayoutName)


        //替换Id
        oldId.map { id ->
            val codeIdResource = Regex("""\b$id\b""")
            codeDirContent.map { tempKotMap ->
                codeDirContent[tempKotMap.key] = tempKotMap.value.replace(codeIdResource,newId[oldId.indexOf(id)])
            }
        }

        //替换anim文件
        oldAnimName.map { name ->
            val trueName = name.split(".")[0]
            val animResource = Regex("""\b$trueName\b""")
            codeDirContent.map { tempKotMap ->
                codeDirContent[tempKotMap.key] = tempKotMap.value.replace(animResource,newAnimName[oldAnimName.indexOf(name)])
            }
        }

        //替换string名称
        oldStringName.map { name ->
            val stringResource = Regex("""\b$name\b""")
            codeDirContent.map { tempKotMap ->
                codeDirContent[tempKotMap.key] = tempKotMap.value.replace(stringResource,newStringName[oldStringName.indexOf(name)])
            }
        }

        //替换drawable文件
        oldDrawableName.map { name ->
            val trueName = name.split(".")[0]
            val drawableResource = Regex("""\b$trueName\b""")
            codeDirContent.map { tempKotMap ->
                codeDirContent[tempKotMap.key] = tempKotMap.value.replace(drawableResource,newDrawableName[oldDrawableName.indexOf(name)])
            }
        }

        //替换mipmap文件
        oldMipmapName.map { name ->
            val trueName = name.split(".")[0]
            val idResource = Regex("""\b$trueName\b""")
            codeDirContent.map { tempKotMap ->
                codeDirContent[tempKotMap.key] = tempKotMap.value.replace(idResource,newMipmapName[oldMipmapName.indexOf(name)])
            }
        }

        println("开始写入修改后的文件")
        FileHandle.writeContent(codeDirContent)

        println("开始修改Anim文件名称")
        //修改anim文件名称
        val currentAnimFiles = DirUtils.readDirContent(AndroidResourceUtils.RES_ANIM_RESULT)
        currentAnimFiles.map {
            oldAnimName.map { name ->
                if (it.key.contains(name)){
                    val path = it.key
                    updateFileName(path,newAnimName[oldAnimName.indexOf(name)])
                }
            }
        }

        println("开始修改Drawable文件名称")
        val currentDrawableFiles = DirUtils.readDirContent(AndroidResourceUtils.RES_DRAWABLE_RESULT)
        currentDrawableFiles.map {
            oldDrawableName.map { name ->
                if (it.key.contains(name)){
                    val path = it.key
                    updateFileName(path,newDrawableName[oldDrawableName.indexOf(name)])
                }
            }
        }

        println("开始修改mipmap文件名称")
        val currentMipmapXFiles = DirUtils.readDirContent(AndroidResourceUtils.RES_MIPMAPX_RESULT)
        val currentMipmapXXFiles = DirUtils.readDirContent(AndroidResourceUtils.RES_MIPMAPXX_RESULT)
        val currentMipmapXXXFiles = DirUtils.readDirContent(AndroidResourceUtils.RES_MIPMAPXXX_RESULT)
        currentMipmapXFiles.map {
            oldMipmapName.map { name ->
                if (it.key.contains(name)){
                    val path = it.key
                    updateFileName(path,newMipmapName[oldMipmapName.indexOf(name)])
                }
            }
        }

        currentMipmapXXFiles.map {
            oldMipmapName.map { name ->
                if (it.key.contains(name)){
                    val path = it.key
                    updateFileName(path,newMipmapName[oldMipmapName.indexOf(name)])
                }
            }
        }

        currentMipmapXXXFiles.map {
            oldMipmapName.map { name ->
                if (it.key.contains(name)){
                    val path = it.key
                    updateFileName(path,newMipmapName[oldMipmapName.indexOf(name)])
                }
            }
        }


    }


    fun changeStringName(){

        val stringDirContent = DirUtils.readDirContent("F:\\untitled\\sourceFile\\strings.xml")
        val oldStringName = AndroidResourceUtils.getName(stringDirContent)
        val newStringName = CreateStringUtils.createFileNames(oldStringName)
        val codeDirContent = DirUtils.readDirContent("F:\\untitled\\sourceFile\\strings.xml")
        //替换string名称
        oldStringName.map { name ->
            val stringResource = Regex("""\b$name\b""")
            codeDirContent.map { tempKotMap ->
                codeDirContent[tempKotMap.key] = tempKotMap.value.replace(stringResource,newStringName[oldStringName.indexOf(name)])
            }
        }
        FileHandle.writeContent(codeDirContent)


    }

    //修改ID信息
    fun changeId(xmlDirPath: String,codeDirPath: String){

        val xmlDirContent = DirUtils.readDirContent(xmlDirPath)
        val codeDirContent = DirUtils.readDirContent(codeDirPath)

        val oldId = AndroidResourceUtils.getId(xmlDirContent)
        val newId = CreateStringUtils.createStringIds(oldId.size)

        oldId.map { id ->
            val idResource = Regex("""\b$id\b""")
            xmlDirContent.map { tempXmlMap ->
                xmlDirContent[tempXmlMap.key] = idResource.replace(tempXmlMap.value, newId[oldId.indexOf(id)])
            }

            codeDirContent.map { tempKotMap ->
                codeDirContent[tempKotMap.key] = tempKotMap.value.replace(idResource,newId[oldId.indexOf(id)])
            }
        }

        FileHandle.writeContent(xmlDirContent)
        FileHandle.writeContent(codeDirContent)
    }

    //修改anim文件名称
    fun changeAnimName(animDirPath: String,changePath: String){

        val animDirContent = DirUtils.readDirContent(animDirPath)
        val codeDirContent = DirUtils.readDirContent(changePath)

        val oldName = FileHandle.getFileName(animDirContent)
        val newName = CreateStringUtils.createFileNames(oldName)

        oldName.map { name ->
            val trueName = name.split(".")[0]
            val idResource = Regex("""\b$trueName\b""")
            codeDirContent.map { tempKotMap ->
                codeDirContent[tempKotMap.key] = tempKotMap.value.replace(idResource,newName[oldName.indexOf(name)])
            }
        }

        FileHandle.writeContent(codeDirContent)
        //修改文件名称

        val readDirContent = DirUtils.readDirContent(AndroidResourceUtils.RES_ANIM_RESULT)
        readDirContent.map {
            oldName.map { name ->
                if (it.key.contains(name)){
                    val path = it.key
                    updateFileName(path,newName[oldName.indexOf(name)])
                }
            }
        }
    }

    //修改drawable文件名称
    fun changeDrawableName(drawableDirPath: String,changePath: String){

        val drawableDirContent = DirUtils.readDirContent(drawableDirPath)
        val codeDirContent = DirUtils.readDirContent(changePath)

        val oldName = FileHandle.getFileName(drawableDirContent)
        val newName = CreateStringUtils.createFileNames(oldName)

        oldName.map { name ->
            val trueName = name.split(".")[0]
            val idResource = Regex("""\b$trueName\b""")
            codeDirContent.map { tempKotMap ->
                codeDirContent[tempKotMap.key] = tempKotMap.value.replace(idResource,newName[oldName.indexOf(name)])
            }
        }

        FileHandle.writeContent(codeDirContent)

        val readDirContent = DirUtils.readDirContent(AndroidResourceUtils.RES_DRAWABLE_RESULT)
        readDirContent.map {
            oldName.map { name ->
                if (it.key.contains(name)){
                    val path = it.key
                    updateFileName(path,newName[oldName.indexOf(name)])
                }
            }
        }
    }

    //修改mipmap文件名称
    fun changeMipmapName(mipmap1DirPath: String,changePath: String){

        val xmlDirContent = DirUtils.readDirContent(mipmap1DirPath)
        val codeDirContent = DirUtils.readDirContent(changePath)

        val oldName = FileHandle.getFileName(xmlDirContent)
        val newName = CreateStringUtils.createFileNames(oldName)

        oldName.map { name ->
            val trueName = name.split(".")[0]
            val idResource = Regex("""\b$trueName\b""")
            codeDirContent.map { tempKotMap ->
                codeDirContent[tempKotMap.key] = tempKotMap.value.replace(idResource,newName[oldName.indexOf(name)])
            }
        }

        FileHandle.writeContent(codeDirContent)

        val readDirContent1 = DirUtils.readDirContent(AndroidResourceUtils.RES_MIPMAPX_RESULT)
        val readDirContent2 = DirUtils.readDirContent(AndroidResourceUtils.RES_MIPMAPXX_RESULT)
        val readDirContent3 = DirUtils.readDirContent(AndroidResourceUtils.RES_MIPMAPXXX_RESULT)
        readDirContent1.map {
            oldName.map { name ->
                if (it.key.contains(name)){
                    val path = it.key
                    updateFileName(path,newName[oldName.indexOf(name)])
                }
            }
        }

        readDirContent2.map {
            oldName.map { name ->
                if (it.key.contains(name)){
                    val path = it.key
                    updateFileName(path,newName[oldName.indexOf(name)])
                }
            }
        }

        readDirContent3.map {
            oldName.map { name ->
                if (it.key.contains(name)){
                    val path = it.key
                    updateFileName(path,newName[oldName.indexOf(name)])
                }
            }
        }
    }

    fun updateFileName(path: String, newName: String) {
        var oldFile = File(path)
        if (!oldFile.exists()) { // 判断原文件是否存在（防止文件名冲突）
            return
        }
        var newFileName = newName.trim()
        if (newFileName.isNullOrBlank()) // 文件名不能为空
            return
        var newFilePath = ""
        if (oldFile.isDirectory) { // 判断是否为文件夹
            newFilePath = path.substring(0, path.lastIndexOf("\\")) + "\\" + newFileName
        } else {
            newFilePath = path.substring(0, path.lastIndexOf("\\")) + "\\" + newFileName + path.substring(path.lastIndexOf("."))
        }
        var newFile = File(newFilePath)
        try {
            oldFile.renameTo(newFile) // 修改文件名
        } catch (err:Exception ) {
            err.printStackTrace()
            return
        }
    }

    //修改values下color资源
    fun changeValueAtts(xmlDirPath: String,codeDirPath: String){

        val xmlDirContent = DirUtils.readDirContent(xmlDirPath)
        val codeDirContent = DirUtils.readDirContent(codeDirPath)

        val oldId = AndroidResourceUtils.getId(xmlDirContent)
        val newId = CreateStringUtils.createStringIds(oldId.size)

        oldId.map { id ->
            val idResource = Regex("""\b$id\b""")
            xmlDirContent.map { tempXmlMap ->
                xmlDirContent[tempXmlMap.key] = idResource.replace(tempXmlMap.value, newId[oldId.indexOf(id)])
            }

            codeDirContent.map { tempKotMap ->
                codeDirContent[tempKotMap.key] = tempKotMap.value.replace(idResource,newId[oldId.indexOf(id)])
            }
        }

        FileHandle.writeContent(xmlDirContent)
        FileHandle.writeContent(codeDirContent)
    }

    //修改values下color资源
    fun changeValueColor(xmlDirPath: String,codeDirPath: String){

        val xmlDirContent = DirUtils.readDirContent(xmlDirPath)
        val codeDirContent = DirUtils.readDirContent(codeDirPath)

        val oldId = AndroidResourceUtils.getId(xmlDirContent)
        val newId = CreateStringUtils.createStringIds(oldId.size)

        oldId.map { id ->
            val idResource = Regex("""\b$id\b""")
            xmlDirContent.map { tempXmlMap ->
                xmlDirContent[tempXmlMap.key] = idResource.replace(tempXmlMap.value, newId[oldId.indexOf(id)])
            }

            codeDirContent.map { tempKotMap ->
                codeDirContent[tempKotMap.key] = tempKotMap.value.replace(idResource,newId[oldId.indexOf(id)])
            }
        }

        FileHandle.writeContent(xmlDirContent)
        FileHandle.writeContent(codeDirContent)
    }

    //修改values下string资源
    fun changeValueString(xmlDirPath: String,codeDirPath: String){

        val xmlDirContent = DirUtils.readDirContent(xmlDirPath)
        val codeDirContent = DirUtils.readDirContent(codeDirPath)

        val oldId = AndroidResourceUtils.getId(xmlDirContent)
        val newId = CreateStringUtils.createStringIds(oldId.size)

        oldId.map { id ->
            val idResource = Regex("""\b$id\b""")
            xmlDirContent.map { tempXmlMap ->
                xmlDirContent[tempXmlMap.key] = idResource.replace(tempXmlMap.value, newId[oldId.indexOf(id)])
            }

            codeDirContent.map { tempKotMap ->
                codeDirContent[tempKotMap.key] = tempKotMap.value.replace(idResource,newId[oldId.indexOf(id)])
            }
        }

        FileHandle.writeContent(xmlDirContent)
        FileHandle.writeContent(codeDirContent)
    }

    //修改values下string资源
    fun changeValueStyle(xmlDirPath: String,codeDirPath: String){

        val xmlDirContent = DirUtils.readDirContent(xmlDirPath)
        val codeDirContent = DirUtils.readDirContent(codeDirPath)

        val oldId = AndroidResourceUtils.getId(xmlDirContent)
        val newId = CreateStringUtils.createStringIds(oldId.size)

        oldId.map { id ->
            val idResource = Regex("""\b$id\b""")
            xmlDirContent.map { tempXmlMap ->
                xmlDirContent[tempXmlMap.key] = idResource.replace(tempXmlMap.value, newId[oldId.indexOf(id)])
            }

            codeDirContent.map { tempKotMap ->
                codeDirContent[tempKotMap.key] = tempKotMap.value.replace(idResource,newId[oldId.indexOf(id)])
            }
        }

        FileHandle.writeContent(xmlDirContent)
        FileHandle.writeContent(codeDirContent)
    }

    //修改values下string资源
    fun changeValueThemes(xmlDirPath: String,codeDirPath: String){

        val xmlDirContent = DirUtils.readDirContent(xmlDirPath)
        val codeDirContent = DirUtils.readDirContent(codeDirPath)

        val oldId = AndroidResourceUtils.getId(xmlDirContent)
        val newId = CreateStringUtils.createStringIds(oldId.size)

        oldId.map { id ->
            val idResource = Regex("""\b$id\b""")
            xmlDirContent.map { tempXmlMap ->
                xmlDirContent[tempXmlMap.key] = idResource.replace(tempXmlMap.value, newId[oldId.indexOf(id)])
            }

            codeDirContent.map { tempKotMap ->
                codeDirContent[tempKotMap.key] = tempKotMap.value.replace(idResource,newId[oldId.indexOf(id)])
            }
        }

        FileHandle.writeContent(xmlDirContent)
        FileHandle.writeContent(codeDirContent)
    }
}

//修改请求参数（前3后2）
//    FileUtil.changeParams("F:\\untitled\\sourceFile\\ParamKey.kt")
//    //修改请求链接前3后3
//    FileUtil.changeUrl("C:\\Users\\Administrator\\Desktop\\replaceContent\\IApiRequest.kt")

