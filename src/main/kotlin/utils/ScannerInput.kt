package utils

import java.util.Scanner

object ScannerInput {
    private val scanner = Scanner(System.`in`)

    fun readString(prompt: String?): String {
        print(prompt)
        return scanner.nextLine()
    }

    fun readInt(prompt: String?): Int {
        while (true) {
            print(prompt)
            try {
                return scanner.nextInt()
            } catch (e: Exception) {
                scanner.nextLine() // clear invalid input
                println("Please enter a valid integer.")
            }
        }
    }

    fun readDouble(prompt: String?): Double {
        while (true) {
            print(prompt)
            try {
                return scanner.nextDouble()
            } catch (e: Exception) {
                scanner.nextLine() // clear invalid input
                println("Please enter a valid double.")
            }
        }
    }

    fun readChar(prompt: String?): Char {
        while (true) {
            print(prompt)
            val input = scanner.nextLine()
            if (input.length == 1) {
                return input[0]
            }
            println("Please enter a single character.")
        }
    }
}
