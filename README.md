# Burster

> Kotlin helper for nicer Junit4 "parametrized" tests

## Overview

Junit4 parametrized tests are not so cool when working with Kotlin. 😢

Let's see an example:

```kotlin
@RunWith(Parameterized::class)
class DigitToLetterTest(
    private val number: Int,
    private val letter: String) {

    @Test fun `your fancy test`() { 
        // perform your assertion here
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun errorCode() = listOf(
            arrayOf(1, "A"),
            arrayOf(2, "B"),
            arrayOf(3, "C")
    }

```

What do we see here?

- We must use `Parameterized` as our test runner
- We must provide test parameters using **static bridges** to JVM
- Kotlin classes only accept a unique **companion object**
- Type-safety is not guaranteed in compile time

**Burster** is a small DSL that borrows some ideas from [KotlinTest](https://github.com/kotlintest/kotlintest) in order to let you write the same test as above in a type-safe and more structured way:

```kotlin
@Test fun `should convert digit into letter`() {

    using<Int, String> {

        burst {
            values(1, "A")
            values(2, "B")
            values(3, "C")
        }

        thenWith { number, letter ->
            // perform your assertion here
        }
    }
}
```

`KotlinTest` offers a much more robust support to the table testing discipline, allowing you to provide table headers and so on, and this is great 🚀! 

But as an Android developer, I figure out that bursting some values and performing the assertions is my main usecase.

Nevertheless, some of the niciests (and advanced) `KotlinTest` features rely on the `junit5` engine and despite a [OSS effort](https://github.com/mannodermaus/android-junit5) to enable us - Android devs - to use junit5 platform to build Android applications, I burned my fingers a little bit much on it ... 

**Project Nitrogen** at [Android Jetpack] (https://developer.android.com/jetpack/) teaches us that official **junit5** support for Android developement from Google is not coming any soon ... therefore, we are stucked on jUnit4 by now. This DSL solves the aforementioned issue in this overall context and usecase.

## Setup

Grab this dependency to your Gradle project using [Jitpack](https://jitpack.io) 

For Android projects

```groovy
dependencies {
	implementation 'com.github.ubiratansoares:burster:<latest_version>'
}
```

For non-Android Gradle projects

```groovy
dependencies {
	compile 'com.github.ubiratansoares:burster:<latest_version>'
}
```

Please, refer to [releases](https://github.com/ubiratan/tite/releases) to learn more about each published version of this project.

## About

This DSL is inspired by [KotlinTest](https://github.com/kotlintest/kotlintest) table testing, and shamelessly borrows it's concept from Square's [Burst](https://github.com/square/burst/)

## License

```
The MIT License (MIT)

Copyright (c) 2018 Ubiratan Soares

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```