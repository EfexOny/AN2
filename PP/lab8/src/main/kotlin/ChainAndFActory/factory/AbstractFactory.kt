package ChainAndFActory.factory

import ChainAndFActory.chain.Handler

abstract class AbstractFactory {
    abstract fun getHandler(handler: String): Handler
}