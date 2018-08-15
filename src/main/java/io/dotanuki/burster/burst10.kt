package io.dotanuki.burster

fun <A, B, C, D, E, F, G, H, I, J> using(block: Buster10<A, B, C, D, E, F, G, H, I, J>.() -> Unit) =
    Buster10<A, B, C, D, E, F, G, H, I, J>().apply { block() }.execute()

class Buster10<A, B, C, D, E, F, G, H, I, J> {

    private val container by lazy { Bullets10<A, B, C, D, E, F, G, H, I, J>() }
    private var target: ((A, B, C, D, E, F, G, H, I, J) -> Unit)? = null

    fun burst(block: Bullets10<A, B, C, D, E, F, G, H, I, J>.() -> Unit) {
        container.apply { block() }
    }

    fun thenWith(assertion: (A, B, C, D, E, F, G, H, I, J) -> Unit) {
        target = assertion
    }

    internal fun execute() = with(container.bullets) {
        if (isEmpty()) throw BursterError.NoValues
        forEach {
            target
                ?.invoke(it.a, it.b, it.c, it.d, it.e, it.f, it.g, it.h, it.i, it.j)
                ?: throw BursterError.NoTarget
        }
    }
}

class Bullets10<A, B, C, D, E, F, G, H, I, J> {

    internal val bullets = mutableListOf<T10<A, B, C, D, E, F, G, H, I, J>>()

    fun values(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J) {
        bullets += T10(a, b, c, d, e, f, g, h, i, j)
    }
}