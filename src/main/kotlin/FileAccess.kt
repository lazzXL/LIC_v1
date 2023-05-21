import java.io.File
import java.util.*

object FileAccess {

    fun File.exists() : Boolean  = this.exists()

    fun openFile(name: String) = File(name)

    fun createFile(fileName: String) = File(fileName).createNewFile()

    fun removeFile(file: File) = file.delete()

    fun editFile(file: File, line: Int, newValue: String) {
        var fileArray = readFile(file)
        val fileName = file.name
        removeFile(file)
        fileArray[line] = newValue
        var newFile = openFile(fileName)
        for (i in fileArray.indices) writeFile(newFile, fileArray[i])
    }

    fun writeFile (file: File, input: String) = file.writeText("$input /n")

    fun readFile (file: File) : Array<String> {
            var fileArray = emptyArray<String>()
            file.forEachLine { fileArray += it }
            return fileArray
    }
}