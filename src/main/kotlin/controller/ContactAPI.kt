package controller

import models.Contact
import utils.Utilities.formatListString

import java.util.ArrayList

class ContactAPI() {

    private var contacts = ArrayList<Contact>()

    //To manage id in the system
    private var lastId = 0
    private fun getId() = lastId++


    fun add(contact: Contact): Boolean {
        return contacts.add(contact)
    }

    // Find a contact in the ArrayList by ID.
    fun findContact(id: Int): Contact? {
        for (contact in contacts) {
            if (contact.id == id) {
                return contact
            }
        }
        return null
    }



    //Delete a contact from the ArrayList by ID.
    fun delete(id: Int): Boolean {
        val contactToDelete = findContact(id)
        if (contactToDelete != null) {
            contacts.remove(contactToDelete)
            return true
        }
        return false
    }

    fun updateContact(id: Int, updatedContact: Contact): Boolean {
        val contactToUpdate = findContact(id)
        if (contactToUpdate != null) {
            contactToUpdate.apply {
                firstName = updatedContact.firstName
                lastName = updatedContact.lastName
                phone = updatedContact.phone
                email = updatedContact.email
               // groups = updatedContact.groups
            }
            return true
        }
        return false
    }




    fun listAllContacts(): String =
        if (contacts.isEmpty())  "No Contact stored"
        else formatListString(contacts)

    fun numberOfContacts(): Int {
        return contacts.size
    }

    fun isValidId(id: Int): Boolean {
        return contacts.any { it.id == id }
    }
    private fun formatListString(contactsToFormat : List<Contact>) : String =
        contactsToFormat
            .joinToString (separator = "\n") { contact ->
                contacts.indexOf(contact).toString() + ": " + contact.toString() }

}




