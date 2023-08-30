package controller


import models.Contact
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ContactAPITest {

    private var contact1: Contact? = null
    private var contact2: Contact? = null
    private var contact3: Contact? = null
    private var addingContacts: ContactAPI? = null

    @BeforeEach
    fun buildUp() {
        contact1 = Contact(0, "Diego", "Loppez", "123456789", "j@gmail.com")
        contact2 = Contact(1, "Edgar", "Torres", "987654321", "e@gmail.com")
        contact3 = Contact(2, "Lukas", "Perez", "555555555", "a@gamil.com")


        // Adding contacts
        addingContacts!!.add(contact1!!)
        addingContacts!!.add(contact2!!)
        addingContacts!!.add(contact3!!)
    }


    @AfterEach
    fun tearDown() {
        contact1 = null
        contact2 = null
        contact3 = null
        addingContacts = null

        File("contacts.test.json").delete()
    }

    /*
    @Nested
    inner class FindingMethods {
        @Test
        fun `findContact returns the correct contact`() {
            assertEquals(contact1, addingContacts!!.findContact(contact1!!.id))
            assertEquals(contact2, addingContacts!!.findContact(contact2!!.id))
        }

        @Test
        fun `findContact returns null when contact not found`() {
            assertNull(addingContacts!!.findContact(-1))
        }
    }
}
*/

    @Nested
    inner class UpdatingContacts {
        @Test
        fun `updateContact updates the contact details`() {
            val updatedContact = Contact(0, "Diego", "Loppez", "123456789", "j@gmail.com")
            assertTrue(addingContacts!!.updateContact(contact2!!.id, updatedContact))
            assertEquals(updatedContact.firstName, contact2!!.firstName)
            assertEquals(updatedContact.lastName, contact2!!.lastName)
            assertEquals(updatedContact.email, contact2!!.email)
            assertEquals(updatedContact.phone, contact2!!.phone)
        }

        @Test
        fun `updateContact returns false for non-existing contact`() {
            val updatedContact = Contact(3, "Di", "Lop", "1234567895", "ll@gmail.com")
            assertFalse(addingContacts!!.updateContact(-1, updatedContact))
        }
    }

}