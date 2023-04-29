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
   /* fun delete(id: Int): Boolean {
        val contactToDelete = findContact(id)
        if (contactToDelete != null) {
            contacts.remove(contactToDelete)
            return true
        }
        return false
    }
*/

    fun listAllContacts(): String =
        if (contacts.isEmpty())  "No notes stored"
        else formatListString(contacts)

    fun numberOfContacts(): Int {
        return contacts.size
    }


}


