package org.example

import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.assertFalse
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class Lab3Exemplu1Test(
    private val regex : Regex,
    private val testInput : String,
    private val shouldMatch : Boolean) {
    companion object {
        @JvmStatic
        @Parameters
        fun data() : Collection<Array<Any>> {
            val testString = "link/ether a0:b1:c2:d3:e4:f5 brd ff:ff:ff:ff:ff:ff\n" +
                    "inet 192.168.0.2/24 brd 192.168.0.255 scope global eno1\n" +
                    "Hi,\n You can contact me at john.smith@gmail.com\n" +
                    "You should use a search engine like www.duckduckgo.com\n" +
                    "I'll meet you at 08:00 AM tomorrow"
            val stringWithDuplicates = "one two two three three three four four four four"

            val ipRegex = Regex("((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)")
            val emailRegex = Regex("\\w+[+.\\w-]*@([\\w-]+.)*\\w+[\\w-]*.([a-z]{2,4}|\\d+)")
            val urlRegex = Regex("(https?:\\/\\/)?www\\.[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)")
            val timeRegex = Regex("(0?[1-9]|1[0-2]):[0-5][0-9]")
            val duplicatesRegex = Regex("(\\b\\w+\\b)(?=.*\\b\\1\\b)")
            val splitRegex = Regex("\\d+")

            return listOf(
                arrayOf(ipRegex, testString, true),
                arrayOf(emailRegex, testString, true),
                arrayOf(urlRegex, testString, true),
                arrayOf(timeRegex, "08:00", true),
                arrayOf(timeRegex, "Tomorrow at 09:15", true),
                arrayOf(timeRegex, "08:00", true),
                arrayOf(duplicatesRegex, stringWithDuplicates, true),
                arrayOf(splitRegex, "This10text20is30splitted40by50regex", true),
                arrayOf(ipRegex, stringWithDuplicates, false)
            )
        }
    }

    @Test
    fun testRegex() {
        if (shouldMatch) {
            assertTrue("Expected /${regex.pattern}/ to match '${testInput}'", testInput.matches(regex))
        } else {
            assertFalse("Expected /${regex.pattern}/ to NOT match '${testInput}'", testInput.matches(regex))
        }
    }
}
