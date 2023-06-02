public const val  errorValue = "error"
fun init() {
    HAL.init()
    KBD.init()
    LCD.init()
    DoorMechanism.init()
}


fun accessMode() {
    init()
    login()
}

fun login() {
    var insertedUIN: String
    var insertedPIN: String = ""
    do {
        insertedUIN = TUI.readUIN("Insert UIN")
        if (insertedUIN != errorValue) insertedPIN = TUI.readPIN("Insert PIN")
    } while (TUI.auth(insertedUIN, insertedPIN))
    TUI.writeMessage(insertedUIN)
    DoorMechanism.open(1)
    while(!DoorMechanism.finished()){}
    DoorMechanism.close(1)
}

fun maintenanceMode() {
    init()
    
}