package controller

import models.Contact
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import persistence.JSONSerializer
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ContactAPITest {

    private var contact1: Contact? = null
    private var contact2: Contact? = null
    private var contact3: Contact? = null
    private var addingContacts: ContactAPI? = null
    private var emptyContacts: ContactAPI? = null

    @BeforeEach
    fun setup() {
        contact1 = Contact(0, "Diego", "Loppez", "123456789", "j@gmail.com")
        contact2 = Contact(1, "Edgar", "Torres", "987654321", "e@gmail.com")
        contact3 = Contact(2, "Lukas", "Perez", "555555555", "a@gmail.com")

        // Initialize contactAPI instances
        addingContacts = ContactAPI(JSONSerializer(File("contact.json")))
        emptyContacts = ContactAPI(JSONSerializer(File("contact.json")))

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
        emptyContacts = null
    }

    @Test
    fun `adding a contact adds to the contact list`() {
        val newContact = Contact(10, "Loco", "Lopez", "1234567895", "lp@gmail.com")
        assertTrue(addingContacts!!.add(newContact))
        assertEquals(4, addingContacts!!.numberOfContacts())
        assertEquals(newContact, addingContacts!!.findContact(addingContacts!!.numberOfContacts() - 1))
    }

    @Nested
    inner class UpdateContact {
        @Test
        fun `updating a Contact that does not exist returns false`() {
            assertFalse(
                addingContacts!!.updateContact(
                    10,
                    Contact(10, "Updated", "Contact", "1234567895", "updated@gmail.com")
                )
            )
            assertFalse(
                emptyContacts!!.updateContact(
                    0,
                    Contact(0, "Updated", "Contact", "1234567895", "updated@gmail.com")
                )
            )
        }


        @Nested
        inner class DeleteContacts {

            @Test
            fun `deleting a Contact that does not exist returns false`() {
                assertFalse(emptyContacts!!.delete(0))
                assertFalse(addingContacts!!.delete(10))
                assertFalse(addingContacts!!.delete(3))
            }

            @Test
            fun `deleting a Contact that exists deletes and returns true`() {
                assertEquals(3, addingContacts!!.numberOfContacts())
                assertTrue(addingContacts!!.delete(2))
                assertEquals(2, addingContacts!!.numberOfContacts())
                assertTrue(addingContacts!!.delete(1))
                assertEquals(1, addingContacts!!.numberOfContacts())
            }
        }


        @Nested
        inner class ListContacts {

            @Test
            fun `listAllContacts returns No Contacts Available message when ArrayList is empty`() {
                assertEquals(0, emptyContacts!!.numberOfContacts())
                assertTrue(emptyContacts!!.listAllContacts().lowercase().contains("no contacts available"))
            }

            @Test
            fun `listAllContacts returns Contacts when ArrayList has Contacts stored`() {
                assertEquals(3, addingContacts!!.numberOfContacts())
                val contactsString = addingContacts!!.listAllContacts().lowercase()
                assertTrue(contactsString.contains("diego"))
                assertTrue(contactsString.contains("edgar"))
                assertTrue(contactsString.contains("lukas"))
            }
        }


    }
}
