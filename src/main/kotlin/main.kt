fun main(){
    SerialEmitter.init()
    DoorMechanism.init()

    //LCD TEST
    //LCD.init()
    //Thread.sleep(2000)
    //LCD.clear()
    //Thread.sleep(1000)
    //LCD.cursor(1,12)
    //Thread.sleep(1000)
    //LCD.clear()

/*
    //KEYBOARD TEST
    KBD.init()
    Thread.sleep(10000)
    while(true){
        println(KBD.getKey())
        Thread.sleep(500)
    }*/

    //DOOR TEST
    DoorMechanism.open(1)
    while(!DoorMechanism.finished()){}
    DoorMechanism.close(1)


}

