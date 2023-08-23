package models

data class Group(var groupId: Int = 0, var groupName: String, var type: String = "Work") {
    override fun toString() = "$groupId: $groupName ($type)"
}