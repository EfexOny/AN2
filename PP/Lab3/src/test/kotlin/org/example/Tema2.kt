package org.example

import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.Test

fun <T> proc(text : T): String{
    val rez=text.toString()
    return rez
        .replace(Regex("""\s+\d+\s+"""), "\n")
        .replace(Regex(" +")," ")
        .replace(Regex("""\\n+"""),"\n")


}

@RunWith(Parameterized::class)
class Lab3Tema2(
    private val input: String,
){

    companion object{
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<String>> {
            return listOf(
                arrayOf("""
                        Capitolul 1: Introducere
                        
                        
                        Aceasta este o propoziție    cu multe spații inutile.
                        
                        
                              24
                    
                        """
                )
            )
        }

    }


    @Test
    fun testManipulareEbook(){
        print(proc(input))

    }
}