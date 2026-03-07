package org.example

import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import java.io.File
import kotlin.test.assertFalse


@RunWith(Parameterized::class)
class Ex4(
    private val tecst : String ,
    private val expectedTecst: String,
    private val shouldMatch: Boolean,
    ) {
    companion object {
        @JvmStatic
        @Parameters
        fun data() : Collection<Array<Any>> {
            return listOf(
                    arrayOf("Once upon a time there was an old woman who loved baking gingerbread. " +
                    "She would bake gingerbread cookies, cakes, " +
                    "houses and gingerbread people, all decorated with chocolate and " +
                    "peppermint, caramel candies and colored ingredients.",

                    "Odata ca  niciodata acolo a fost o batrana femeie care iubea sa gateasca turta dulce Ea " +
                    "ar fi gatit turta dulce biscuiti prajituri case si " +
                    "turta dulce oameni toti decorati cu ciocolata si " +
                    "menta caramel bomboane si colorate ingrediente. " ,
                        true
                    )
            )
        }
        val Dictionar = hashMapOf<String, String>(
            "Once"                to  "Odata",
            "upon"                to  "ca",
            "a"                   to  "",
            "time"                to  "niciodata",
            "there"               to  "acolo",
            "was"                 to  "a fost",
            "an"                  to  "o",
            "old"                 to  "batrana",
            "woman"               to  "femeie",
            "who"                 to  "care",
            "loved"               to  "iubea",
            "baking"              to  "sa gateasca",
            "gingerbread"         to  "turta dulce",
            "She"                 to  "Ea",
            "would"               to  "ar fi",
            "bake"                to  "gatit",
            "gingerbread"         to  "turta dulce",
            "cookies"             to  "biscuiti",
            "cakes"               to  "prajituri",
            "houses"              to  "case",
            "and"                 to  "si",
            "people"              to  "oameni",
            "all"                 to  "toti",
            "decorated"           to  "decorati",
            "with"                to  "cu",
            "chocolate"           to  "ciocolata",
            "peppermint"          to  "menta",
            "caramel"             to  "caramel",
            "candies"             to  "bomboane",
            "colored"             to  "colorate",
            "ingredients"         to  "ingrediente"
        )

    }

    @Test
    fun testTranslate(){
        val poveste = tecst ;
        val words1 = poveste.split(" ")

        val words2 = mutableListOf<String>()
        for (word in words1){
            words2.add(word.trim(',','.'))
        }
        println("\n")

        val traducerea = mutableListOf<String>();

        for (item in words2){
            if (Dictionar.contains(item)) {
                traducerea.add(Dictionar[item] ?: "")
            }
            else {
                traducerea.add(item)
            }
        }
        val adev = traducerea.joinToString(" ") + ". "

        File("traducere.txt").writeText(adev)
        File("").walk().filter { it.name.equals("traducere.txt") }  

        if(shouldMatch){
            assertTrue(adev.equals(expectedTecst))
        }else{
            assertFalse { adev.equals(expectedTecst) }
        }
    }
}
