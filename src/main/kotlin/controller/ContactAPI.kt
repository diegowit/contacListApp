package controller

import models.Contact
import persistence.Serializer
import java.util.ArrayList

/**
 * The `ContactAPI` class is responsible for managing contacts.
 *
 * @param serializerType The type of serializer to use for data storage.
 */
class ContactAPI(serializerType: Serializer) {

    private var contacts: MutableList<Contact> = mutableListOf()
    private var serializer: Serializer = serializerType

    private var lastId = 0

    private fun generateNewId(): Int {
        return lastId++
    }

    /**
     * Add a contact to the `contacts` list.
     *
     * @param contact The contact object to add.
     * @return `true` if the contact was added successfully, `false` otherwise.
     */
    fun add(contact: Contact): Boolean {
        contact.id = generateNewId()
        return contacts.add(contact)
    }

    // Find a contact in the list by ID.
    fun findContact(id: Int): Contact? {
        return contacts.find { it.id == id }
    }

    // Delete a contact from the list by ID.
    fun delete(id: Int): Boolean {
        val contactToDelete = findContact(id)
        if (contactToDelete != null) {
            contacts.remove(contactToDelete)
            return true
        }
        return false
    }

    // Update a contact in the list by ID.
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

    // List contacts by group name.
    fun listContactsByGroup(groupName: String): List<Contact> {
        return contacts.filter { contact ->
            contact.groups.any { group ->
                group.groupName.equals(groupName, ignoreCase = true)
            }
        }
    }

    // List contacts grouped by last name.
    fun listContactsByLastName(): Map<String, List<Contact>> {
        return contacts.groupBy { it.lastName }
    }

    // List contacts by phone number.
    fun listContactsByPhoneNumber(phoneNumber: String): List<Contact> {
        return contacts.filter { it.phone == phoneNumber }
    }

    // Load contacts from storage.
    @Throws(Exception::class)
    fun load() {
        contacts = serializer.read() as ArrayList<Contact>
    }

    // Store contacts to storage.
    @Throws(Exception::class)
    fun store() {
        serializer.write(contacts)
    }

    // Format contacts as a string.
    private fun formatListString(contactsToFormat: List<Contact>): String {
        return contactsToFormat.joinToString(separator = "\n") { contact ->
            "${contacts.indexOf(contact)}: $contact"
        }
    }

    // List contacts by tag.
    fun listContactsByTag(tag: String): List<Contact> {
        return contacts.filter { it.hasTag(tag) }
    }

    // Function to add a tag to a specific contact by ID.
    fun addTagToContact(contactId: Int, tag: String): Boolean {
        val contact = contacts.find { it.id == contactId }
        return contact?.addTag(tag) ?: false
    }

    // Function to remove a tag from a specific contact by ID.
    fun removeTagFromContact(contactId: Int, tag: String): Boolean {
        val contact = contacts.find { it.id == contactId }
        return contact?.removeTag(tag) ?: false
    }
}
