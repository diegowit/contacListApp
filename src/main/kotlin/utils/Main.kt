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
/*import persistence.JSONSerializer*/
import utils.CategoryUtility
import utils.ScannerInput

import utils.ValidateInput.readValidEmail
import utils.ValidateInput.readValidPhone

import java.io.File
import java.lang.System.exit

private val logger = KotlinLogging.logger {}

val scanner = Scanner(System.`in`)

private val contactAPI = ContactAPI()

fun main() = runMenu()

fun runMenu() {
    do {
        when (val option = mainMenu()) {
            1 -> addContact()
            2 -> listAllContacts()
            3 -> updateContact()
            //4 -> deleteContact()
            0 -> exitApp()
            else -> println("Invalid menu choice: $option")
        }
    } while (true)
}

fun mainMenu():Int {
    return ScannerInput.readNextInt(
        """
         > -----------------------------------------------------  
         > |                  CONTACT LIST APP                 |
         > -----------------------------------------------------  
         > | CONTACT MENU                                      |
         > |   1) Add a contact                                 |
         > |   2) List ALL contacts                                 |
         > |   3) Update a contact                              |
         > |   4) Delete a contact                              |
         > -----------------------------------------------------  
         > |   0) Exit                                          |
         > -----------------------------------------------------  
         > ==>> """.trimMargin(">"))

}




fun addContact() {
    //logger.info { "addContact() function invoked" }
    val firstName = readNextLine("Enter first name: ")
    val lastName = readNextLine("Enter last name: ")
    val phone = readValidPhone("Enter phone number (e.g. +1-555-555-5555): ")
    val email = readValidEmail("Enter email address: ")
   // val groups = readGroups()
    val isAdded = contactAPI.add(Contact(firstName = firstName, lastName = lastName, phone = phone, email = email))

    if (isAdded) {
        println("Contact added successfully!")
    } else {
        println("Failed to add contact")
    }
}

/*
private fun readGroups(): MutableSet<Group> {
    val groups = mutableSetOf<Group>()
    var addAnotherGroup = true

    do {
        val groupName = readNextLine("Enter a group name (leave blank to finish): ").trim()

        if (groupName.isNotEmpty()) {
            groups.add(Group(groupName))
        } else {
            addAnotherGroup = false
        }
    } while (addAnotherGroup)

    return groups
}
*/
fun listAllContacts() {
    println(contactAPI.listAllContacts())
}
fun updateContact(){
    logger.info { "updateContact() function invoked" }
}

/*fun deleteContact() {
    listContacts()
    if (contactAPI.numberOfContacts() > 0) {
        // only ask the user to choose the contact to delete if contacts exist
        val id = readNextInt("Enter the id of the contact to delete: ")
        val contactToDelete = contactAPI.findContact(id)
        if (contactToDelete != null) {
            val isDeleted = contactAPI.delete(id)
            if (isDeleted) {
                println("Contact Delete Successful!")
            } else {
                println("Contact Delete NOT Successful!")
            }
        } else {
            println("Contact not found.")
        }
    } else {
        println("No contacts found.")
    }
}


 */
fun exitApp() {
    println("Exiting...bye")
    exitProcess(0)
}

