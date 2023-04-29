package models
import utils.Utilities
data class Contact(
    var id: Int = 0,
    var firstName: String,
    var lastName: String,
    var phone: String,
    var email: String,
    var groups: MutableSet<Group> = mutableSetOf()
)
{
    private var lastGroupId = 0
    private fun getGroupId() = lastGroupId++

    fun addGroup(group: String) : Boolean {
        return groups.add(group)
    }

    fun numberOfGroups() = groups.size

    fun removeGroup(group: String): Boolean {
        return groups.remove(group)
    }

    fun update(id: Int, newContact : Contact): Boolean {
        val foundContact = findOne(id)

        //if the object exists, use the details passed in the newContact parameter to
        //update the found object in the ArrayList
        if (foundContact != null){
            foundContact.firstName = newContact.firstName
            foundContact.lastName = newContact.lastName
            foundContact.email = newContact.email
            foundContact.phone = newContact.phone
            foundContact.groups = newContact.groups
            return true
        }

        //if the object was not found, return false, indicating that the update was not successful
        return false
    }

    fun findOne(id: Int): Contact?{
        return contact.find{ contact -> contact.id == id }
    }

    fun delete(id: Int): Boolean {
        return contact.removeIf { contact -> contact.id == id}
    }

    override fun toString(): String {
        return "$id: $firstName $lastName, Email($email), Phone($phone), Groups($groups)"
    }
}