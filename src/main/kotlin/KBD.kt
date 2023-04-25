object KBD{ // Ler teclas. Métodos retornam ‘0’..’9’,’#’,’*’ ou NONE.

    private const val NONE = 0.toChar()
    private const val K_ack = 0b10000000
    private const val K_val = 0b00010000
    private const val K_03  = 0b00001111
    private var initialized = false


    // Inicia a classe
    fun init(){
        if (!initialized) {
            HAL.clrBits(1)
            HAL.init()
            initialized = true
        }
    }

    // Retorna de imediato a tecla premida ou NONE se não há tecla premida.
    private fun getKey(): Char {
        if(HAL.isBit(K_val)) {
            // Read the bits from the keypad
            val bits = HAL.readBits(K_03)
            // Check which key is pressed
            val key = when (bits) {
                0 -> '1'
                4 -> '2'
                8 -> '3'
                1 -> '4'
                5 -> '5'
                9 -> '6'
                2 -> '7'
                6 -> '8'
                10 -> '9'
                3 -> '*'
                7 -> '0'
                11 -> '#'
                else -> NONE
            }
            HAL.setBits(K_ack)
            while (HAL.isBit(K_val)) {
                Thread.sleep(50)
            }
            HAL.clrBits(K_ack)
            return key
        }
        return NONE
    }

    // Retorna a tecla premida, caso ocorra antes do ‘timeout’ (representado em milissegundos), ou
    //NONE caso contrário.
    fun waitKey(timeout: Long): Char {
        val startTime = System.currentTimeMillis()
        while (System.currentTimeMillis()-startTime < timeout) {
            val key = getKey()
            if (key != NONE) {
                return key
            }
            Thread.sleep(50)
        }
        return NONE
    }

}