package pixi.utils

import kotlinx.js.Void
import kotlinx.js.jso
import pixi.externals.BLACK
import pixi.externals.Color
import pixi.typings.app.Application
import pixi.typings.app.IApplicationOptions
import pixi.typings.core.BaseTexture
import pixi.typings.core.IAutoDetectOptions
import pixi.typings.core.Resource
import pixi.typings.core.Texture
import pixi.typings.display.Container
import pixi.typings.display.DisplayObject
import pixi.typings.graphics.Graphics
import pixi.typings.math.Matrix
import pixi.typings.math.ObservablePoint
import pixi.typings.math.Point
import pixi.typings.math.Rectangle
import pixi.typings.sprite.Sprite
import pixi.typings.text.PartialTextStyle
import pixi.typings.text.Text
import pixi.typings.text.TextStyle

fun application(block: IApplicationOptions.() -> Unit) = Application(jso(block))

fun color(block: Color.() -> Unit) = Color.BLACK.apply(block)

fun <T : DisplayObject> container(block: Container<T>.() -> Unit) = Container<T>().apply(block)

fun graphics(block: Graphics.() -> Unit) = Graphics().apply(block)

fun matrix(block: Matrix.() -> Unit) = Matrix().apply(block)

fun point(block: Point.() -> Unit) = Point().apply(block)

fun <T> observablePoint(x: Number, y: Number, scope: T = jso(), onChange: ((T) -> Any?) = {}) = ObservablePoint(onChange, scope, x.toDouble(), y.toDouble())
fun observablePoint(x: Number, y: Number, onChange: (() -> Any?) = {}) = ObservablePoint(onChange.unsafeCast<(Void) -> Any?>(), null, x.toDouble(), y.toDouble())

fun rectangle(block: Rectangle.() -> Unit) = Rectangle().apply(block)

fun sprite(texture: Texture<Resource>, block: Sprite.() -> Unit) = Sprite(texture).apply(block)
fun sprite(texture: BaseTexture<Resource, IAutoDetectOptions>, block: Sprite.() -> Unit) = Sprite.from(texture).apply(block)
fun sprite(texture: String, block: Sprite.() -> Unit) = Sprite.from(texture).apply(block)

fun text(content: String = "", block: PartialTextStyle.() -> Unit) = Text(content, TextStyle(jso(block)))
fun textStyle(block: PartialTextStyle.() -> Unit) = TextStyle(jso(block))
