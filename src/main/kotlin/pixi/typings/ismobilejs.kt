@file:JsModule("ismobilejs")

package pixi.typings

external interface AppleResult {
	var phone: Boolean
	var ipod: Boolean
	var tablet: Boolean
	var universal: Boolean
	var device: Boolean
}

external interface BasicResult {
	var phone: Boolean
	var tablet: Boolean
	var device: Boolean
}

external interface OtherResult {
	var blackberry: Boolean
	var blackberry10: Boolean
	var opera: Boolean
	var firefox: Boolean
	var chrome: Boolean
	var device: Boolean
}

external interface isMobileResult {
	var apple: AppleResult
	var amazon: BasicResult
	var android: BasicResult
	var windows: BasicResult
	var other: OtherResult
	var phone: Boolean
	var tablet: Boolean
	var any: Boolean
}