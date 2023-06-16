fun main(){
   /*
    SerialEmitter.init()
    DoorMechanism.init()

    //LCD TEST

    LCD.init()
    Thread.sleep(2000)
    LCD.clear()
    Thread.sleep(1000)
    LCD.writeString("teste")
    LCD.cursor(1,12)
    Thread.sleep(1000)
    LCD.writeString("haha")
    Thread.sleep(1000)
    LCD.clear()
*/
/*
    //KEYBOARD TEST
    KBD.init()
    Thread.sleep(10000)
    while(true){
        println(KBD.getKey())
        Thread.sleep(500)
    }*/
/*
    //DOOR TEST
    DoorMechanism.open(1)
    while(!DoorMechanism.finished()){}
    DoorMechanism.close(1)
    */
/*
    usersInit()
    removeUser(3)
    newUser("User3", 1001)
    newUser("User", 5555)
    addUserMessage(5,"HAHA")
    usersWrite()

*/


/*
    //println(getUser(2))
    //addUserMessage(2,"LOL")
    //editPin(2,"0000")
    //accessMode()
    Log.init()
    Thread.sleep(5000)
    Log.newLog(123)
    Thread.sleep(5000)
    Log.newLog(199)
    Thread.sleep(5000)
    Log.writeLog()*/
    init()
    while(true) {
        while (!HAL.isBit(maintenance)) {
            Log.init()
            accessMode()
            Log.writeLog()
        }
        LCD.clear()
        LCD.writeString("Out Of Service")
        while (HAL.isBit(maintenance)) {
            maintenanceMode()
        }
    }
}

