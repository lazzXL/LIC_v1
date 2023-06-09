object DoorMechanism { // Controla o estado do mecanismo de abertura da porta.

    private var initialized = false
    private const val OC = 0b1

    // Inicia a classe, estabelecendo os valores iniciais.
    fun init() {
        if (!initialized) {
            SerialEmitter.init()
            initialized = true
        }
    }

    // Envia comando para abrir a porta, com o parâmetro de velocidade
    fun open(velocity: Int) {
        SerialEmitter.send(SerialEmitter.Destination.DOOR,(velocity shl 1) + OC)
    }

    // Envia comando para fechar a porta, com o parâmetro de velocidade
    fun close(velocity: Int) {
        SerialEmitter.send(SerialEmitter.Destination.DOOR, velocity shl 1 )
    }

    // Verifica se o comando anterior está concluído
    fun finished() : Boolean = !SerialEmitter.isBusy()

}