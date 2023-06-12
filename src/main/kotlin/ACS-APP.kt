import User.addUserMessage
import User.checkUser
import User.getUser
import User.newUser
import User.removeUser

public const val  errorValue = "error"
const val CANCEL = "CANCEL"
const val maintenance = false
const val maxMessageSize = 15 // TIAGO QUAL É o TAMANHO MAXIMO QUE cabE no baguio
fun init() {
    HAL.init()
    KBD.init()
    SerialEmitter.init()
    LCD.init()
    DoorMechanism.init()
    //DoorMechanism.close(1)
    //while(!DoorMechanism.finished()){}

}


fun accessMode() {
    init()
    login()
}

fun login() {
    while (!maintenance) {
        var insertedUIN: String
        var insertedPIN: String = ""
        do {
            insertedUIN = TUI.readUIN("Insert UIN:")
            if (insertedUIN != CANCEL) insertedPIN = TUI.readPIN("Insert PIN:")
        } while (insertedUIN == CANCEL || insertedPIN == CANCEL || !checkUser(insertedUIN.toInt(), insertedPIN))
        val info = getUser(insertedUIN.toInt())
        LCD.writeString("${info!!.name}")
        LCD.cursor(1, 0)
        LCD.writeString("${info.message}")
        DoorMechanism.open(1)
        val afterKey = KBD.waitKey(5000)
        if (afterKey == '#') println("novo pin")
        else if (afterKey == '*') addUserMessage(insertedUIN.toInt(), "")
        while (!DoorMechanism.finished()) {
        }
        DoorMechanism.close(1)
        while (!DoorMechanism.finished()) {
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
fun maintenanceMode() {
    while (maintenance) {
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

fun turnOffSystemMM() {
    TODO()
}
fun addMessageMM() {
    var sure = false
    var sureMessage = true
    var userUIN : Int = 0
    var userName : String? = "."
    var userMessage : String = "."
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
            userName = getUser(userUIN)?.name
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
            print("Deseja inserir a mensagem $userMessage para o utilizador $userName? 1. Sim 2. Alterar mensagem 3. Alterar utilizador 4. Retornar para o menu")
            val option = readLine()?.toIntOrNull()
            when (option) {
                1 -> sure = true
                2 -> sureMessage = false
                3 -> sure = false
                4 -> return
                else -> sure = false
            }
        } while (sureMessage)
    }
    addUserMessage(userUIN, userMessage)
}

fun removeUserMM () {
    var sure = false
    var removeUserUIN : Int = 0
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
            removeUserName = getUser(removeUserUIN)?.name
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
    removeUser(removeUserUIN)
    return
}
fun insertUserMM () {
    var sure : Boolean = false
    var newUserName : String = ""
    var newUserPin : Int = 0
    while (!sure) {
        do {
            clearConsole()
            println("Insira o nome do usuário (max. 16 caracteres)")
            newUserName = readln()
        } while (newUserName.length > 16)
        do {
            clearConsole()
            println("Insira o PIN do usuário (4 dígitos)")
            newUserPin = readln().toInt()
        } while (newUserPin < 1000 || newUserPin > 9999)
        println("Deseja adicionar o utilizador: $newUserName com o PIN: $newUserPin ? \n 1. Sim \n 2. Não \n 3. Retornar ao Menu")
        val option = readLine()?.toIntOrNull()
        sure = when (option) {
            1 -> true
            2 -> false
            3 -> return
            else -> false
        }
    }
    newUser(newUserName, newUserPin)
    return
}

