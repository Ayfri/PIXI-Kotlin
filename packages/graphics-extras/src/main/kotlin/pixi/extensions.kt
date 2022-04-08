package pixi

import pixi.typings.graphics.Graphics
import pixi.typings.math.PI_2

inline fun Graphics.drawChamferRect(x: Double, y: Double, width: Double, height: Double, chamfer: Double) = asDynamic().drawChamferRect(x, y, width, height, chamfer) as Graphics

inline fun Graphics.drawFilletRect(x: Double, y: Double, width: Double, height: Double, fillet: Double) = asDynamic().drawFilletRect(x, y, width, height, fillet) as Graphics

inline fun Graphics.drawRegularPolygon(x: Double, y: Double, radius: Double, sides: Double, rotation: Double = 0.0) = asDynamic().drawRegularPolygon(x, y, radius, sides, rotation) as Graphics

inline fun Graphics.drawRoundedPolygon(x: Double, y: Double, radius: Double, sides: Double, corner: Double, rotation: Double = 0.0) =
	asDynamic().drawRegularPolygon(x, y, radius, sides, corner, rotation) as Graphics

inline fun Graphics.drawStar(x: Double, y: Double, radius: Double, sides: Double, innerRadius: Double, rotation: Double = 0.0) =
	asDynamic().drawStar(x, y, radius, sides, innerRadius, rotation) as Graphics

inline fun Graphics.drawTorus(x: Double, y: Double, innerRadius: Double, outerRadius: Double, startArc: Double = 0.0, endArc: Double = PI_2) =
	asDynamic().drawTorus(x, y, innerRadius, outerRadius, startArc, endArc) as Graphics
