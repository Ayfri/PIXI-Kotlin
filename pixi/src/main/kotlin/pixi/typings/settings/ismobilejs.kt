@file:JsModule("ismobilejs")

package pixi.typings.settings

external interface BaseResult {
	var phone: Boolean
	var tablet: Boolean
	var device: Boolean
}

external interface AppleResult : BaseResult {
	var ipod: Boolean
	var universal: Boolean
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
	var amazon: BaseResult
	var android: BaseResult
	var windows: BaseResult
	var other: OtherResult
	var phone: Boolean
	var tablet: Boolean
	var any: Boolean
}