object SerialEmitter { // Envia tramas para os diferentes módulos Serial Receiver.

    enum class Destination {LCD, DOOR}

    // Inicia a classe
    fun init() = 0

    // Envia uma trama para o SerialReceiver identificado o destino em addr e os bits de dados em ‘data’.
    fun send(addr: Destination, data: Int) = 0

    // Retorna true se o canal série estiver ocupado
    fun isBusy(): Boolean = true

}