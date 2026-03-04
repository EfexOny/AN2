package org.example

import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.assertFalse
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)

class Birth(val year: Int, val Month: Int, val Day: Int){
    override fun toString() : String{
        return "($Day.$Month.$year)"
    }
}

class Contact(val Name: String, val Phone: String, val BirthDate: Birth){
    fun Print() {
        println("Name: $Name, Mobile: $Phone, Date: $BirthDate")
    }
}
@RunWith(Parameterized::class)
class Lab3Ex3AgendaTest(
    private val name : String,
    private val number : String,
    private val birthYear : Int,
    private val birthMonth : Int,
    private val birthDay : Int,
    ) {
    companion object {
        @JvmStatic
        @Parameters
        fun data() : Collection<Array<Any>> {

            return listOf(
                arrayOf("Mihai", "0744321987", 1900, 11, 25, "(25.11.1900)"),
                arrayOf("George", "0761332100", 2002, 3, 14, "(14.3.2002)"),
                arrayOf("Liviu", "0231450211", 1999, 7, 30, "(30.7.1999)"),
                arrayOf("Popescu", "0211342787", 1955, 5, 12, "(12.5.1955)"),
            )
        }
        val agenda = mutableListOf<Contact>()




    }

    @Test
    fun testCreare() {
        val birth = Birth(birthYear, birthMonth, birthDay )
        val contact = Contact(name,number,birth);
        agenda.add(contact)
        assertTrue(agenda.contains(contact) && name==contact.Name && number==contact.Phone && birth==contact.BirthDate)
    }

    @Test
    fun testStergere() {
        
    }
}
