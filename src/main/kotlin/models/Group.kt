package models

/**
 * A data class representing a group.
 *
 * @property groupId The unique identifier of the group.
 * @property groupName The name of the group.
 * @property type The type of the group (default is "Work").
 */
data class Group(
    var groupId: Int = 0,
    var groupName: String,
    var type: String = "Work"
) {
    /**
     * Returns a string representation of the group.
     *
     * @return The formatted string containing group ID, group name, and type.
     */
    override fun toString() = "$groupId: $groupName ($type)"
}
