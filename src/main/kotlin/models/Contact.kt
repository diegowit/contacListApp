package models

/**
 * A data class representing a contact.
 *
 * @property id The unique identifier of the contact.
 * @property firstName The first name of the contact.
 * @property lastName The last name of the contact.
 * @property phone The phone number of the contact.
 * @property email The email address of the contact.
 * @property groups The set of groups to which the contact belongs.
 * @property tags The set of tags associated with the contact.
 */
data class Contact(
    var id: Int = 0,
    var firstName: String,
    var lastName: String,
    var phone: String,
    var email: String,
    var groups: MutableSet<Group> = mutableSetOf(),
    var tags: MutableSet<String> = mutableSetOf() // Tags associated with the contact
) {
    private var lastGroupId = 0

    /**
     * Generates a unique group ID.
     */
    private fun generateGroupId() = lastGroupId++

    /**
     * Adds a group to the contact's list of groups.
     *
     * @param group The group to add.
     * @return `true` if the group was added successfully, `false` otherwise.
     */
    fun addGroup(group: Group): Boolean {
        group.groupId = generateGroupId()
        return groups.add(group)
    }

    /**
     * Returns the number of groups the contact belongs to.
     */
    fun numberOfGroups() = groups.size

    /**
     * Lists all groups the contact belongs to.
     *
     * @return A string representation of the contact's groups.
     */
    fun listAllGroups(): String {
        return if (groups.isEmpty()) "No groups available" else groups.joinToString(separator = "\n") { it.toString() }
    }

    /**
     * Adds a tag to the contact's list of tags.
     *
     * @param tag The tag to add.
     * @return `true` if the tag was added successfully, `false` otherwise.
     */
    fun addTag(tag: String): Boolean {
        return tags.add(tag)
    }

    /**
     * Removes a tag from the contact's list of tags.
     *
     * @param tag The tag to remove.
     * @return `true` if the tag was removed successfully, `false` otherwise.
     */
    fun removeTag(tag: String): Boolean {
        return tags.remove(tag)
    }

    /**
     * Lists all tags associated with the contact.
     *
     * @return A string representation of the contact's tags.
     */
    fun listTags(): String {
        return if (tags.isEmpty()) "No tags available" else tags.joinToString(", ")
    }

    /**
     * Checks if the contact has a specific tag.
     *
     * @param tag The tag to check.
     * @return `true` if the contact has the tag, `false` otherwise.
     */
    fun hasTag(tag: String): Boolean {
        return tags.contains(tag)
    }
}
