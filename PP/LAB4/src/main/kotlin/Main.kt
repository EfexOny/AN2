package org.example

class Content(
    private var author: String,
    private var text : String,
    private var name : String,
    private var publisher : String){

    public fun getAuthor() : String {
        return author
    }

    public fun setAuthor(newAuthor : String)  {
        author=newAuthor
    }

    public fun getText() : String {
        return text
    }

    public fun setText(newText : String)  {
        text = newText
    }
    public fun getName() : String {
        return name
    }

    public fun setName(newName : String)  {
        name= newName
    }
    public fun getPublisher() : String {
        return publisher
    }

    public fun setPublisher(newPublisher : String) {
        publisher=newPublisher;
    }

}

open class Book(
    var data : Content,
    ){
    public override fun toString() : String {
        return super.toString()
    }

    public fun getName() : String {
        return data.getName()
    }
    public fun getAuthor() : String {
        return data.getAuthor()
    }
    public fun getPublisher() : String {
        return data.getPublisher()
    }
    public fun getContent() : String {
        return data.getText()
    }

    public fun hasAuthor(cauta : String) : Boolean{
        return data.getAuthor().contains(cauta)
    }

    public fun hasTitle(titlu : String) : Boolean{
        return data.getName().contains(titlu)
    }

    public fun isPublishedBy(publicant : String) : Boolean{
        return data.getPublisher().contains(publicant)
    }
}

class CarteCuPret(
    data : Content,
    var price : Double,
) : Book(data){
}

class Library(var books : Set<Book>) {
    public fun getBooks(): Set<Book>{
        return books
    }
    public fun addBook(book : Book){
        books = books.plus(book)
    }

    public fun findAllByAuthor(autor : String) : Set<Book>{
        var setRet : Set<Book> = emptySet()
        for (carte in books ){
            if(carte.getAuthor() == autor){
                setRet = setRet.plus(carte)
            }
        }
        return setRet
    }

    public fun findAllByName(nume : String) : Set<Book>{
        var setRet : Set<Book> = emptySet()
        for (carte in books ){
            if(carte.getName() == nume){
                setRet = setRet.plus(carte)
            }
        }
        return setRet
    }

    public fun findAllByPublisher(publicant : String) : Set<Book>{
        var setRet : Set<Book> = emptySet()
        for (carte in books ){
            if(carte.getPublisher() == publicant){
                setRet = setRet.plus(carte)
            }
        }
        return setRet
    }
}

class LibraryPrinter( ) {
    public fun printBooksRaw(carti : Set<Book>){
//        cod
    }
    public fun printHTML(carti : Set<Book>){
//        cod
    }
    public fun printJSON(carti : Set<Book>){
//        cod
    }
}



fun main() {

    }
