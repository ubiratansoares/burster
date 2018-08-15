package io.dotanuki.burster

fun <A, B> using(block: Buster2<A, B>.() -> Unit) =
    Buster2<A, B>().apply { block() }.execute()

class Buster2<A, B> {

    private val container by lazy { Bullets2<A, B>() }
    private var target: ((A, B) -> Unit)? = null

    fun burst(block: Bullets2<A, B>.() -> Unit) {
        container.apply { block() }
    }

    fun thenWith(assertion: (A, B) -> Unit) {
        target = assertion
    }

    internal fun execute() = with(container.bullets) {
        if (isEmpty()) throw BursterError.NoValues
        forEach {
            target
                ?.invoke(it.a, it.b)
                ?: throw BursterError.NoTarget
        }
    }
}

class Bullets2<A, B> {

    internal val bullets = mutableListOf<T2<A, B>>()

    fun values(a: A, b: B) {
        bullets += T2(a, b)
    }
}

