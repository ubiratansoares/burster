package io.dotanuki.burster

fun <A, B, C> using(block: Buster3<A, B, C>.() -> Unit) =
    Buster3<A, B, C>().apply { block() }.execute()

class Buster3<A, B, C> {

    private val container by lazy { Bullets3<A, B, C>() }
    private var target: ((A, B, C) -> Unit)? = null

    fun burst(block: Bullets3<A, B, C>.() -> Unit) {
        container.apply { block() }
    }

    fun thenWith(assertion: (A, B, C) -> Unit) {
        target = assertion
    }

    internal fun execute() = with(container.bullets) {
        if (isEmpty()) throw BursterError.NoValues
        forEach {
            target
                ?.invoke(it.a, it.b, it.c)
                ?: throw BursterError.NoTarget
        }
    }
}

class Bullets3<A, B, C> {

    internal val bullets = mutableListOf<T3<A, B, C>>()

    fun values(a: A, b: B, c: C) {
        bullets += T3(a, b, c)
    }
}

