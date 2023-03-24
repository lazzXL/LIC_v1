import isel.leic.UsbPort

var output = 0

fun main (args: Array<String>) {
        init()

            //val value = UsbPort.read()
            //UsbPort.write(9)
            //println(value)
        setBits(9)
        clrBits(1)



    }

fun init(){
    UsbPort.write(0)
}

fun isBit(mask: Int): Boolean{
    val read = UsbPort.read()
    val kappa = mask.and(UsbPort.read())
    return kappa != 0
}


// Retorna os valores dos bits representados por mask presentes no UsbPort
fun readBits(mask: Int): Int{
    val read = UsbPort.read()
    return 1
}

fun writeBits(mask: Int, value: Int) {

}

fun setBits(mask: Int){
    val write = mask.or(output)
    UsbPort.write(write)
    output = write
}

// Coloca os bits representados por mask no valor lógico ‘0’
fun clrBits(mask: Int) {
    val write = (mask.xor(output))
    UsbPort.write(write)
    output = write
}

