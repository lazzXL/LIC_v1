import java.io.BufferedReader
import java.io.FileReader
import java.io.PrintWriter
import java.time.LocalDateTime

data class LogInfo (val UIN:Int, val time:LocalDateTime)
data class UserInfo(val UIN:String, var PIN:String, val name:String, var message:String)

object FileAccess {


    private fun createReader(fileName: String): BufferedReader {
        return BufferedReader(FileReader(fileName))
    }

    private fun createWriter(fileName: String): PrintWriter {
        return PrintWriter(fileName)
    }

    fun readFile(fileName: String): Array<UserInfo?> {
        val read = createReader(fileName)
        val userArray = arrayOfNulls<UserInfo>(1000)
        var line:String?
        line = read.readLine()
        while(line!=null){
            val split = line.split(";")
            userArray[split[0].toInt()] = UserInfo(split[0],split[1],split[2],if(split.size==4)split[3] else "")
            line = read.readLine()
        }
        read.close()
        return userArray
    }
    fun writeFile(fileName: String, users:Array<UserInfo?>){
        val write = createWriter(fileName)
        val notNull = users.filterNotNull()
        for(i in notNull.indices){
            write.println("${users[i]!!.UIN};${users[i]!!.PIN};${users[i]!!.name};${users[i]!!.message}")
        }
        write.close()
    }

    fun writeFileLog(logs : List<LogInfo>){
        val write = createWriter("Logs.txt")
        for(i in logs){
            write.println("User ${i.UIN} entered at ${i.time}")
        }
        write.close()
    }

}

