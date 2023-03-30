import isel.leic.UsbPort

object HAL {
    private var output = 0

    //fun main() {
    //init()
    //isBit(2)
    //readBits(5)
    //setBits(15)
    //clrBits(1)
    //writeBits(9, 2)
    //}

    fun init() {
        UsbPort.write(0)
    }

    // Retorna true se o bit tiver o valor lógico ‘1’
    fun isBit(mask: Int): Boolean = mask.and(UsbPort.read()) != 0
// +1 Bit


    // Retorna os valores dos bits representados por mask
    fun readBits(mask: Int): Int = mask.and(UsbPort.read())
// Nice


    // Coloca os bits representados por mask no valor de value
    fun writeBits(mask: Int, value: Int) {
        val write = (mask and value) or (output and mask.inv())
        UsbPort.write(write)
        output = write
    }
//Nice


    // Coloca os bits representados por mask no valor lógico ‘1’
    fun setBits(mask: Int) {
        val write = mask.or(output)
        UsbPort.write(write)
        output = write
    }
//Nice


    // Coloca os bits representados por mask no valor lógico ‘0’
    fun clrBits(mask: Int) {
        val clearMask = mask.inv()
        val write = clearMask.and(output)
        UsbPort.write(write)
        output = write
    }
//Nice

    /*
fun clrBitsOld(mask: Int) {
    val write = mask.xor(output)
    UsbPort.write(write)
    output = write
}
// Coloca os bits que estão 0 em 1



fun writeBitsOld(mask: Int, value: Int) { // ??????
    val write = mask.and(value)
    UsbPort.write(write)
    output = write
}


fun writeBits2(mask: Int, value: Int) {
    val write = mask.xor(output).and(value)
    UsbPort.write(write)
    output = output.xor(write)
}
*/

}