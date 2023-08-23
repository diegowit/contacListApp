package utils
import controller.ContactAPI
import models.Contact
import models.Group
import mu.KotlinLogging

import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import kotlin.system.exitProcess
import java.util.*
import persistence.JSONSerializer

import utils.ValidateInput.readValidEmail
import utils.ValidateInput.readValidPhone
import java.io.File

private val logger = KotlinLogging.logger {}




private val contactAPI = ContactAPI(JSONSerializer(File("Contact.json")))
fun main() = runMainMenu()

fun runMainMenu() {
    do {
        when (val option = mainMenu()) {
            1 -> runContactMenu()
            2 -> runGroupMenu()
            0 -> exitApp()
            else -> println("Invalid menu choice: $option")
        }
    } while (true)
}

fun mainMenu(): Int {
    return readNextInt(
        """
         > -----------------------------------------------------  
         > |                  CONTACT LIST APP                 |
         > -----------------------------------------------------  
         > | MAIN MENU                                         |
         > |   1) Contact Menu                                 |
         > |   2) Group Menu                                   |
         > |                                                   |
         > -----------------------------------------------------  
         > |   0) Exit                                         |
         > -----------------------------------------------------  
         > ==>> """.trimMargin(">"))
}

fun runContactMenu() {
    do {
        when (val option = contactMenu()) {
            1 -> addContact()
            2 -> listAllContacts()
            3 -> updateContact()
            4 -> deleteContact()
            5 -> addGroupToContact()
            20 -> save()
            21 -> load()
            0 -> return // Return to main menu
            else -> println("Invalid menu choice: $option")
        }
    } while (true)
}

fun contactMenu(): Int {
    return readNextInt(
        """
         > -----------------------------------------------------  
         > |                  CONTACT MENU                     |
         > -----------------------------------------------------  
         > |   1) Add a contact                                |
         > |   2) List ALL contacts                            |
         > |   3) Update a contact                             |
         > |   4) Delete a contact                             |
         > |   5) Add group To contact                         |
         > -----------------------------------------------------  
         > |   20) Save notes                                  |
         > |   21) Load notes                                  |
         > -----------------------------------------------------  
         > |   0) Return to Main Menu                          |
         > -----------------------------------------------------  
         > ==>> """.trimMargin(">"))
}


fun runGroupMenu() {
    do {
        when (val option = groupMenu()) {
            1 -> addGroup()
            2 -> listAllGroups()
            0 -> return // Return to main menu
            else -> println("Invalid menu choice: $option")
        }
    } while (true)
}

fun groupMenu(): Int {
    return readNextInt(
        """
         > -----------------------------------------------------  
         > |                  GROUP MENU                       |
         > -----------------------------------------------------  
         > |   1) Add a group                                  |
         > |   2) List ALL groups                              |
         > -----------------------------------------------------  
         > |   0) Return to Main Menu                          |
         > -----------------------------------------------------  
         > ==>> """.trimMargin(">"))
}

fun addGroup() {
    val groupName = readNextLine("Enter group name: ")
    // Add your logic to add the group here, for example:
    // val isAdded = groupAPI.add(Group(groupName = groupName))
    // if (isAdded) println("Group added successfully!") else println("Failed to add group")
    println("Group '$groupName' added!") // Placeholder
}

fun listAllGroups() {
    // Add your logic to list all groups here, for example:
    // println(groupAPI.listAllGroups())
    println("List of all groups...") // Placeholder
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




fun listAllContacts() {
    println(contactAPI.listAllContacts())
}


fun updateContact() {
    listAllContacts()
    if (contactAPI.numberOfContacts() > 0) {
        //only ask the user to choose the contact if contacts exist
        val id = readNextInt("Enter the id of the contact to update: ")
        if (contactAPI.isValidId(id)) {
            val firstName = readNextLine("Enter the first name of the contact: ")
            val lastName = readNextLine("Enter the last name of the contact: ")
            val phone = readValidPhone("Enter the phone number of the contact (XXX-XXX-XXXX): ")
            val email = readValidEmail("Enter the email of the contact: ")
           // val groups = readGroups("Enter the groups of the contact separated by commas: ")

            //pass the id of the contact and the new contact details to ContactAPI for updating and check for success.
            if (contactAPI.updateContact(id, Contact(firstName = firstName, lastName = lastName, phone = phone, email = email))) {
                println("Update Successful!")
            } else {
                println("Update Failed!")
            }
        } else {
            println("There are no contacts with this ID.")
        }
    }
}



fun deleteContact() {
    listAllContacts()
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



fun addGroupToContact() {
    listAllContacts()
    if (contactAPI.numberOfContacts() > 0) {
        // only ask the user to choose the contact if contacts exist
        val contactId = readNextInt("Enter the ID of the contact to add a group to: ")
        if (contactAPI.isValidId(contactId)) {
            val contact = contactAPI.findContact(contactId)
            if (contact != null) {
                if (contact.addGroup(Group(groupName = readNextLine("\t Group Name:"))))

                    println("Add Successful")
                else println("Add not Successful")
            }
        }
    }
}



fun save() {
    try {
        contactAPI.store()
    } catch (e: Exception) {
        System.err.println("Error writing to file: $e")
    }
}

fun load() {
    try {
        contactAPI.load()
    } catch (e: Exception) {
        System.err.println("Error reading from file: $e")
    }
}
        fun exitApp() {
            println("Exiting...bye")
            exitProcess(0)
        }



