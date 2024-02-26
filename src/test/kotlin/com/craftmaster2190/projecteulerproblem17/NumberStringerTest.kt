package com.craftmaster2190.projecteulerproblem17

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class NumberStringerTest {

    val numberStringer = NumberStringer()

    @Test
    fun `1`() {
        val numberWithCount = numberStringer.getOrStringify(1)
        Assertions.assertThat(numberWithCount.int).isEqualTo(1)
        Assertions.assertThat(numberWithCount.string).isEqualTo("One")
        Assertions.assertThat(numberWithCount.countOfLetters).isEqualTo(3)
    }

    @Test
    fun `6`() {
        val numberWithCount = numberStringer.getOrStringify(6)
        Assertions.assertThat(numberWithCount.int).isEqualTo(6)
        Assertions.assertThat(numberWithCount.string).isEqualTo("Six")
        Assertions.assertThat(numberWithCount.countOfLetters).isEqualTo(3)
    }

    @Test
    fun `15`() {
        val numberWithCount = numberStringer.getOrStringify(15)
        Assertions.assertThat(numberWithCount.int).isEqualTo(15)
        Assertions.assertThat(numberWithCount.string).isEqualTo("Fifteen")
        Assertions.assertThat(numberWithCount.countOfLetters).isEqualTo(7)
    }

    @Test
    fun `42`() {
        val numberWithCount = numberStringer.getOrStringify(42)
        Assertions.assertThat(numberWithCount.int).isEqualTo(42)
        Assertions.assertThat(numberWithCount.string).isEqualTo("FortyTwo")
        Assertions.assertThat(numberWithCount.countOfLetters).isEqualTo(8)
    }


    // For example, 342 (three hundred and forty-two) contains 23 letters and 115 (one hundred and fifteen) contains 20 letters.
    @Test
    fun `115`() {
        val numberWithCount = numberStringer.getOrStringify(115)
        Assertions.assertThat(numberWithCount.int).isEqualTo(115)
        Assertions.assertThat(numberWithCount.string).isEqualTo("OneHundredAndFifteen")
        Assertions.assertThat(numberWithCount.countOfLetters).isEqualTo(20)
    }

    @Test
    fun `342`() {
        val numberWithCount = numberStringer.getOrStringify(342)
        Assertions.assertThat(numberWithCount.int).isEqualTo(342)
        Assertions.assertThat(numberWithCount.string).isEqualTo("ThreeHundredAndFortyTwo")
        Assertions.assertThat(numberWithCount.countOfLetters).isEqualTo(23)
    }

}
