package pixi.externals

import kotlin.math.abs
import kotlin.math.roundToInt
import kotlin.random.Random

typealias Color = Int

val Color.red get() = this and 0xFF0000 shr 1
val Color.green get() = this and 0xFF00 shr 8
val Color.blue get() = this and 0xFF

inline val Color.r get() = red
inline val Color.g get() = green
inline val Color.b get() = blue

val Color.rd get() = red / 255.0
val Color.gd get() = green / 255.0
val Color.bd get() = blue / 255.0

val Color.asHexString get() = "#${toString(16).padStart(6, '0')}"
inline val Color.asRGB get() = arrayOf(red, green, blue)
fun Color.toArray(): ColorArr = arrayOf(red.toDouble() / 255, green.toDouble() / 255, blue.toDouble() / 255)

fun Color(red: Int, green: Int, blue: Int): Color {
	Color.checkRGB(red, green, blue)
	
	return (red shl 16) or (green shl 8) or blue
}

fun Color(red: Double, green: Double, blue: Double): Color {
	Color.checkRGB(red, green, blue)
	
	return Color((red * 256).roundToInt(), (green * 256).roundToInt(), (blue * 256).roundToInt())
}

fun Color(red: Float, green: Float, blue: Float): Color {
	val r = red.toDouble()
	val g = green.toDouble()
	val b = blue.toDouble()
	Color.checkRGB(r, g, b)
	
	return Color(r, g, b)
}

fun Color(red: Number, green: Number, blue: Number): Color {
	Color.checkRGB(red, green, blue)
	
	return Color(red.toInt(), green.toInt(), blue.toInt())
}

fun Color(string: String): Color {
	val hex = string.removePrefix("#")
	val r: String
	val g: String
	val b: String
	
	when (hex.length) {
		3 -> {
			r = hex[0].toString().repeat(2)
			g = hex[1].toString().repeat(2)
			b = hex[2].toString().repeat(2)
		}
		
		6 -> {
			r = hex.take(2)
			g = hex.substring(2, 4)
			b = hex.substring(4, 6)
		}
		
		else -> throw IllegalArgumentException("Invalid hex string: $string")
	}
	
	return Color(r.toInt(16), g.toInt(16), b.toInt(16))
}

fun Color(number: Number): Color = number.toInt()
fun Color(array: ColorArr): Color = Color(array[0], array[1], array[2])

val Int.Companion.RED: Color get() = Color(255, 0, 0)
val Int.Companion.GREEN: Color get() = Color(0, 255, 0)
val Int.Companion.BLUE: Color get() = Color(0, 0, 255)
val Int.Companion.WHITE: Color get() = Color(255, 255, 255)
val Int.Companion.BLACK: Color get() = Color(0, 0, 0)

fun Int.Companion.random(): Color = Color(Random.nextDouble(), Random.nextDouble(), Random.nextDouble())
fun Int.Companion.randomGray(): Color = Color(Random.nextDouble(), Random.nextDouble(), Random.nextDouble())
fun Int.Companion.rgb(red: Int, green: Int, blue: Int): Color = Color(red, green, blue)
fun Int.Companion.rgb(red: Float, green: Float, blue: Float): Color = Color(red, green, blue)
fun Int.Companion.rgb(red: Double, green: Double, blue: Double): Color = Color(red, green, blue)
fun Int.Companion.rgb(red: Number, green: Number, blue: Number): Color = Color(red, green, blue)
fun Int.Companion.gray(value: Double): Color = Color(value, value, value)
fun Int.Companion.gray(value: Number): Color = Color(value, value, value)

fun Int.Companion.fromHex(hex: String): Color = Color(hex)
fun Int.Companion.fromHsv(hue: Double, saturation: Double, value: Double): Color {
	val h = hue.toInt()
	val s = saturation.toInt()
	val v = value.toInt()
	
	val c = v * s / 255
	val x = c * (1 - abs((h / 60) % 2 - 1))
	val m = v - c
	
	val r = when (h) {
		in 0..60 -> c
		in 61..120 -> x
		in 121..180 -> 0
		in 181..240 -> m
		in 241..300 -> 0
		in 301..360 -> c
		else -> 0
	}
	
	val g = when (h) {
		in 0..60 -> x
		in 61..120 -> c
		in 121..180 -> 0
		in 181..240 -> m
		in 241..300 -> 0
		in 301..360 -> x
		else -> 0
	}
	
	val b = when (h) {
		in 0..60 -> 0
		in 61..120 -> m
		in 121..180 -> 0
		in 181..240 -> c
		in 241..300 -> x
		in 301..360 -> 0
		else -> 0
	}
	
	return Color(r + m, g + m, b + m)
}

fun Color.toHsv(): Triple<Int, Int, Int> {
	val r = rd
	val g = gd
	val b = bd
	
	val max = maxOf(r, g, b)
	val min = minOf(r, g, b)
	val delta = max - min
	val h = when (max) {
		min -> 0.0
		r -> (60 * ((g - b) / delta) + 360) % 360
		g -> (60 * ((b - r) / delta) + 120) % 360
		b -> (60 * ((r - g) / delta) + 240) % 360
		else -> 0.0
	}
	
	val s = when (max) {
		0.0 -> 0.0
		else -> delta / max
	}
	
	return Triple(h.toInt(), s.toInt(), max.toInt())
}

internal fun Int.Companion.checkRGB(red: Number, green: Number, blue: Number) {
	val r = red.toInt()
	require(r in 0..255) { "Red must be between 0 and 255, got $r" }
	
	val g = green.toInt()
	require(g in 0..255) { "Green must be between 0 and 255, got $g" }
	
	val b = blue.toInt()
	require(b in 0..255) { "Blue must be between 0 and 255, $b" }
}

internal fun Int.Companion.checkRGB(red: Double, green: Double, blue: Double) {
	require(red in 0.0..1.0) { "Red must be between 0 and 1, got $red" }
	require(green in 0.0..1.0) { "Green must be between 0 and 1, got $green" }
	require(blue in 0.0..1.0) { "Blue must be between 0 and 1, got $blue" }
}

fun String.toColor(): Color = Color(this)
