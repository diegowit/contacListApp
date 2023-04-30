package controller

import models.Contact
import persistence.Serializer
import utils.Utilities.formatListString

import java.util.ArrayList
/**
 * The `ContactAPI` class is responsible for managing contacts.
 *
 * @ contacts An ArrayList of Contact objects.
 */
class ContactAPI(serializerType: Serializer) {

    private var serializer: Serializer = serializerType

    private var contacts = ArrayList<Contact>()

    // To manage id in the system
    private var lastId = 0
    private fun getId() = lastId++


    /**
     * Add a contact to the `contacts` ArrayList.
     *
     * @param contact The contact object to add.
     * @return `true` if the contact was added successfully, `false` otherwise.
     */
    // Add a new contact to the ArrayList.
    fun add(contact: Contact): Boolean {
        contact.id = getId()
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

    // Delete a contact from the ArrayList by ID.
    fun delete(id: Int): Boolean {
        val contactToDelete = findContact(id)
        if (contactToDelete != null) {
            contacts.remove(contactToDelete)
            return true
        }
        return false
    }

    // Update a contact in the ArrayList by ID.
    fun updateContact(id: Int, updatedContact: Contact): Boolean {
        val contactToUpdate = findContact(id)
        if (contactToUpdate != null) {
            contactToUpdate.apply {
                firstName = updatedContact.firstName
                lastName = updatedContact.lastName
                phone = updatedContact.phone
                email = updatedContact.email
            }
            return true
        }
        return false
    }

    // List all contacts in the system.
    fun listAllContacts(): String =
        if (contacts.isEmpty()) "No Contact stored"
        else formatListString(contacts)

    // Get the number of contacts in the system.
    fun numberOfContacts(): Int {
        return contacts.size
    }

    // Check if an ID is valid.
    fun isValidId(id: Int): Boolean {
        return contacts.any { it.id == id }
    }





    @Throws(Exception::class)
    fun load() {
        contacts = serializer.read() as ArrayList<Contact>
    }

    @Throws(Exception::class)
    fun store() {
        serializer.write(contacts)
    }


    private fun formatListString(contactsToFormat : List<Contact>) : String =
        contactsToFormat
            .joinToString (separator = "\n") { contact ->
                contacts.indexOf(contact).toString() + ": " + contact.toString() }

}




