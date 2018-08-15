package io.dotanuki.burster

fun <A, B, C, D, E, F, G, H, I> using(block: Buster9<A, B, C, D, E, F, G, H, I>.() -> Unit) =
    Buster9<A, B, C, D, E, F, G, H, I>().apply { block() }.execute()

class Buster9<A, B, C, D, E, F, G, H, I> {

    private val container by lazy { Bullets9<A, B, C, D, E, F, G, H, I>() }
    private var target: ((A, B, C, D, E, F, G, H, I) -> Unit)? = null

    fun burst(block: Bullets9<A, B, C, D, E, F, G, H, I>.() -> Unit) {
        container.apply { block() }
    }

    fun thenWith(assertion: (A, B, C, D, E, F, G, H, I) -> Unit) {
        target = assertion
    }

    internal fun execute() = with(container.bullets) {
        if (isEmpty()) throw BursterError.NoValues
        forEach {
            target
                ?.invoke(it.a, it.b, it.c, it.d, it.e, it.f, it.g, it.h, it.i)
                ?: throw BursterError.NoTarget
        }
    }
}

class Bullets9<A, B, C, D, E, F, G, H, I> {

    internal val bullets = mutableListOf<T9<A, B, C, D, E, F, G, H, I>>()

    fun values(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I) {
        bullets += T9(a, b, c, d, e, f, g, h, i)
    }
}