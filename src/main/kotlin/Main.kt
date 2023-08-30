package utils
import controller.ContactAPI
import models.Contact
import models.Group
import mu.KotlinLogging

import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import kotlin.system.exitProcess
import persistence.JSONSerializer

import utils.ValidateInput.readValidEmail
import utils.ValidateInput.readValidPhone
import java.io.File

private val logger = KotlinLogging.logger {}



// Initialize the ContactAPI with JSONSerializer

private val contactAPI = ContactAPI(JSONSerializer(File("Contact.json")))
fun main() = runMainMenu()

/**
 * Runs the main menu loop.
 */
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



/**
 *
 *  Main Menu
 *
 */

fun mainMenu(): Int {
    return readNextInt(
        """
    
▐░▌       ▐░▌▐░░░░░░░░░░░▌▐░▌          ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░▌     ▐░░▌▐░░░░░░░░░░░▌
▐░▌       ▐░▌▐░█▀▀▀▀▀▀▀▀▀ ▐░▌          ▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░▌░▌   ▐░▐░▌▐░█▀▀▀▀▀▀▀▀▀ 
▐░▌       ▐░▌▐░▌          ▐░▌          ▐░▌          ▐░▌       ▐░▌▐░▌▐░▌ ▐░▌▐░▌▐░▌          
▐░▌   ▄   ▐░▌▐░█▄▄▄▄▄▄▄▄▄ ▐░▌          ▐░▌          ▐░▌       ▐░▌▐░▌ ▐░▐░▌ ▐░▌▐░█▄▄▄▄▄▄▄▄▄ 
▐░▌  ▐░▌  ▐░▌▐░░░░░░░░░░░▌▐░▌          ▐░▌          ▐░▌       ▐░▌▐░▌  ▐░▌  ▐░▌▐░░░░░░░░░░░▌
▐░▌ ▐░▌░▌ ▐░▌▐░█▀▀▀▀▀▀▀▀▀ ▐░▌          ▐░▌          ▐░▌       ▐░▌▐░▌   ▀   ▐░▌▐░█▀▀▀▀▀▀▀▀▀ 
▐░▌▐░▌ ▐░▌▐░▌▐░▌          ▐░▌          ▐░▌          ▐░▌       ▐░▌▐░▌       ▐░▌▐░▌          
▐░▌░▌   ▐░▐░▌▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄█░▌▐░▌       ▐░▌▐░█▄▄▄▄▄▄▄▄▄ 
▐░░▌     ▐░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░░░░░░░░░░░▌
 ▀▀       ▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀         ▀  ▀▀▀▀▀▀▀▀▀▀▀ 
            
╔═══════════════════════════════════════════════════╗
║                 CONTACT LIST APP                  ║
╠═══════════════════════════════════════════════════╣
║                                                   ║
║                  1 Contact Menu                   ║
║                  2 Group Menu                     ║
╚═══════════════════════════════════════════════════╝
║                  0    Exit                        ║
╚═══════════════════════════════════════════════════╝
==>> """.trimMargin())
}







/**
 *
 *  Contacts
 *
 */

fun runContactMenu() {
    do {
        when (val option = contactMenu()) {
            1 -> addContact()
            2 -> runListingMenu()
            3 -> updateContact()
            4 -> deleteContact()
            5 -> addGroupToContact()
            6 -> runTagMenu()
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
╔═══════════════════════════════════════════════════╗
║                   CONTACT MENU                    ║
╠═══════════════════════════════════════════════════╣
║   1) Add a contact                                ║
║   2) List contacts                                ║
║   3) Update a contact                             ║
║   4) Delete a contact                             ║
║   5) Add group to contact                         ║
║   6) Manage contact tags                          ║
╟───────────────────────────────────────────────────╢
║   20) Save Contacts                               ║
║   21) Load Contacts                               ║
╚═══════════════════════════════════════════════════╝
║   0) Return to Main Menu                          ║
╚═══════════════════════════════════════════════════╝
==>> """.trimMargin(">"))
}


fun addContact() {
    // Prompt user for contact details
    val firstName = readNextLine("Enter first name: ")
    val lastName = readNextLine("Enter last name: ")
    val phone = readValidPhone("Enter phone number (e.g. +1-555-555-5555): ")
    val email = readValidEmail("Enter email address: ")

    // Attempt to add the contact using ContactAPI
    val isAdded = contactAPI.add(Contact(firstName = firstName, lastName = lastName, phone = phone, email = email))

    // Provide feedback based on success
    if (isAdded) {
        println("Contact added successfully!")
    } else {
        println("Failed to add contact")
    }
}

fun updateContact() {
    // Display list of contacts
    listAllContacts()

    // Check if contacts exist before proceeding
    if (contactAPI.numberOfContacts() > 0) {
        val id = readNextInt("Enter the id of the contact to update: ")

        // Check if the provided ID is valid
        if (contactAPI.isValidId(id)) {
            // Prompt user for updated contact details
            val firstName = readNextLine("Enter the first name of the contact: ")
            val lastName = readNextLine("Enter the last name of the contact: ")
            val phone = readValidPhone("Enter the phone number of the contact (XXX-XXX-XXXX): ")
            val email = readValidEmail("Enter the email of the contact: ")

            // Attempt to update the contact using ContactAPI
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
    // Display list of contacts
    listAllContacts()

    // Check if contacts exist before proceeding
    if (contactAPI.numberOfContacts() > 0) {
        val id = readNextInt("Enter the id of the contact to delete: ")
        val contactToDelete = contactAPI.findContact(id)

        // Check if the contact exists before attempting to delete
        if (contactToDelete != null) {
            // Attempt to delete the contact using ContactAPI
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
    // Display list of contacts
    listAllContacts()

    // Check if contacts exist before proceeding
    if (contactAPI.numberOfContacts() > 0) {
        val contactId = readNextInt("Enter the ID of the contact to add a group to: ")

        // Check if the provided ID is valid
        if (contactAPI.isValidId(contactId)) {
            val contact = contactAPI.findContact(contactId)

            // Check if the contact exists before adding a group
            if (contact != null) {
                if (contact.addGroup(Group(groupName = readNextLine("Enter Group Name:")))) {
                    println("Add Successful")
                } else {
                    println("Add not Successful")
                }
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
    println("_______  _______  _______    __   __  _______  __   __    _______  _______  _______  __    _ \n" +
            "|       ||       ||       |  |  | |  ||       ||  | |  |  |       ||       ||       ||  |  | |\n" +
            "|  _____||    ___||    ___|  |  |_|  ||   _   ||  | |  |  |  _____||   _   ||   _   ||   |_| |\n" +
            "| |_____ |   |___ |   |___   |       ||  | |  ||  |_|  |  | |_____ |  | |  ||  | |  ||       |\n" +
            "|_____  ||    ___||    ___|  |_     _||  |_|  ||       |  |_____  ||  |_|  ||  |_|  ||  _    |\n" +
            " _____| ||   |___ |   |___     |   |  |       ||       |   _____| ||       ||       || | |   |\n" +
            "|_______||_______||_______|    |___|  |_______||_______|  |_______||_______||_______||_|  |__|\n" +
            "                                                                                              \n" +
            "\n" +
            "      Thank you for using Contact List App!\n" +
            "Exiting... Have a great day!")
    exitProcess(0)
}




/**
 *
 *  Listing Contacts
 *
 */





fun runListingMenu() {
    do {
        when (val option = listingMenu()) {
            1 -> listContactsByGroup()
            2 -> listContactsByLastName()
            3 -> listContactsByPhoneNumber()
            4 -> listAllContacts()
            0 -> return // Return to the previous menu
            else -> println("Invalid menu choice: $option")
        }
    } while (true)
}

fun listingMenu(): Int {
    return readNextInt(
        """
╭───────────────────────────────────────────────────╮
│             LISTING CONTACT MENU                  │
├───────────────────────────────────────────────────┤
│   1) List Contacts by Group                       │
│   2) List Contacts by Last Name                   │
│   3) List Contacts by Phone Number                │
│   4) List All Contacts                            │
├───────────────────────────────────────────────────┤
│   0) Return to Previous Menu                      │
╰───────────────────────────────────────────────────╯
==>> """.trimMargin(">"))
}


fun listContactsByGroup() {
    // Prompt user for group name
    val groupName = readNextLine("Enter group name to list contacts by: ")

    // Get contacts in the specified group using ContactAPI
    val contactsInGroup = contactAPI.listContactsByGroup(groupName)

    // Display the results
    if (contactsInGroup.isEmpty()) {
        println("No contacts found in the group '$groupName'")
    } else {
        println("Listing contacts by group '$groupName':")
        contactsInGroup.forEach { contact ->
            println(contact.toString())
        }
    }
}

fun listContactsByLastName() {
    // Get contacts grouped by last name using ContactAPI
    val contactsByLastName = contactAPI.listContactsByLastName()

    // Display the results
    if (contactsByLastName.isEmpty()) {
        println("No contacts found")
    } else {
        println("Listing contacts by last name:")
        contactsByLastName.forEach { (lastName, contacts) ->
            println("Last Name: $lastName")
            contacts.forEach { contact ->
                println(contact.toString())
            }
        }
    }
}

fun listContactsByPhoneNumber() {
    // Prompt user for phone number
    val phoneNumber = readNextLine("Enter phone number to list contacts by: ")

    // Get contacts with the specified phone number using ContactAPI
    val contactsByPhoneNumber = contactAPI.listContactsByPhoneNumber(phoneNumber)

    // Display the results
    if (contactsByPhoneNumber.isEmpty()) {
        println("No contacts found with phone number '$phoneNumber'")
    } else {
        println("Listing contacts by phone number '$phoneNumber':")
        contactsByPhoneNumber.forEach { contact ->
            println(contact.toString())
        }
    }
}

fun listAllContacts() {
    // Display all contacts using ContactAPI
    println(contactAPI.listAllContacts())
}



/**
 *
 *  Groups
 *
 */




fun runGroupMenu() {
    do {
        when (val option = groupMenu()) {
            1 -> addGroup()
           // -> listAllGroups()

            0 -> return // Return to main menu
            else -> println("Invalid menu choice: $option")
        }
    } while (true)
}
fun groupMenu(): Int {
    return readNextInt(
        """
╭───────────────────────────────────────────────────╮
│                   GROUP MENU                      │
├───────────────────────────────────────────────────┤
│   1) Add a Group                                  │
│                                                   │
├───────────────────────────────────────────────────┤
│   0) Return to Main Menu                          │
╰───────────────────────────────────────────────────╯
==>> """.trimMargin(">"))
}

fun addGroup() {
    val groupName = readNextLine("Enter group name: ")


    println("Group '$groupName' added!") // Placeholder
}



/**
 *
 *  Tags
 *
 */



fun runTagMenu() {
    do {
        when (val option = tagMenu()) {
            1 -> addTagToContact()
            2 -> removeTagFromContact()
            3 -> listContactsByTag()
            0 -> return // Return to contact menu
            else -> println("Invalid menu choice: $option")
        }
    } while (true)
}

fun tagMenu(): Int {
    return readNextInt(
        """
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
                   TAG MENU
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
   1) Add a Tag
   2) Remove Tag
   3) List Contacts by Tag
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
   0) Return to Contact Menu
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
==>> """.trimMargin(">"))
}

fun listContactsByTag() {
    // Prompt user for tag name
    val tagName = readNextLine("Enter tag name to list contacts by: ")

    // Get contacts with the specified tag using ContactAPI
    val contactsByTag = contactAPI.listContactsByTag(tagName)

    // Display the results
    if (contactsByTag.isEmpty()) {
        println("No contacts found for the tag '$tagName'.")
    } else {
        println("Listing contacts by tag '$tagName':")
        contactsByTag.forEach { println(it) }
    }
}

fun addTagToContact() {
    // Prompt user for contact ID and tag name
    val contactId = readNextLine("Enter the contact ID to which you want to add a tag: ").toIntOrNull()
    if (contactId == null) {
        println("Invalid ID format. Operation aborted.")
        return
    }
    val tagName = readNextLine("Enter the tag name: ")

    // Attempt to add the tag to the contact using ContactAPI
    if (contactAPI.addTagToContact(contactId, tagName)) {
        println("Tag '$tagName' added to contact with ID $contactId.")
    } else {
        println("Failed to add tag. Please check if the contact ID is correct.")
    }
}

fun removeTagFromContact() {
    // Prompt user for contact ID and tag name
    val contactId = readNextLine("Enter the contact ID from which you want to remove a tag: ").toIntOrNull()
    if (contactId == null) {
        println("Invalid ID format. Operation aborted.")
        return
    }
    val tagName = readNextLine("Enter the tag name: ")

    // Attempt to remove the tag from the contact using ContactAPI
    if (contactAPI.removeTagFromContact(contactId, tagName)) {
        println("Tag '$tagName' removed from contact with ID $contactId.")
    } else {
        println("Failed to remove tag. Please check if the contact ID and tag are correct.")
    }
}























