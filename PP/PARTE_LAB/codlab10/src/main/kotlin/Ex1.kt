

val eps = 1E-10 // suficient, dar ar putea fi si 10^(-15)

private fun findFixPoint(): Double {
    var x = 1.0
    while (true) {
        val y = Math.cos(x)
        if (Math.abs(x - y) < eps) return x
        x = Math.cos(x)
    }
}

tailrec suspend fun fibonacci(n: Int, a: Long, b: Long): Long {
    return if (n == 0) a else fibonacci(n-1, b, a+b)
}

suspend fun main(){
    fibonacci(3,1,2)
}