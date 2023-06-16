object LCD { // Writes to the LCD using the 4-bit interface.

    private const val COLS = 16 // Dimensions of the display.
    private const val LINES = 2

    private const val RS = 0b1 //
    private const val ENABLE = 0b10 //
    private const val DATA_PARALLEL = 0b1111000 //120

    private const val T_AS = 40 // Time Intervals
    private const val T_W = 230
    private const val T_C = 500

    // Writes a command/data nibble to the LCD in parallel
    private fun writeNibbleParallel(rs: Boolean, data: Int){
        if (rs) HAL.setBits(RS) else HAL.clrBits(RS)        //RS
        HAL.writeBits(DATA_PARALLEL,data shl 3)               //Data
        Thread.sleep(0,T_AS)
        HAL.setBits(ENABLE)                          //Enable 1
        Thread.sleep(0,T_W)
        HAL.clrBits(ENABLE)                    //Enable 0
        Thread.sleep(0,T_C- T_W)
    }



    // Writes a command/data nibble to the LCD in series
    fun writeNibbleSerial(rs: Boolean, data: Int) {
        val merge = if(rs) (data shl 1) + 1 else data shl 1
        SerialEmitter.send(SerialEmitter.Destination.LCD,merge)
        Thread.sleep(5)
    }

    // Writes a command/data nibble to the LCD
    private fun writeNibble(rs: Boolean, data: Int) {
        writeNibbleSerial(rs,data)
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


    // Sends the initialization sequence for 4-bit communication.
    fun init(){
        Thread.sleep(20)
        writeNibble(false,3)
        Thread.sleep(5)
        writeNibble(false,3)
        Thread.sleep(0,100000)
        writeNibble(false,3)
        writeNibble(false,2)
        writeNibble(false,2)
        writeNibble(false,12)
        writeNibble(false,0)
        writeNibble(false,8)
        writeNibble(false,0)
        writeNibble(false,1)
        writeNibble(false,0)
        writeNibble(false,6)
        writeCMD(0b00001111 )
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
        if(line in 0 until LINES && column in 0 until COLS) {
            var address: Int = when (line) {
                0 -> 0x00
                else -> 0x40
            }
            address += column
            writeCMD(address + 0x80)  //DB7 = 1 = 128 = 0x80
        }
    }

    // Sends a command to clear the screen and position the cursor at (0,0)
    fun clear() {
        writeCMD(1)
    }

}
