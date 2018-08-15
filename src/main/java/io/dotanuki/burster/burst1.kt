package io.dotanuki.burster

fun <A> using(block: Buster1<A>.() -> Unit) =
    Buster1<A>().apply { block() }.execute()

class Buster1<A> {

    private val container = Bullets1<A>()
    private var target: ((A) -> Unit)? = null

    fun burst(block: Bullets1<A>.() -> Unit) {
        container.apply { block() }
    }

    fun thenWith(assertion: (A) -> Unit) {
        target = assertion
    }

    internal fun execute() = with(container.bullets) {
        if (isEmpty()) throw BursterError.NoValues
        forEach {
            target
                ?.invoke(it.a)
                ?: throw BursterError.NoTarget
        }
    }
}

class Bullets1<A> {
    internal val bullets = mutableListOf<T1<A>>()
    fun value(a: A) {
        bullets += T1(a)
    }
}


