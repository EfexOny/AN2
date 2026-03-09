package org.example

import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.arrayOf
import kotlin.test.Test

@RunWith(Parameterized::class)
class Lab3Tema3(){
    companion object{
        @JvmStatic
        @Parameterized.Parameters
        fun data() : Collection<Array<String>>  {
            return listOf(
                        arrayOf(
                            ""
                        )
                    )
        }
    }



    @Test
    fun serialize() {

    }

    @Test
    fun deserialize(){

    }

}