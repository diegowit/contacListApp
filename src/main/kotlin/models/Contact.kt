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

fun findOneG(id: Int): Group? {
    return groups.find { group -> group.groupId == id }
}


fun delete(groupId: Int): Boolean {
   return groups.removeIf{ group -> group.groupId == id}
}


fun update(groupId: Int, updatedGroup: Group): Boolean {
    val groupToUpdate = findOneG(groupId)
    if (groupToUpdate != null) {
        groupToUpdate.apply {
            groupToUpdate.groupName = updatedGroup.groupName
            groupToUpdate.type = updatedGroup.type
        }
        return true
    }
    return false
}

    fun findGroupByName(name: String): Group? {
        return groups.find { group -> group.groupName == name }
    }



    fun checkContactGroupType(): Boolean {
        if (groups.isNotEmpty()) {
            for (group in groups) {
                if (!group.type) {
                    return false
                }
            }
        }
        return true //a note with empty items can be archived, or all items are complete
    }

fun listGroups() =
    if (groups.isEmpty())
        "No groups stored"
     else Utilities.formatSetString(groups)

}