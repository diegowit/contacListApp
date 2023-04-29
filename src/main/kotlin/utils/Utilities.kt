package utils


import models.Contact
import models.Group
// NOTE: JvmStatic annotation means that the methods are static i.e. we can call them over the class
//      name; we don't have to create an object of Utilities to use them.

object Utilities {

    @JvmStatic
    fun formatListString(contactsToFormat: List<Contact>): String =
        contactsToFormat
            .joinToString(separator = "\n") { contact ->  "$contact" }

    @JvmStatic
    fun formatSetString(groupsToFormat: Set<Group>): String =
        groupsToFormat
            .joinToString(separator = "\n") { group ->  "\t$group" }

}