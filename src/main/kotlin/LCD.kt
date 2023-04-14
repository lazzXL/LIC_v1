object LCD { // Writes to the LCD using the 4-bit interface.

    //MUDAR HARDWARE SIMUL

    private const val COLS = 16 // Dimensions of the display.
    private const val LINES = 2
    private const val T_AS = 40 // Time Intervals
    private const val T_W = 230
    private const val T_C = 500

    // Writes a command/data nibble to the LCD in parallel
    private fun writeNibbleParallel(rs: Boolean, data: Int){
        if (rs) HAL.setBits(2) else HAL.clrBits(2)        //RS
        HAL.writeBits(120,data shl 3)               //Data
        Thread.sleep(0,T_AS)
        HAL.setBits(4)                          //Enable 1
        Thread.sleep(0,T_W)
        HAL.clrBits(4)                    //Enable 0
        Thread.sleep(0,T_C- T_W)
    }

    // Writes a command/data nibble to the LCD in series
    //private fun writeNibbleSerial(rs: Boolean, data: Int) {
    //    return 1
    //}

    // Writes a command/data nibble to the LCD
    private fun writeNibble(rs: Boolean, data: Int) {
        writeNibbleParallel(rs,data)
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


    // Sends the initialization sequence for 4-bit communication. //writeNibble e writeCMD
    fun init(){
        Thread.sleep(20)
        writeNibble(false,3)
        Thread.sleep(5)
        writeNibble(false,3)
        Thread.sleep(0,100000)
        writeNibble(false,3)
        writeNibble(false,2)
    }


    // Writes a character at the current position.
    private fun writeChar(c: Char){
        writeDATA(c.code)
    }

    // Writes a string at the current position.
    fun writeString(text: String){
        for (c in text) {
            writeChar(c)
        }
    }

    // Sends a command to position the cursor (‘line’:0..LINES-1 , ‘column’:0..COLS-1)
    fun cursor(line: Int, column: Int){
        if(line in 0 until LINES-1 && column in 0 until COLS-1) {
            var address: Int = when (line) {
                0 -> 0x00
                else -> 0x40
            }
            address += column
            writeCMD(address + 128)  //DB7 = 1 = 128
        }
    }

    // Sends a command to clear the screen and position the cursor at (0,0)
    fun clear() {
        writeCMD(1)
    }

    fun main(){
        init()
        clear()
        writeString("TEST")
        cursor(1,7)
    }
}


