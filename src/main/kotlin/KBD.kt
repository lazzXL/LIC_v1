object KBD{ // Ler teclas. Métodos retornam ‘0’..’9’,’#’,’*’ ou NONE.

    private const val NONE = 0.toChar()
    private var initialized = false

    fun main(){
        init()
        while(true){
            println(waitKey(5000))
        }
    }

    // Inicia a classe
    fun init(){
        if (!initialized) {
            HAL.clrBits(16)
            HAL.init()
            initialized = true
        }
    }

    // Retorna de imediato a tecla premida ou NONE se não há tecla premida.
    private fun getKey(): Char {
        // Read the bits from the keypad
        val bits = HAL.readBits(15)

        // Check which key is pressed
        return when (bits) {
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