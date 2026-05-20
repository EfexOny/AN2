package com.pp.laborator

import kotlinx.coroutines.*
import kotlin.system.*
import kotlinx.coroutines.channels.*

sealed class ContorMsg

object IncContor : ContorMsg()

class GetContor(val response: CompletableDeferred<Int>) : ContorMsg()

fun CoroutineScope.counterActor() = actor<ContorMsg> {
    var contor = 0
    for (msg in channel) {
        when (msg) {
            is IncContor -> contor++
            is GetContor -> msg.response.complete(contor)
        }
    }
}

suspend fun CoroutineScope.massiveRun(action: suspend () -> Unit) {
    val n = 100
    val k = 1000
    val timp = measureTimeMillis {
        val jobs = List(n) {
            launch {
                repeat(k) { action() }
            }
        }
        jobs.forEach { it.join() }
    }
    println("Am terminat atatea ${n * k} actiuni in $timp ms")
}

fun main() = runBlocking<Unit> {
    val contor = counterActor()
    GlobalScope.massiveRun()
    {
        contor.send(IncContor)
        println(contor.onSend)
    }
    val raspuns = CompletableDeferred<Int>()
    contor.send(GetContor(raspuns))
    println("Contor = ${raspuns.await()}")
    contor.close()
}
