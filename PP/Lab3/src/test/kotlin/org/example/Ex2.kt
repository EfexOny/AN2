package org.example

import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.assertFalse
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import java.io.File

@RunWith(Parameterized::class)
class Lab3Ex2HttpGetRequestTest(
    private val url: String,
    private val responseNotEmpty: Boolean,
) {
    private val client: OkHttpClient = OkHttpClient()

    companion object {
        @JvmStatic
        @Parameters
        fun data() : Collection<Array<Any>> {
            return listOf(
                arrayOf("https://www.google.com", true),
                arrayOf("https://khttp.readthedocs.io/en/latest/", true),
                arrayOf("https://example.com", true),
            )
        }
    }

    @Test
    fun testHttpGetRequest() {
        try {
            val request: Request = Request.Builder()
                .url(url)
                .build()

            client.newCall(request).execute().use { response ->
                if (responseNotEmpty) {
                    assertFalse("GET request to ${url} returned NOT be empty!", response.body.string().isEmpty())
                } else {
                    val body = response.body.string()
                    assertTrue("GET request to ${url} expected to be empty! Actual response: ${body}", body.isEmpty())
                }
            }
        } catch (ex: IllegalArgumentException) {
            println(ex.message)
        }
    }
}

@RunWith(Parameterized::class)
class Lab3Ex2JsoupParseTest(
    private val source: String,
    private val url: String,
    private val baseUri: String,
    private val cssHeadSelector : String,
    private val cssParagraphSelector : String,
    private val cssLinkSelector: String,
) {
    companion object {
        @JvmStatic
        @Parameters
        fun data() : Collection<Array<Any>> {
            var projectPath = System.getProperty("user.dir")

            var htmlContent = ""
            try {
                val client: OkHttpClient = OkHttpClient()
                val request: Request = Request.Builder()
                    .url("https://khttp.readthedocs.io/en/latest/")
                    .build()
                client.newCall(request).execute().use { response ->
                    htmlContent = response.body.string()
                }
            } catch (e: Exception) {
                println("Warning: Could not fetch HTML content: ${e.message}")
                htmlContent = "<html><head><title>Fallback</title></head><body><h1>Fallback</h1><p>No network</p></body></html>"
            }

            return listOf(
                arrayOf("url", "https://khttp.readthedocs.io/en/latest/", "", "h1", "p", "a[href]"),
                arrayOf("file", "${projectPath}/src/main/resources/example.html", "", "h1", "p", "a[href]"),
                arrayOf("string", htmlContent, "", "h1", "p", "a[href]")
            )
        }
    }

    @Test
    fun testHttpGetRequest() {
        var htmlDocument: Document?
        htmlDocument = when(source) {
            "url" -> Jsoup.connect(url).get()
            "file" -> Jsoup.parse(File(url), "UTF-8", baseUri)
            "string" -> Jsoup.parse(url)
            else -> throw Exception("Unknown source")
        }
        println(htmlDocument.title())
        println(htmlDocument.select(cssHeadSelector).text())
        val paragraphs: Elements = htmlDocument.select(cssParagraphSelector)
        for (paragraph in paragraphs) {
            println("\t${paragraph.text()}")
        }
        val links = htmlDocument.select(cssLinkSelector)
        println("-".repeat(100))
        for (link in links) {
            println("${link.text()}\n\t${link.absUrl("href")}")
        }
    }
}
