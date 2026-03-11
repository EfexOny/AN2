package org.example

import java.net.http.HttpResponse



class Crawler(
    private var url : String, ){



    public fun getResource() : HttpResponse<String>{

    }

    public fun processContent(contentType : String){

    }

}

interface Parser {
    public fun Parse(text : String) : Map< Int , String >
}


class JsonParser : Parser {
    override fun Parse(text : String) : Map< Int , String > {
        return mutableMapOf<Int,String>(

        )
    }
}

class XmlParser : Parser {
    override fun Parse(text : String) : Map< Int , String > {
        return mutableMapOf<Int,String>(

        )
    }
}

class YamlParser : Parser {
    override fun Parse(text : String) : Map< Int , String > {
        return mutableMapOf<Int,String>(

        )
    }
}