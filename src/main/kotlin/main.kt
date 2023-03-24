import isel.leic.UsbPort

var output = 0

fun main (args: Array<String>) {
    init()

    //val value = UsbPort.read()
    //UsbPort.write(9)
    //println(value)
    setBits(15)
    writeBits(15, 7)
    println(readBits(15))


}

fun init(){
    UsbPort.write(0)
}

fun isBit(mask: Int): Boolean{
    val read = UsbPort.read()
    val kappa = mask.and(UsbPort.read())
    return kappa != 0
}


// Retorna os valores dos bits representados por mask
fun readBits(mask: Int): Int{
    return mask.and(output)
}

// Coloca os bits representados por mask no valor de value
fun writeBits(mask: Int, value: Int) {
    val write = mask.and(value)
    UsbPort.write(write)
    output = write


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