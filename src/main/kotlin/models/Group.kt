package models

data class Group( var groupId: Int = 0, var groupName: String, var type: Boolean = false){
    override fun toString() =
        if (type)
            "$groupId: $groupName (Family)"

    else
        "$groupId: $groupName (Work)"
}