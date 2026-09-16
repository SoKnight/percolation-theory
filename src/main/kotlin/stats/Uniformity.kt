package stats

import data.Lattice
import org.apache.commons.math3.distribution.ChiSquaredDistribution
import kotlin.math.pow

/**
 * Критерий Пирсона x² для проверки равномерности заполнения по интервалам [grid].
 *
 * @property alpha уровень значимости
 */
class Uniformity(val grid: Grid, val alpha: Double = 0.05) {

    /** Число степеней свободы `m - 1`. */
    val df: Int = grid.m - 1

    private val distribution = ChiSquaredDistribution(df.toDouble())

    /** Критическое значение x² для уровня значимости [alpha]. */
    val critical: Double = distribution.inverseCumulativeProbability(1 - alpha)

    /** Число занятых узлов в каждом интервале. */
    fun count(lattice: Lattice): LongArray {
        val counts = LongArray(grid.m)

        for (y in 0..<lattice.size)
            for (x in 0..<lattice.size)
                if (lattice[x, y] != 0)
                    counts[grid.indexOf(x, y)]++

        return counts
    }

    /** x² для счётчиков, сложенных по [trials] испытаниям. */
    fun chiSquared(counts: LongArray, trials: Int = 1): Double =
        counts.indices.sumOf { i ->
            val expected = trials * grid.expected(i)
            (counts[i] - expected).pow(2) / expected
        }

    /** Вероятность получить x² не меньше [chiSquared], если заполнение равномерное. */
    fun pValue(chiSquared: Double): Double =
        1 - distribution.cumulativeProbability(chiSquared)

    /** Проходит ли [chiSquared] критерий: гипотеза о равномерности не отвергается. */
    fun passes(chiSquared: Double): Boolean =
        pValue(chiSquared) > alpha

}
