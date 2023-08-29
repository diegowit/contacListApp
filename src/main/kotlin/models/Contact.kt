package models
import models.Group
import utils.Utilities
data class Contact(
    var id: Int = 0,
    var firstName: String,
    var lastName: String,
    var phone: String,
    var email: String,
    var groups: MutableSet<Group> = mutableSetOf(),
    //var groups: MutableSet<Group> = mutableSetOf()
    var tags: MutableSet<String> = mutableSetOf()  // Added this line for tags
) {
    private var lastGroupId = 0
    private fun getGroupId() = lastGroupId++

    //Functions To manage Group
    fun addGroup(group: Group): Boolean {
        group.groupId = getGroupId()
        return groups.add(group)
    }

    fun numberOfGroups() = groups.size




    fun listAllGroups(): String {
        return if (groups.isEmpty()) "No groups available" else groups.joinToString(separator = "\n") { it.toString() }
    }


fun addTag(tag: String): Boolean {
    return tags.add(tag)
}

fun removeTag(tag: String): Boolean {
    return tags.remove(tag)
}

fun listTags(): String {
    return if (tags.isEmpty()) "No tags available" else tags.joinToString(", ")
}

fun hasTag(tag: String): Boolean {
    return tags.contains(tag)
}
}