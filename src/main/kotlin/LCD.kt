import kotlin.concurrent.thread

object LCD { // Writes to the LCD using the 4-bit interface.

    //MUDAR HARDWARE SIMUL

    private const val COLS = 16 // Dimensions of the display.
    private const val LINES = 2
    private const val T_as = 40
    private const val T_w = 230
    private const val T_c = 500

    // Writes a command/data nibble to the LCD in parallel
    private fun writeNibbleParallel(rs: Boolean, data: Int){
        if (rs) HAL.setBits(2) else HAL.clrBits(2)
        HAL.writeBits(120,data shl 3)
        Thread.sleep(0,T_as)
        HAL.setBits(4)                          //Enable 1
        Thread.sleep(0,T_w)
        HAL.clrBits(4)                    //Enable 0
        Thread.sleep(0,T_c- T_w)
    }

    // Writes a command/data nibble to the LCD in series
    //private fun writeNibbleSerial(rs: Boolean, data: Int) {
    //    return 1
    //}

    // Writes a command/data nibble to the LCD // Chama writeNibbleParallel
    private fun writeNibble(rs: Boolean, data: Int) {
        return writeNibbleParallel(rs,data)
    }

    // Writes a command/data byte to the LCD // Envia um byte para LCD, dividir e chama writeNibble
    private fun writeByte(rs: Boolean, data: Int){
        writeNibble(rs,(data shr 4) and 0x0F)
        writeNibble(rs,data and 0x0F)
    }

    // Writes a command to the LCD
    private fun writeCMD(data: Int) = writeByte(false,data)

    // Writes data to the LCD
    private fun writeDATA(data: Int) = writeByte(true,data)

/*
    // Sends the initialization sequence for 4-bit communication.
    fun init() //writeNibble e writeCMD

    // Writes a character at the current position.
    fun write(c: Char) ...

    // Writes a string at the current position.
    fun write(text: String) ...

    // Sends a command to position the cursor (‘line’:0..LINES-1 , ‘column’:0..COLS-1)
    fun cursor(line: Int, column: Int) ...
*/
    // Sends a command to clear the screen and position the cursor at (0,0)
    fun clear() {
        writeCMD(1)
    }
}


