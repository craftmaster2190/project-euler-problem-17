package com.craftmaster2190.projecteulerproblem17

import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component

@SpringBootApplication
class ProjectEulerProblem17Application(val numberStringer: NumberStringer) : ApplicationRunner {
    val log = LoggerFactory.getLogger(javaClass)

    override fun run(args: ApplicationArguments?) {
        var count = 0;
        for (i in 1..1000) {
            val numberWithCount = numberStringer.getOrStringify(i)
            count += numberWithCount.countOfLetters
            log.info("{} count={}", numberWithCount, count)
        }
    }
}

@Component
class NumberStringer {
    private final val baseNumbers = HashMap<Int, String>(
        mapOf(
            0 to "Zero",
            1 to "One",
            2 to "Two",
            3 to "Three",
            4 to "Four",
            5 to "Five",
            6 to "Six",
            7 to "Seven",
            8 to "Eight",
            9 to "Nine",
            10 to "Ten",
            11 to "Eleven",
            12 to "Twelve",
            13 to "Thirteen",
            14 to "Fourteen",
            15 to "Fifteen",
            16 to "Sixteen",
            17 to "Seventeen",
            18 to "Eighteen",
            19 to "Nineteen",
            20 to "Twenty",
            30 to "Thirty",
            40 to "Forty",
            50 to "Fifty",
            60 to "Sixty",
            70 to "Seventy",
            80 to "Eighty",
            90 to "Ninety"
        )
    )

    final val hundred = "Hundred"
    final val thousand = "Thousand"
    final val and = "And"

    final val numbersWithCountsCache = HashMap<Int, NumberWithCount>(1000)

    init {
        for (num in 21..99) {
            if (num !in baseNumbers) {
                baseNumbers[num] = baseNumbers[tensPlace(num) * 10] + baseNumbers[onesPlace(num)]
            }
        }

        for (num in 1..9) {
            val numHundred = num * 100;
            baseNumbers[numHundred] = baseNumbers[num] + hundred
            val numThousand = num * 1000;
            baseNumbers[numThousand] = baseNumbers[num] + thousand
        }

        for (num in 101..999) {
            if (num !in baseNumbers) {
                val numHundred = hundredsPlace(num) * 100
                baseNumbers[num] = baseNumbers[numHundred] + and + baseNumbers[tensPlace(num) * 10 + onesPlace(num)]
            }
        }
    }

    final fun digitAt(n: Int, digit: Int): Int {
        return (n / digit) % 10
    }

    final fun onesPlace(n: Int): Int {
        return digitAt(n, 1);
    }

    final  fun tensPlace(n: Int): Int {
        return digitAt(n, 10);
    }

    final  fun hundredsPlace(n: Int): Int {
        return digitAt(n, 100);
    }

    final  fun thousandsPlace(n: Int): Int {
        return digitAt(n, 1000);
    }

    fun getOrStringify(int: Int): NumberWithCount {
        return numbersWithCountsCache.computeIfAbsent(int) {
            val str = stringify(int)
            NumberWithCount(
                int,
                str,
                countWithoutSpacesOrHyphens(str)
            )
        }
    }

    fun stringify(int: Int): String {
        if (int in baseNumbers) {
            return baseNumbers[int]!!
        }
        return "Unknown"
    }


    fun countWithoutSpacesOrHyphens(str: String): Int {
        return str.replace(" ", "").length
    }
}

data class NumberWithCount(val int: Int, val string: String, val countOfLetters: Int)

fun main(args: Array<String>) {
    runApplication<ProjectEulerProblem17Application>(*args)
}
