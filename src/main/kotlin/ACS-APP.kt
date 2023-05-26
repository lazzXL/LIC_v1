fun init() {
    HAL.init()
    KBD.init()
    LCD.init()
    DoorMechanism.init()
}
fun accessMode() {
    init()


}
fun login() {
    do {
        val insertedUIN = TUI.readUIN("Insert UIN")
        val insertedPIN = TUI.readPIN("Insert PIN")
    } while (!TUI.auth(insertedUIN, insertedPIN))
}

fun maintenanceMode() {
    init()
    
}