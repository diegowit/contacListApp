package utils
import controller.ContactAPI
import models.Contact
import models.Group
import mu.KotlinLogging
import utils.ScannerInput.readNextChar
import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import kotlin.system.exitProcess
import java.util.*
private val logger = KotlinLogging.logger {}

val scanner = Scanner(System.`in`)

private val contactAPI = ContactAPI()

fun main() = runMenu()

fun runMenu() {
    do {
        when (val option = mainMenu()) {
            1 -> addContact()
            2 -> listContacts()
            3 -> updateContact()
            4 -> deleteContact()
            0 -> exitApp()
            else -> println("Invalid menu choice: $option")
        }
    } while (true)
}

fun mainMenu():Int {
    return ScannerInput.readNextInt(
            """
         > -----------------------------------------------------  
         > |               CONTACT LIST APP                     |
         > -----------------------------------------------------  
         > | CONTACT MENU                                      |
         > |   1) Ad                               |
         > |   2) List cd a contact  ontacts                                 |
         > |   3) Update a contact                              |
         > |   4) Delete a contact                              |
         > -----------------------------------------------------  
         > |   0) Exit                                          |
         > -----------------------------------------------------  
         > ==>> """.trimMargin(">"))
}




fun addContact(){
    logger.info { "addContact() function invoked" }
}

fun listContacts(){
    logger.info { "listContacts() function invoked" }
}
fun updateContact(){
    logger.info { "updateContact() function invoked" }
}

fun deleteContact(){
    logger.info { "deleteContact() function invoked" }
}

fun exitApp() {
    println("Exiting...bye")
    exitProcess(0)
}

