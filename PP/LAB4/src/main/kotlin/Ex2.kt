class informatii(
    var titlu : String,
    var durata : Int,
    var genul : String,
    var distributie : String,
    var regizor : String,
    var productie:  String,
    var clasificare: String,
){

//    GETTERS
    public fun getTitlu() : String{
        return titlu
    }

    public fun getDurata() : Int{
        return durata
    }
    public fun getGenul() : String{
        return genul
    }
    public fun getDistributie() : String{
        return distributie
    }
    public fun getProductie() : String{
        return productie
    }
//    SETTERS
    public fun setTitlu(titlu : String){
        this.titlu=titlu
    }
    public fun setDurata(durata : Int){
        this.durata=durata
    }
    public fun setGenul(genul : String){
        this.genul=genul
    }
    public fun setDistribuite(distributie : String){
        this.distributie=distributie
    }
    public fun setProductie(productie : String){
        this.productie=productie
    }
}

class Film(
    var data : informatii,
    var pret : Double,
){
    public fun getData(){

    }
    public fun getPret(){

    }
}

class Cinema(
   var filme : Set<Film>
){
    public fun getFilme() : Set<Film>{
        return emptySet()
    }

    public fun addFilm(film : Film){
        filme = filme.plus(film)
    }
}


interface Payment{
    public fun pay(price:Double) : Boolean
}

class CashPayment (availAmm : Double): Payment{

    public override fun pay(price:Double) : Boolean{
        return false
    }
}

class CardPayment (bankAccNumber : String): Payment{

    public override fun pay(price:Double) : Boolean{
        return false
    }
}

class BookPlace(){
    public fun sellTicket(
        film : Film,
        paymentMethod : Payment,
    ) : Boolean{
        return true
    }
}

