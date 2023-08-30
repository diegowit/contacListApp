package utils



import java.util.*

object ValidateInput {

    @JvmStatic
    fun readValidPhone(prompt: String?): String {
        print(prompt)
        var input = Scanner(System.`in`).nextLine()
        do {
            if (CategoryUtility.isValidPhone(input))
                return input
            else {
                print("Invalid phone number $input. Please enter a valid phone number: ")
                input = Scanner(System.`in`).nextLine()
            }
        } while (true)
    }
    @JvmStatic
    fun readValidEmail(prompt: String?): String {
        print(prompt)
        var input = Scanner(System.`in`).nextLine()
        do {
            if (CategoryUtility.isValidEmail(input))
                return input
            else {
                print("Invalid email address $input. Please enter a valid email address: ")
                input = Scanner(System.`in`).nextLine()
            }
        } while (true)
    }
}