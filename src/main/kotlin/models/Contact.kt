package models
import models.Group
import utils.Utilities
data class Contact(
    var id: Int = 0,
    var firstName: String,
    var lastName: String,
    var phone: String,
    var email: String,
    var groups: MutableSet<Group> = mutableSetOf()
    //var groups: MutableSet<Group> = mutableSetOf()
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
}