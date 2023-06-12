public const val  errorValue = "error"
const val CANCEL = "CANCEL"
const val maintenance = false
fun init() {
    HAL.init()
    KBD.init()
    SerialEmitter.init()
    LCD.init()
    DoorMechanism.init()
    //DoorMechanism.close(1)
    //while(!DoorMechanism.finished()){}

}


fun accessMode() {
    init()
    login()
}

fun login() {
    while (!maintenance) {
        var insertedUIN: String
        var insertedPIN: String = ""
        do {
            insertedUIN = TUI.readUIN("Insert UIN:")
            if (insertedUIN != CANCEL) insertedPIN = TUI.readPIN("Insert PIN:")
        } while (insertedUIN == CANCEL || insertedPIN == CANCEL || !checkUser(insertedUIN.toInt(), insertedPIN))
        val info = getUser(insertedUIN.toInt())
        LCD.writeString("${info!!.name}")
        LCD.cursor(1,0)
        LCD.writeString("${info.message}")
        DoorMechanism.open(1)
        val afterKey = KBD.waitKey(5000)
        if (afterKey == '#') println("novo pin")
        else if(afterKey == '*') addUserMessage(insertedUIN.toInt(),"")
        while (!DoorMechanism.finished()) {
        }
        DoorMechanism.close(1)
        while (!DoorMechanism.finished()) {
        }
    }
}

fun maintenanceMode() {
    init()
    
}