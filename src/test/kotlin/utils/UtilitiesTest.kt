package utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import utils.Utilities.validRange
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

class UtilitiesTest {

    @Test
    fun validRangeWorksWithPositiveTestData() {
        Assertions.assertTrue(validRange(1, 1, 1))
        Assertions.assertTrue(validRange(1, 1, 2))
        Assertions.assertTrue(validRange(1, 0, 1))
        Assertions.assertTrue(validRange(1, 0, 2))
        Assertions.assertTrue(validRange(-1, -2, -1))
    }

    @Test
    fun validRangeWorksWithNegativeTestData() {
        Assertions.assertFalse(validRange(1, 0, 0))
        Assertions.assertFalse(validRange(1, 1, 0))
        Assertions.assertFalse(validRange(1, 2, 1))
        Assertions.assertFalse(validRange(-1, -1, -2))
    }

    @Test
    fun `readValidPhone returns valid phone number`() {
        val input = "1234567890\n"
        val expectedPhoneNumber = "1234567890"

        val originalSystemIn = System.`in`
        val originalSystemOut = System.out
        val byteArrayInputStream = ByteArrayInputStream(input.toByteArray())
        val byteArrayOutputStream = ByteArrayOutputStream()
        System.setIn(byteArrayInputStream)
        System.setOut(PrintStream(byteArrayOutputStream))

        val actualPhoneNumber = ValidateInput.readValidPhone("Enter phone number: ")

        System.setIn(originalSystemIn)
        System.setOut(originalSystemOut)

        assertEquals(expectedPhoneNumber, actualPhoneNumber)
    }

    @Test
    fun `readValidEmail returns valid email address`() {
        val input = "test@example.com\n"
        val expectedEmail = "test@example.com"

        val originalSystemIn = System.`in`
        val originalSystemOut = System.out
        val byteArrayInputStream = ByteArrayInputStream(input.toByteArray())
        val byteArrayOutputStream = ByteArrayOutputStream()
        System.setIn(byteArrayInputStream)
        System.setOut(PrintStream(byteArrayOutputStream))

        val actualEmail = ValidateInput.readValidEmail("Enter email address: ")

        System.setIn(originalSystemIn)
        System.setOut(originalSystemOut)

        assertEquals(expectedEmail, actualEmail)
    }
}
