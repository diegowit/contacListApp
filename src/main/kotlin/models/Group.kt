package models

data class Group(
    val groupId: Int,
    val groupName: String,
    val groupDescription: String?,
    val members: MutableSet<Contact> = mutableSetOf()
)


/*
class Groups {
    private val groups = mutableListOf<Group>()
    private var lastId = 0

    fun add(groupName: String, groupDescription: String?): Group {
        val group = Group(getNextId(), groupName, groupDescription)
        groups.add(group)
        return group
    }

    fun getGroupById(groupId: Int): Group? {
        return groups.find { it.groupId == groupId }
    }

    fun removeGroupById(groupId: Int): Boolean {
        return groups.removeIf { it.groupId == groupId }
    }

    fun getGroups(): List<Group> {
        return groups.toList()
    }

    private fun getNextId(): Int {
        lastId += 1
        return lastId
    }

 */