fun main(){
    KBD.init()
    while(true){
        Thread.sleep(1000)
        println(KBD.waitKey(5000))
    }
}

