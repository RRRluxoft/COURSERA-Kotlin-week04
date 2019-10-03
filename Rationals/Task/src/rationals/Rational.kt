package rationals

import java.math.BigInteger
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

fun String.toRational(): Rational {
    val n = this.substringBefore('/').toBigInteger()
    val d = this.substringAfter('/').toBigInteger()
    return Rational(n, d)
}

infix fun Number.divBy(d: Number): Rational {
    return Rational(BigInteger.valueOf(this.toLong()), BigInteger.valueOf(d.toLong()))
}

class Rational(n: BigInteger, d: BigInteger): Number(), Comparable<Rational> {

    override fun toByte(): Byte {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toChar(): Char {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toDouble(): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toFloat(): Float {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toInt(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toLong(): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toShort(): Short {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val n: BigInteger
    val d: BigInteger
    init {
        require(d != BigInteger.ZERO)
        val g = gcd(n.abs(), d.abs())
        this.n = n.divide(g)
        this.d = d / g
    }
    constructor(n: BigInteger) : this(n, BigInteger.ONE)

    val lazyValue : String by lazy { println("Rational\n"); this.toString() }

    private fun gcd(a: BigInteger, b: BigInteger): BigInteger = if (b == BigInteger.ZERO) a
    else gcd(b, a % b)
//    val g = gcd(n.absoluteValue, d.absoluteValue)
//    val number = n / g
//    val denom = d / g


    operator fun plus(other: Rational): Rational {
        return Rational(n * other.d + d * other.n, d * other.d)
    }

    operator fun minus(other: Number): Rational {
        return when (other) {
            is Rational -> this.plus(other.unaryMinus())
            else -> this.plus((other as Rational).unaryMinus())
        }
    }

    operator fun times(other: Rational): Rational {
        return Rational(n * other.n, d * other.d)
    }

    operator fun div(other: Rational): Rational {
        return Rational(n * other.d, d * other.n)
    }

    operator fun rangeTo(other: Rational): ClosedRange<Rational> {
        return object : ClosedRange<Rational> {
            override val endInclusive: Rational = other
            override val start: Rational = this@Rational
        }
    }

    operator fun unaryMinus(): Rational {
        return Rational(n.negate(), d)
    }

    override operator fun compareTo(other: Rational): Int {
        val mNum = n * other.d;
        val oNum = other.n * d;
        return mNum.compareTo(oNum)
    }



    override fun toString(): String {
        return if (this.d == BigInteger.ONE)
            "" + this.n
        else if (this.d < BigInteger.ZERO)
            "" + this.n.negate() + "/" + this.d.abs()
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
        var result = n.hashCode()
        result = 31 * result + d.hashCode()
        return result
    }

}
