package utils
import controller.ContactAPI
import models.Contact
import models.Group
import utils.ScannerInput.readNextChar
import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import kotlin.system.exitProcess

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

fun mainMenu() = readNextInt(
    """
         > -----------------------------------------------------  
         > |               CONTACT LIST APP                     |
         > -----------------------------------------------------  
         > | CONTACT MENU                                      |
         > |   1) Add a contact                                 |
         > |   2) List contacts                                 |
         > |   3) Update a contact                              |
         > |   4) Delete a contact                              |
         > -----------------------------------------------------  
         > |   0) Exit                                          |
         > -----------------------------------------------------  
         > ==>> """.trimMargin(">"))


fun exitApp() {
    println("Exiting...bye")
    exitProcess(0)
}

