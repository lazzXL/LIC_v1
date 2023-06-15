import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Log {

    private var logs = listOf<LogInfo>()


    fun newLog(UIN: Int) {
        var formatter = DateTimeFormatter.ofPattern("hh:mm - dd/MM/yyyy")
        var formattedTimeandDate = LocalDateTime.now().format(formatter)
        val log = LogInfo(UIN, formattedTimeandDate)
        logs = logs + log
    }

    fun writeLog(){
        FileAccess.writeFileLog(logs)
    }

}