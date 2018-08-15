package io.dotanuki.burster

fun <A, B, C, D, E, F, G, H> using(block: Buster8<A, B, C, D, E, F, G, H>.() -> Unit) =
    Buster8<A, B, C, D, E, F, G, H>().apply { block() }.execute()

class Buster8<A, B, C, D, E, F, G, H> {

    private val container by lazy { Bullets8<A, B, C, D, E, F, G, H>() }
    private var target: ((A, B, C, D, E, F, G, H) -> Unit)? = null

    fun burst(block: Bullets8<A, B, C, D, E, F, G, H>.() -> Unit) {
        container.apply { block() }
    }

    fun thenWith(assertion: (A, B, C, D, E, F, G, H) -> Unit) {
        target = assertion
    }

    internal fun execute() = with(container.bullets) {
        if (isEmpty()) throw BursterError.NoValues
        forEach {
            target
                ?.invoke(it.a, it.b, it.c, it.d, it.e, it.f, it.g, it.h)
                ?: throw BursterError.NoTarget
        }
    }
}

class Bullets8<A, B, C, D, E, F, G, H> {

    internal val bullets = mutableListOf<T8<A, B, C, D, E, F, G, H>>()

    fun values(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H) {
        bullets += T8(a, b, c, d, e, f, g, h)
    }
}