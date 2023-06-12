import java.io.BufferedReader
import java.io.FileReader
import java.io.PrintWriter

object FileAccess {

    data class Userr(val UIN:String, var PIN:String, val name:String, var message:String)

    fun createReader(fileName: String): BufferedReader {
        return BufferedReader(FileReader(fileName))
    }

    fun createWriter(fileName: String?): PrintWriter {
        return PrintWriter(fileName)
    }

    fun readFile(fileName: String): Array<Userr?> {
        val read = createReader(fileName)
        val userArray = arrayOfNulls<Userr>(1000)
        var line:String?
        line = read.readLine()
        while(line!=null){
            val split = line.split(";")
            userArray[split[0].toInt()] = Userr(split[0],split[1],split[2],if(split.size==4)split[3] else "")
            line = read.readLine()
        }
        read.close()
        return userArray
    }
    fun writeFile(fileName: String, users:Array<Userr?>){
        val write = createWriter(fileName)
        val notNull = users.filterNotNull()
        for(i in notNull.indices){
            write.println("${users[i]!!.UIN};${users[i]!!.PIN};${users[i]!!.name};${users[i]!!.message}")
        }
        write.close()
    }
/*
    fun File.exists() : Boolean  = this.exists()

    fun openFile(name: String) = File(name)

    fun createFile(fileName: String) = File(fileName).createNewFile()

    fun removeFile(file: File) = file.delete()

    fun editFile(file: File, line: Int, newValue: String) {
        var fileArray = readFile(file)
        val fileName = file.name
        var fileString = ""
        removeFile(file)
        fileArray += newValue
        createFile(fileName)
        val newFile = openFile(fileName)
        for (i in fileArray.indices) fileString += "${fileArray[i]}\n"
        writeFile(newFile, fileString)
    }

    fun writeFile (file: File, input: String) = file.writeText("$input" )

    fun readFile (file: File) : Array<String> {
        var fileArray = emptyArray<String>()
        file.forEachLine { fileArray += it }
        return fileArray
    }
    */

}

