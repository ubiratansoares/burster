package io.dotanuki.burster

fun <A, B, C, D, E, F, G> using(block: Buster7<A, B, C, D, E, F, G>.() -> Unit) =
    Buster7<A, B, C, D, E, F, G>().apply { block() }.execute()

class Buster7<A, B, C, D, E, F, G> {

    private val container by lazy { Bullets7<A, B, C, D, E, F, G>() }
    private var target: ((A, B, C, D, E, F, G) -> Unit)? = null

    fun burst(block: Bullets7<A, B, C, D, E, F, G>.() -> Unit) {
        container.apply { block() }
    }

    fun thenWith(assertion: (A, B, C, D, E, F, G) -> Unit) {
        target = assertion
    }

    internal fun execute() = with(container.bullets) {
        if (isEmpty()) throw BursterError.NoValues
        forEach {
            target
                ?.invoke(it.a, it.b, it.c, it.d, it.e, it.f, it.g)
                ?: throw BursterError.NoTarget
        }
    }
}

class Bullets7<A, B, C, D, E, F, G> {

    internal val bullets = mutableListOf<T7<A, B, C, D, E, F, G>>()

    fun values(a: A, b: B, c: C, d: D, e: E, f: F, g: G) {
        bullets += T7(a, b, c, d, e, f, g)
    }
}