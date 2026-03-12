package org.example

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException


class Crawler(
    private var url: String,
    ){



    public fun getResource() : Response{

        val request = Request.Builder()
            .url(url)
            .build()
        val client = OkHttpClient()

        client.newCall(request).execute().use { response ->
            if(response.isSuccessful) {
                return response
            }else{
                throw IOException("Unexpected code $response")
            }



        }
    }

    public fun processContent(contentType : String){
        val response = getResource()
        val text = response.body!!.string()

        println(text)




        }
    }


interface Parser {
    public fun parse(text : String) : Map< * , * >
}


class JsonParser : Parser {
    override fun parse(text : String) : Map< String , String > {
        return mutableMapOf<String,String>(

        )
    }
}

class XmlParser : Parser {
    override fun parse(text : String) : Map< String , String > {
        return mutableMapOf<String,String>(

        )
    }
}

class YamlParser : Parser {
    override fun parse(text : String) : Map< String , String > {
        return mutableMapOf<String,String>(

        )
    }
}

fun main() {

    val url = "https://jsonplaceholder.typicode.com/posts/1"
    val crawler = Crawler(url)

    crawler.processContent("xml")
}