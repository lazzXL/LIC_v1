import java.io.BufferedReader
import java.io.FileReader
import java.io.PrintWriter
/*
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
fun readFile(filename :String): MutableList<Userr>{
    val read = createReader(filename)
    val list = mutableListOf<Userr>()
    var line:String?
    line = read.readLine()
    while(line != null){
        val split = line.split(";")
        list += Userr(split[0],split[1],split[2],if(split.size==4)split[3] else "")
        line = read.readLine()
    }
    read.close()
    return list
}

fun writeFile(fileName: String,list:MutableList<Userr>){
    val write = createWriter(fileName)
    for(i in list.indices){
        write.println("${list[i].UIN};${list[i].PIN};${list[i].name};${list[i].message}")
    }
    write.close()
}
*/

/** Usage Example
 *  File on the project Directory:
 *  Copy Input File to OutputFile.
 * **/
fun main( ){
    val br=createReader("USERS.txt")
    val pw=createWriter("USERS.txt")
    var line:String?
    line=br.readLine()
    while(line!=null){
        // val words = str.split(Regex("\\W"))
        val words = line.split(";")
        pw.println(words)
        line=br.readLine()
    }
    pw.close()
}

 */
