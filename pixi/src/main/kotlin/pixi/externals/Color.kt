package pixi.externals

import kotlin.math.roundToInt
import kotlin.random.Random

typealias Color = Int

val Color.red get() = this and 0xFF0000 shr 1
val Color.green get() = this and 0xFF00 shr 8
val Color.blue get() = this and 0xFF

val Color.asHexString get() = "#${toString(16).padStart(6, '0')}"
val Color.asRGB get() = arrayOf(red, green, blue)
fun Color.toArray(): ColorArr = arrayOf(red.toDouble() / 255, green.toDouble() / 255, blue.toDouble() / 255)

fun Color(red: Int, green: Int, blue: Int) = (red shl 16) or (green shl 8) or blue
fun Color(red: Double, green: Double, blue: Double) = Color((red * 256).roundToInt(), (green * 256).roundToInt(), (blue * 256).roundToInt())
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
			r = hex.substring(0, 2)
			g = hex.substring(2, 4)
			b = hex.substring(4, 6)
		}
		else -> throw IllegalArgumentException("Invalid hex string: $string")
	}
	
	return Color(r.toInt(16), g.toInt(16), b.toInt(16))
}

fun Color(int: Int): Color = int
fun Color(array: ColorArr): Color = Color(array[0], array[1], array[2])

val Int.Companion.RED: Color get() = Color(255, 0, 0)
val Int.Companion.GREEN: Color get() = Color(0, 255, 0)
val Int.Companion.BLUE: Color get() = Color(0, 0, 255)
val Int.Companion.WHITE: Color get() = Color(255, 255, 255)
val Int.Companion.BLACK: Color get() = Color(0, 0, 0)
fun Int.Companion.random(): Color = Color(Random.nextDouble(), Random.nextDouble(), Random.nextDouble())
fun Int.Companion.rgb(red: Int, green: Int, blue: Int): Color = Color(red, green, blue)
fun Int.Companion.rgb(red: Double, green: Double, blue: Double): Color = Color(red, green, blue)
fun Int.Companion.fromHex(hex: String): Color = Color(hex)

typealias ColorArr = Array<Double>

var ColorArr.red
	get() = (this[0] * 256).roundToInt().coerceAtMost(255)
	set(value) {
		this[0] = value.toDouble() / 256
	}
var ColorArr.green
	get() = (this[1] * 256).roundToInt().coerceAtMost(255)
	set(value) {
		this[1] = value.toDouble() / 256
	}
var ColorArr.blue
	get() = (this[2] * 256).roundToInt().coerceAtMost(255)
	set(value) {
		this[2] = value.toDouble() / 256
	}

var ColorArr.alpha
	get() = this[3]
	set(value) {
		this[3] = value
	}
val ColorArr.alphaOrNull get() = getOrNull(3)
val ColorArr.hasAlpha get() = this.size > 3

val ColorArr.asHexString get() = "#${red.toString(16).padStart(2, '0')}${green.toString(16).padStart(2, '0')}${blue.toString(16).padStart(2, '0')}"
val ColorArr.asRGB get() = arrayOf(red, green, blue)
fun ColorArr.toColor() = Color(this)

fun ColorArr(color: Color) = arrayOf(
	(color and 0xFF0000 shr 16).toDouble() / 255,
	(color and 0xFF00 shr 8).toDouble() / 255,
	(color and 0xFF).toDouble() / 255
)

fun ColorArr(red: Int, green: Int, blue: Int, alpha: Double = 1.0) = arrayOf(red.toDouble() / 255, green.toDouble() / 255, blue.toDouble() / 255, alpha)
fun ColorArr(red: Double, green: Double, blue: Double, alpha: Double = 1.0) = arrayOf(red, green, blue, alpha)

fun ColorArr(color: String): ColorArr {
	val hex = color.removePrefix("#")
	return ColorArr(
		hex.substring(0, 2).toInt(16).toDouble() / 255,
		hex.substring(2, 4).toInt(16).toDouble() / 255,
		hex.substring(4, 6).toInt(16).toDouble() / 255
	)
}

fun String.toColor(): Color = removePrefix("#").toInt(16)
