package io.dotanuki.burster

sealed class BursterError(message: String) : IllegalStateException(message) {
    object NoValues : BursterError("No values to burst")
    object NoTarget : BursterError("No target to aim")
}