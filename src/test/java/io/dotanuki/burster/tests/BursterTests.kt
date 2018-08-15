package io.dotanuki.burster.tests

import io.dotanuki.burster.BursterError
import io.dotanuki.burster.using
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class BursterTests {

    @Test fun `no values throws`() {

        val block = {
            using<Int> {
                thenWith { number: Int ->
                    assertThat(number).isGreaterThan(0)
                }
            }
        }

        assertThatThrownBy { block.invoke() }
            .isInstanceOf(BursterError.NoValues::class.java)
    }

    @Test fun `no target throws`() {

        val block = {
            using<Int> {
                burst {
                    value(1)
                    value(2)
                    value(3)
                }
            }
        }
        assertThatThrownBy { block.invoke() }
            .isInstanceOf(BursterError.NoTarget::class.java)
    }

    @Test fun `single type given`() {

        using<Int> {

            burst {
                value(1)
                value(2)
                value(3)
            }

            thenWith { number ->
                assertThat(number).isGreaterThan(0)
            }
        }
    }

    @Test fun `several inputs given, same type`() {

        using<Int, Int> {
            burst {
                values(1, 2)
                values(2, 4)
                values(3, 6)
            }

            thenWith { first, second ->
                assertThat(first * 2).isEqualTo(second)
            }
        }
    }

    @Test fun `double burst with distinct types`() {

        using<Int, String> {

            burst {
                values(1, "1")
                values(2, "2")
                values(3, "3")
            }

            thenWith { first, second ->
                assertThat(first.toString()).isEqualTo(second)
            }

        }
    }


    @Test fun `tripe burst`() {

        using<Int, Int, Int> {

            burst {
                values(1, 1, 2)
                values(2, 2, 4)
            }

            thenWith { first, second, sum ->
                assertThat(first + second).isEqualTo(sum)
            }
        }
    }

    @Test fun `burst four values`() {

        using<Int, Int, Int, Int> {

            burst {
                values(1, 1, 1, 3)
                values(2, 2, 2, 6)
            }

            thenWith { first, second, third, sum ->
                assertThat(first + second + third).isEqualTo(sum)
            }
        }
    }

    @Test fun `burst five values`() {

        using<Int, Int, Int, Int, Int> {

            burst {
                values(1, 1, 1, 1, 4)
                values(2, 2, 2, 2, 8)
            }

            thenWith { first, second, third, fourth, sum ->
                assertThat(first + second + third + fourth).isEqualTo(sum)
            }
        }
    }

    @Test fun `burst six values`() {

        using<Int, Int, Int, Int, Int, Int> {

            burst {
                values(1, 1, 1, 1, 1, 5)
                values(2, 2, 2, 2, 2, 10)
            }

            thenWith { a1, a2, a3, a4, a5, sum ->
                assertThat(a1 + a2 + a3 + a4 + a5).isEqualTo(sum)
            }
        }
    }

    @Test fun `burst seven values`() {

        using<Int, Int, Int, Int, Int, Int, Int> {

            burst {
                values(1, 1, 1, 1, 1, 1, 6)
                values(2, 2, 2, 2, 2, 2, 12)
            }

            thenWith { a1, a2, a3, a4, a5, a6, sum ->
                assertThat(a1 + a2 + a3 + a4 + a5 + a6).isEqualTo(sum)
            }
        }
    }

    @Test fun `burst eight values`() {

        using<Int, Int, Int, Int, Int, Int, Int, Int> {

            burst {
                values(1, 1, 1, 1, 1, 1, 1, 7)
                values(2, 2, 2, 2, 2, 2, 2, 14)
            }

            thenWith { a1, a2, a3, a4, a5, a6, a7, sum ->
                assertThat(a1 + a2 + a3 + a4 + a5 + a6 + a7).isEqualTo(sum)
            }
        }
    }

    @Test fun `burst nine values`() {

        using<Int, Int, Int, Int, Int, Int, Int, Int, Int> {

            burst {
                values(1, 1, 1, 1, 1, 1, 1, 1, 8)
                values(2, 2, 2, 2, 2, 2, 2, 2, 16)
            }

            thenWith { a1, a2, a3, a4, a5, a6, a7, a8, sum ->
                assertThat(a1 + a2 + a3 + a4 + a5 + a6 + a7 + a8).isEqualTo(sum)
            }
        }
    }

    @Test fun `burst ten values`() {

        using<Int, Int, Int, Int, Int, Int, Int, Int, Int, Int> {

            burst {
                values(1, 1, 1, 1, 1, 1, 1, 1, 1, 9)
                values(2, 2, 2, 2, 2, 2, 2, 2, 2, 18)
            }

            thenWith { a1, a2, a3, a4, a5, a6, a7, a8, a9, sum ->
                assertThat(a1 + a2 + a3 + a4 + a5 + a6 + a7 + a8 + a9).isEqualTo(sum)
            }
        }
    }
}
