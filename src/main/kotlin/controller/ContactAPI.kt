package controller
import models.Group
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
    private var contacts: MutableList<Contact> = mutableListOf()

    private var serializer: Serializer = serializerType



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
    fun listAllContacts(): String {
        return if (contacts.isEmpty()) {
            "No contacts available"
        } else {
            contacts.joinToString(separator = "\n") { it.toString() }
        }
    }




    // Get the number of contacts in the system.
    fun numberOfContacts(): Int {
        return contacts.size
    }

    // Check if an ID is valid.
    fun isValidId(id: Int): Boolean {
        return contacts.any { it.id == id }
    }


    fun listContactsByGroup(groupName: String): List<Contact> {
        return contacts.filter { contact ->
            contact.groups.any { group ->
                group.groupName.equals(groupName, ignoreCase = true)
            }
        }
    }

    fun listContactsByLastName(): Map<String, List<Contact>> {
        return contacts.groupBy { it.lastName }

    }


    fun listContactsByPhoneNumber(phoneNumber: String): List<Contact> {
        return contacts.filter { it.phone == phoneNumber }
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




    fun listContactsByTag(tag: String): List<Contact> {
        return contacts.filter { it.hasTag(tag) }
    }

    // Function to add tag to a specific contact by ID
    fun addTagToContact(contactId: Int, tag: String): Boolean {
        val contact = contacts.find { it.id == contactId }
        return contact?.addTag(tag) ?: false
    }

    // Function to remove tag from a specific contact by ID
    fun removeTagFromContact(contactId: Int, tag: String): Boolean {
        val contact = contacts.find { it.id == contactId }
        return contact?.removeTag(tag) ?: false
    }













}









