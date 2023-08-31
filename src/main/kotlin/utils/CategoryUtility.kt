package utils
import java.util.*

object CategoryUtility {

    // NOTE: JvmStatic annotation means that the categories variable is static (i.e. we can reference it through the class
    //      name; we don't have to create an object of CategoryUtility to use it.
    fun isValidPhone(phoneNumber: String): Boolean {
        val regex = "^[+]?[0-9]{10,13}\$"
        return phoneNumber.matches(regex.toRegex())
    }

    @JvmStatic
    fun isValidEmail(email: String): Boolean {
        val regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@[\\w]+\\.[\\w]+\$"
        return email.matches(regex.toRegex())
    }
}
