package org.example

class Content(){

}

class Book(){

}

class Library (books : MutableSet<Book>){

    fun getBooks() :Set<Book>{
        return emptySet()
    }

    fun addBook(book: Book){

    }

    fun findByAuthor(autor : String) : Set<Book>{
        return emptySet()
    }
    fun findByName(autor : String) : Set<Book>{
        return emptySet()
    }
    fun findByPublisher(autor : String) : Set<Book>{
        return emptySet()
    }
}

fun main() {
        print("Hello World!")
    }
