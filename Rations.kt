package rationals

import kotlin.math.absoluteValue


fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}

operator fun Any.contains(rational: Rational): Boolean {
    TODO("not implemented")
}

fun String.toRational(): Rational {
    val n = this.substringBefore('/').toInt()
    val d = this.substringAfter('/').toInt()
    return Rational(n, d)
}

infix fun Number.divBy(d: Number): Rational {
    return Rational(this as Int, d as Int)
}

class Rational(n: Int, d: Int) {
    val n: Int
    val d: Int
    init {
        val g = gcd(n.absoluteValue, d.absoluteValue)
        this.n = n / g
        this.d = d / g
    }
    constructor(n: Int) : this(n, 1)

    val lazyValue : String by lazy { println("Rational\n"); this.toString() }

    private fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
//    val g = gcd(n.absoluteValue, d.absoluteValue)
//    val number = n / g
//    val denom = d / g


    operator fun plus(other: Rational): Rational {
        return Rational(n * other.d + d * other.n, d * other.d)
    }

    operator fun minus(other: Rational): Rational {
        return this.plus(other.unaryMinus())
    }

    operator fun times(other: Rational): Rational {
        return Rational(n * other.n, d * other.d)
    }

    operator fun div(other: Rational): Rational {
        return Rational(n * other.d, d * other.n)
    }

    operator fun rangeTo(other: Rational): Any {
        TODO("not implemented")
    }

    operator fun unaryMinus(): Rational {
        return Rational(0 - n, d)
    }

    operator fun compareTo(other: Rational): Int {
        TODO("not implemented")
    }



    override fun toString(): String {
        return if (this.d == 1)
            "" + this.n
        else if (this.d < 0)
            "-" + this.n + "/" + this.d.absoluteValue
        else "" + this.n + "/" + this.d
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rational

        if (n != other.n) return false
        if (d != other.d) return false

        return true
    }

    override fun hashCode(): Int {
        var result = n
        result = 31 * result + d
        return result
    }


}
