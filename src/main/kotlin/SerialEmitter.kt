object SerialEmitter { // Envia tramas para os diferentes módulos Serial Receiver.

    enum class Destination {LCD, DOOR}
    private const val SCLK      = 0b00000010 // Bit 1 Output
    private const val SS_LCD    = 0b00000100 // Bit 2 Output
    private const val DATA_LCD  = 0b00001000 // Bit 3 Output
    private const val SS_DOOR   = 0b00010000 // Bit 4 Output
    private const val DATA_DOOR = 0b00100000 // Bit 5 Output
    private var initialized = false
    private const val busy = 0b00100000 // Bit 5 Input

    // Inicia a classe
    fun init() {
        if (!initialized) {
            HAL.init()
            HAL.setBits(SS_LCD)
            HAL.setBits(SS_DOOR)
            initialized = true
        }
    }

    private fun clock(){
        Thread.sleep(0,5)
        HAL.setBits(SCLK)
        Thread.sleep(0,25)
        HAL.clrBits(SCLK)
        Thread.sleep(0,20)
    }

    // Envia uma trama para o SerialReceiver identificado o destino em addr e os bits de dados em ‘data’.
    fun send(addr: Destination, data: Int) {
        if(addr == Destination.LCD){
            HAL.clrBits(SS_LCD)
            for(bit in 0 .. 4) {
                val bitValue = (data shr bit) and 1
                if(bitValue==1)HAL.setBits(DATA_LCD) else HAL.clrBits((DATA_LCD))
                clock()
            }
            HAL.setBits(SS_LCD)
        } else{
            while(isBusy()){}
            HAL.clrBits(SS_DOOR)
            for(bit in 0 ..4) {
                val bitValue = (data shr bit) and 1
                if(bitValue==1)HAL.setBits(DATA_DOOR) else HAL.clrBits(DATA_DOOR)
                clock()
            }
            HAL.setBits(SS_DOOR)
        }
    }

    // Retorna true se o canal série estiver ocupado
    fun isBusy(): Boolean = HAL.isBit(busy)


}
