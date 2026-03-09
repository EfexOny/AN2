package org.example

import org.jsoup.Jsoup
import org.jsoup.parser.Parser
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized.Parameters
import org.junit.runners.Parameterized

data class Items(
    val title: String,
    val link: String,
    val description: String,
    val pubDate: String,
)

data class AllData(
    val title: String,
    val link: String,
    val description: String,
    val pubDate: String,
    val items : List<Items>
    )

@RunWith(Parameterized::class)
class Lab3Tema1(
    private val url: String,
){
    companion object{
        @JvmStatic
        @Parameters
        fun data(): Collection<Array<String>> {
            return listOf(
                arrayOf("http://rss.cnn.com/rss/edition.rss")
            )
        }

    }
@Test
fun tema1() {
    val doc = Jsoup.connect(url)
        .get()

    val title = doc.select("channel > title").text()
    val link = doc.select("channel > link").text()
    val desc = doc.select("channel > description").text()
    val date = doc.select("channel > pubdate").text()
    val items = doc.select("item").map { element ->
        Items(
            title = element.select("title").text(),
            link = element.select("link").text(),
            description = element.select("description").text(),
            pubDate = element.select("pubDate").text(),
        )

    }
    val allData = AllData(
        title,
        link,
        desc,
        date,
        items
    )



    println("--- titlu: ${allData.title} ---")
    for (element in allData.items) {
        println("--- titlu: ${element.title} ---")
        println("descriere: ${element.description} ---")
        println("date: ${element.pubDate} ---")
        println("Link:  ${element.link}")
        }
    }
}