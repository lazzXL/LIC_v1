import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Log {

    private var logs = listOf<LogInfo>()
    private var startTime = LocalDateTime.now()
    private var formatter = DateTimeFormatter.ofPattern("hh:mm:ss - dd/MM/yyyy")

    fun init(){
        startTime = LocalDateTime.now()
    }

    fun newLog(UIN: Int) {
        val formattedTimeandDate = LocalDateTime.now().format(formatter)
        val log = LogInfo(UIN, formattedTimeandDate)
        logs = logs + log
    }

    fun writeLog(){
        val endTime = LocalDateTime.now()
        FileAccess.writeFileLog(logs, startTime.format(formatter),endTime.format(formatter))
    }

}