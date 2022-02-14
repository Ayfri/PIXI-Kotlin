package externals

import kotlin.math.roundToInt

typealias Color = Int

val Color.red get() = this and 0xFF0000 shr 16
val Color.green get() = this and 0xFF00 shr 8
val Color.blue get() = this and 0xFF

val Color.asHexString get() = "#${toString(16).padStart(6, '0')}"
val Color.asRGB get() = arrayOf(red, green, blue)
fun Color.toArray(): ColorArr = arrayOf(red.toDouble() / 255, green.toDouble() / 255, blue.toDouble() / 255)

typealias ColorArr = Array<Double>

val ColorArr.red get() = (this[0] * 256).roundToInt().coerceAtMost(255)
val ColorArr.green get() = (this[1] * 256).roundToInt().coerceAtMost(255)
val ColorArr.blue get() = (this[2] * 256).roundToInt().coerceAtMost(255)

val ColorArr.alpha get() = this[3]
val ColorArr.alphaOrNull get() = getOrNull(3)
val ColorArr.hasAlpha get() = this.size > 3

val ColorArr.asHexString get() = "#${red.toString(16).padStart(2, '0')}${green.toString(16).padStart(2, '0')}${blue.toString(16).padStart(2, '0')}"
val ColorArr.asRGB get() = arrayOf(red, green, blue)
fun ColorArr.toColor() = (red * 0x10000 + green * 0x100 + blue)

fun String.toColor(): Color = removePrefix("#").toInt(16)
