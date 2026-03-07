package org.example

import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.assertFalse
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import kotlin.collections.mutableListOf
import kotlin.test.assertNotNull
import kotlin.test.assertNull


data class Birth(val year: Int, val Month: Int, val Day: Int){
    override fun toString() : String{
        return "($Day.$Month.$year)"
    }
}

data class Contact(val Name: String, var Phone: String, val BirthDate: Birth){
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
                arrayOf("Mihai", "0744321987", 1900, 11, 25),
                arrayOf("George", "0761332100", 2002, 3, 14),
                arrayOf("Liviu", "0231450211", 1999, 7, 30),
                arrayOf("Popescu", "0211342787", 1955, 5, 12),
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

}

fun findFromNumber(agenda : MutableList<Contact>, numberToFind : String) : Contact? {
    val rez = agenda.find { numberToFind== it.Phone }
    return rez
}

@RunWith(Parameterized::class)
class Lab3ex3FindFromNumber(
    private val numberToFind : String,
    private val shouldExist : Boolean,
    ) {
    companion object {
        @JvmStatic
        @Parameters
        fun data() : Collection<Array<Any>> {

            return listOf(
                arrayOf("0744321987",true),
                arrayOf("0723231231",false),
            )
        }

        val agenda = mutableListOf<Contact>()

    }

    @Before
    fun initList(){
        agenda.add(Contact ("Mihai", "0744321987", Birth(1900, 11, 25)))
        agenda.add(Contact ("George", "0761332100", Birth(2000, 3, 14)))
        agenda.add(Contact ("Liviu", "0231450211", Birth(1999, 11, 30)))
        agenda.add(Contact ("Popescu", "0211342787", Birth(1955, 9, 25)))
    }

    @Test
    fun testFindFromNumber() {
        val aGasit = findFromNumber(agenda,numberToFind)
        if(shouldExist){
            assertTrue( aGasit!=null)
        }else{
            assertNull(aGasit)
        }
    }

    @After
    fun resetList(){
        agenda.clear()
    }
}

fun updatePhone(agenda: MutableList<Contact>, oldPhoneNumber: String, newPhoneNumber: String): Boolean {
    val persoana = findFromNumber(agenda, oldPhoneNumber)

    if (persoana != null) {
        persoana.Phone = newPhoneNumber
        return true
    }

    return false
}

@RunWith(Parameterized::class)
class Lab3ex3UpdatePhone(
    private val numberToFind : String,
    private val shouldExist : Boolean,
) {
    companion object {
        @JvmStatic
        @Parameters
        fun data() : Collection<Array<Any>> {

            return listOf(
                arrayOf("0744321987",true),
                arrayOf("0778272722",false),
            )
        }

        val agenda = mutableListOf<Contact>()

    }

    @Before
    fun initList(){
        agenda.add(Contact ("Mihai", "0744321987", Birth(1900, 11, 25)))
        agenda.add(Contact ("George", "0761332100", Birth(2000, 3, 14)))
        agenda.add(Contact ("Liviu", "0231450211", Birth(1999, 11, 30)))
        agenda.add(Contact ("Popescu", "0211342787", Birth(1955, 9, 25)))
    }

    @Test
    fun testUpdateNumber() {
        val numarNou = "0700000000"
        val decizie = updatePhone(agenda, numberToFind, numarNou)

        if (shouldExist) {
            assertTrue(decizie)

            val persoanaActualizata = findFromNumber(agenda, numarNou)
            assertNotNull(persoanaActualizata)

            val persoanaVeche = findFromNumber(agenda, numberToFind)
            assertNull(persoanaVeche)
        } else {
            assertFalse(decizie)
        }
    }

    @After
    fun resetList(){
        agenda.clear()
    }
}


