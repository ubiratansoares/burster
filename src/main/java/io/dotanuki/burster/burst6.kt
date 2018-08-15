package io.dotanuki.burster

fun <A, B, C, D, E, F> using(block: Buster6<A, B, C, D, E, F>.() -> Unit) =
    Buster6<A, B, C, D, E, F>().apply { block() }.execute()

class Buster6<A, B, C, D, E, F> {

    private val container by lazy { Bullets6<A, B, C, D, E, F>() }
    private var target: ((A, B, C, D, E, F) -> Unit)? = null

    fun burst(block: Bullets6<A, B, C, D, E, F>.() -> Unit) {
        container.apply { block() }
    }

    fun thenWith(assertion: (A, B, C, D, E, F) -> Unit) {
        target = assertion
    }

    internal fun execute() = with(container.bullets) {
        if (isEmpty()) throw BursterError.NoValues
        forEach {
            target
                ?.invoke(it.a, it.b, it.c, it.d, it.e, it.f)
                ?: throw BursterError.NoTarget
        }
    }
}

class Bullets6<A, B, C, D, E, F> {

    internal val bullets = mutableListOf<T6<A, B, C, D, E, F>>()

    fun values(a: A, b: B, c: C, d: D, e: E, f: F) {
        bullets += T6(a, b, c, d, e, f)
    }
}



