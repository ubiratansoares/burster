package io.dotanuki.burster

fun <A, B, C, D, E> using(block: Buster5<A, B, C, D, E>.() -> Unit) =
    Buster5<A, B, C, D, E>().apply { block() }.execute()

class Buster5<A, B, C, D, E> {

    private val container by lazy { Bullets5<A, B, C, D, E>() }
    private var target: ((A, B, C, D, E) -> Unit)? = null

    fun burst(block: Bullets5<A, B, C, D, E>.() -> Unit) {
        container.apply { block() }
    }

    fun thenWith(assertion: (A, B, C, D, E) -> Unit) {
        target = assertion
    }

    internal fun execute() = with(container.bullets) {
        if (isEmpty()) throw BursterError.NoValues
        forEach {
            target
                ?.invoke(it.a, it.b, it.c, it.d, it.e)
                ?: throw BursterError.NoTarget
        }
    }
}

class Bullets5<A, B, C, D, E> {

    internal val bullets = mutableListOf<T5<A, B, C, D, E>>()

    fun values(a: A, b: B, c: C, d: D, e: E) {
        bullets += T5(a, b, c, d, e)
    }
}



