import isel.leic.UsbPort

object HAL {
    private var output = 0

    fun init() {
        UsbPort.write(0)
    }

    // Retorna true se o bit tiver o valor lógico ‘1’
    fun isBit(mask: Int): Boolean = mask.and(UsbPort.read()) != 0

    // Retorna os valores dos bits representados por mask
    fun readBits(mask: Int): Int = mask.and(UsbPort.read())

    // Coloca os bits representados por mask no valor de value
    fun writeBits(mask: Int, value: Int) {
        val write = (mask and value) or (output and mask.inv())
        UsbPort.write(write)
        output = write
    }

    // Coloca os bits representados por mask no valor lógico ‘1’
    fun setBits(mask: Int) {
        val write = mask.or(output)
        UsbPort.write(write)
        output = write
    }

    // Coloca os bits representados por mask no valor lógico ‘0’
    fun clrBits(mask: Int) {
        val clearMask = mask.inv()
        val write = clearMask.and(output)
        UsbPort.write(write)
        output = write
    }

}