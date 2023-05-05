object SerialEmitter { // Envia tramas para os diferentes módulos Serial Receiver.

    enum class Destination {LCD, DOOR}
    private const val SS = 0b10 //
    private const val SCLK = 0b100 //
    private const val DATA_SERIAL = 0b1 //
    private const val DATA_DOOR = 0b10000 //
    private var initialized = false
    private const val busy = 0b1000

    // Inicia a classe
    fun init() {
        if (!initialized) {
            HAL.init()
            HAL.setBits(SS)

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
            HAL.clrBits(SS)
            for(bit in 4 downTo  0) {
                HAL.writeBits(DATA_SERIAL, (data shr bit) and 1)
                clock()
            }
            HAL.setBits(SS)
        } else{
            if(!isBusy()){
                HAL.clrBits(SS)
                for(bit in 4 downTo  0) {
                    HAL.writeBits(DATA_DOOR, (data shr bit) and 1)
                    clock()
                }
                HAL.setBits(SS)
            }
        }
    }

    // Retorna true se o canal série estiver ocupado
    fun isBusy(): Boolean = HAL.isBit(busy)


}