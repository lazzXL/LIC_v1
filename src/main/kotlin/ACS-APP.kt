import kotlin.concurrent.thread
import kotlin.system.exitProcess


const val CANCEL = "CANCEL"
const val maintenance = 0b10000000
const val maxMessageSize = 16



// ACS APP
fun app(){
    init()
    while(true) {
        while (!HAL.isBit(maintenance)) {
            Log.init()
            accessMode()
            Log.writeLog()
        }
        LCD.clear()
        LCD.writeString("Out Of Service")
        while (HAL.isBit(maintenance)) {
            maintenanceMode()
        }
    }
}


fun init() {
    HAL.init()
    SerialEmitter.init()
    KBD.init()
    LCD.init()
    DoorMechanism.init()
    User.usersInit()
    DoorMechanism.close(1)
    while(!DoorMechanism.finished()){}

}

fun accessMode() {
    var insertedUIN: String
    var insertedPIN = ""
    do {
        insertedUIN = TUI.readUIN("Insert UIN:")
        if (insertedUIN != CANCEL) insertedPIN = TUI.readPIN("Insert PIN:")
        if(HAL.isBit(maintenance))break
    } while (insertedUIN == CANCEL || insertedPIN == CANCEL || !User.checkUser(insertedUIN.toInt(), insertedPIN))
    if (HAL.isBit(maintenance))return

    Log.newLog(insertedUIN.toInt())

    val info = User.getUser(insertedUIN.toInt())

    LCD.writeString(info!!.name)
    LCD.cursor(1, 0)
    LCD.writeString(info.message)

    DoorMechanism.open(1)

    val afterKey = KBD.waitKey(5000)
    if (afterKey == '#') {
        val newPin1 = TUI.readPIN("Novo PIN:")
        if(newPin1 != CANCEL) {
            val newPin2 = TUI.readPIN("Confirmar PIN:")
            if(newPin2 != CANCEL){
                if(newPin1 == newPin2)User.editPin(insertedUIN.toInt(),newPin1)
            }
        }
    }
    else if (afterKey == '*') User.addUserMessage(insertedUIN.toInt(), "")

    while (!DoorMechanism.finished()) {}
    DoorMechanism.close(1)
    //while (!DoorMechanism.finished()) {
    //}
}



fun maintenanceMode() {
    while (HAL.isBit(maintenance)) {
        clearConsole()
        printMaintenanceModeMenu()
        val option = readLine()?.toIntOrNull()
        when (option) {
            1 -> insertUserMM()
            2 -> removeUserMM()
            3 -> addMessageMM()
            4 -> turnOffSystemMM()
            else -> println("Opção Inválida")
        }
    }
    
}





//////////////////////////// MAINTENANCE MODE FUNCTIONS /////////////////////////////////////////

fun turnOffSystemMM() {
    User.usersWrite()
    shutdownMaintenanceMode()

    Thread.sleep(5000)
    exitProcess(1)


}
fun addMessageMM() {
    var sure = false
    var sureMessage = true
    var userUIN = 0
    var userName : String? = "."
    var userMessage = "."
    while (!sure) {
        do {
            clearConsole()
            if (userName == null) {
                println("O UIN inserido não foi encontrado no sistema")
                Thread.sleep(5000)
                clearConsole()
            }
            println("Insira o UIN do usuário a se adicionar a mensagem")
            userUIN = readln().toInt()
            userName = User.getUser(userUIN)?.name
        } while (userName == null)
        do {
            clearConsole()
            if (userMessage.length > maxMessageSize) {
                println("A mensagem inserida ultrapassa o limite de $maxMessageSize caracteres")
                clearConsole()
            }
            println("Qual a mensagem a ser inserida para o utilizador $userName? (max. $maxMessageSize caracteres)")
            userMessage = readln()
            clearConsole()
            print("Deseja inserir a mensagem: '$userMessage' para o utilizador $userName? \n 1. Sim \n 2. Alterar mensagem \n 3. Alterar utilizador \n 4. Retornar para o menu")
            val option = readLine()?.toIntOrNull()
            when (option) {
                1 -> {sure = true ; sureMessage = false}
                2 -> sureMessage = true
                3 -> break //{sure = false ; sureMessage = false}
                4 -> return
                else -> sure = false
            }
        } while (sureMessage)
    }
    User.addUserMessage(userUIN, userMessage)
}
fun removeUserMM () {
    var sure = false
    var removeUserUIN = 0
    var removeUserName : String? = "."
    while (!sure) {
        do {
            clearConsole()
            if (removeUserName == null) {
                println("O UIN inserido não foi encontrado no sistema")
                Thread.sleep(5000)
                clearConsole()
            }
            println("Insira o UIN do usuário a se remover")
            removeUserUIN = readln().toInt()
            removeUserName = User.getUser(removeUserUIN)?.name
        } while (removeUserName == null)
        println("Deseja remover o utilizador $removeUserName? \n 1. Sim \n 2. Não \n 3. Retornar ao Menu")
        val option = readLine()?.toIntOrNull()
        sure = when (option) {
            1 -> true
            2 -> false
            3 -> return
            else -> false
        }
    }
    User.removeUser(removeUserUIN)
    return
}
fun insertUserMM () {
    var sure = false
    var newUserName = ""
    var newUserPin = ""
    while (!sure) {
        do {
            clearConsole()
            println("Insira o nome do usuário (max. 16 caracteres)")
            newUserName = readln()
        } while (newUserName.length > 16)
        do {
            clearConsole()
            println("Insira o PIN do usuário (4 dígitos)")
            newUserPin = readln()
        } while (newUserPin.toInt() < 0 || newUserPin.toInt() > 9999)
        println("Deseja adicionar o utilizador: $newUserName com o PIN: $newUserPin ? \n 1. Sim \n 2. Não \n 3. Retornar ao Menu")
        val option = readLine()?.toIntOrNull()
        sure = when (option) {
            1 -> true
            2 -> false
            3 -> return
            else -> false
        }
    }
    User.newUser(newUserName, newUserPin)
    return
}

fun shutdownMaintenanceMode() {
    clearConsole()

    val shutdownMessage = """
        .----------------.  .----------------.  .----------------. 
        | .--------------. || .--------------. || .--------------. |
        | |      __      | || |     ______   | || |    _______   | |
        | |     /  \     | || |   .' ___  |  | || |   /  ___  |  | |
        | |    / /\ \    | || |  / .'   \_|  | || |  |  (__ \_|  | |
        | |   / ____ \   | || |  | |         | || |   '.___`-.   | |
        | | _/ /    \ \_ | || |  \ `.___.'\  | || |  |`\____) |  | |
        | ||____|  |____|| || |   `._____.'  | || |  |_______.'  | |
        | |              | || |              | || |              | |
        | '--------------' || '--------------' || '--------------' |
        | '----------------'  '----------------'  '---------------'| 
        |==========================================================|
        |                                                          | 
        |                  Encerrando o sistema...                 |
        |                                                          |
        |==========================================================|
        """.trimIndent()

    print(shutdownMessage)

    val animationDelay = 300L // Delay entre cada quadro da animação em milissegundos
    val frames = listOf("◐", "◓", "◑", "◒") // Quadros da animação
    thread {
        while (true) {
            frames.forEach { frame ->
                print("\r\t\t\t\t\t\t\t $frame")
                Thread.sleep(animationDelay)
            }
        }
    }
}
fun printMaintenanceModeMenu() {
    val menu = """
| .----------------.  .----------------.  .----------------. 
| .--------------. || .--------------. || .--------------. |
| |      __      | || |     ______   | || |    _______   | |
| |     /  \     | || |   .' ___  |  | || |   /  ___  |  | |
| |    / /\ \    | || |  / .'   \_|  | || |  |  (__ \_|  | |
| |   / ____ \   | || |  | |         | || |   '.___`-.   | |
| | _/ /    \ \_ | || |  \ `.___.'\  | || |  |`\____) |  | |
| ||____|  |____|| || |   `._____.'  | || |  |_______.'  | |
| |              | || |              | || |              | |
| '--------------' || '--------------' || '--------------' |
| '----------------'  '----------------'  '----------------' 
|
|          Access Control System - Modo de Manutenção
|          
|          1. Inserir utilizador
|          2. Remover utilizador
|          3. Adicionar mensagem
|          4. Desligar sistema
|          
|          
|
|=============================================================
|
    """.trimMargin()

    println(menu)
}
fun clearConsole() {
    print("\u001b[H\u001b[2J")
    System.out.flush()
}