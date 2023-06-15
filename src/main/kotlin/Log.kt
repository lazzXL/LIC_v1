import java.time.LocalDateTime

object Log {

    private var logs = listOf<LogInfo>()


    fun newLog(UIN: Int) {
        val log = LogInfo(UIN, LocalDateTime.now())
        logs = logs + log
    }

    fun writeLog(){
        FileAccess.writeFileLog(logs)
    }

}