package models

data class Group(
    val groupId: Int,
    val groupName: String,
    val groupDescription: String?,
    val members: MutableSet<Contact> = mutableSetOf()
)