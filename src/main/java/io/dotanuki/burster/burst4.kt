package io.dotanuki.burster

fun <A, B, C, D> using(block: Buster4<A, B, C, D>.() -> Unit) =
    Buster4<A, B, C, D>().apply { block() }.execute()

class Buster4<A, B, C, D> {

    private val container by lazy { Bullets4<A, B, C, D>() }
    private var target: ((A, B, C, D) -> Unit)? = null

    fun burst(block: Bullets4<A, B, C, D>.() -> Unit) {
        container.apply { block() }
    }

    fun thenWith(assertion: (A, B, C, D) -> Unit) {
        target = assertion
    }

    internal fun execute() = with(container.bullets) {
        if (isEmpty()) throw BursterError.NoValues
        forEach {
            target
                ?.invoke(it.a, it.b, it.c, it.d)
                ?: throw BursterError.NoTarget
        }
    }
}

class Bullets4<A, B, C, D> {

    internal val bullets = mutableListOf<T4<A, B, C, D>>()

    fun values(a: A, b: B, c: C, d: D) {
        bullets += T4(a, b, c, d)
    }
}

