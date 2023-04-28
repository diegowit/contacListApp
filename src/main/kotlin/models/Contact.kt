package models
data class Contact(
    var id: Int = 0,
    var firstName: String,
    var lastName: String,
    var phoneNumber: String,
    var emailAddress: String,
    var company: String? = null,
    var notes: String? = null,
    var groups: MutableSet<Group> = mutableSetOf()
)
