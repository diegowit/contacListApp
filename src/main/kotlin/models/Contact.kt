package models
import utils.Utilities
data class Contact(
    var id: Int = 0,
    var firstName: String,
    var lastName: String,
    var phone: String,
    var email: String,
    //var groups: MutableSet<Group> = mutableSetOf()
) {
    private var lastGroupId = 0
    private fun getGroupId() = lastGroupId++

}