package pixi.externals.extensions

import pixi.typings.ListenerFn
import pixi.typings.interaction.InteractionEvent
import pixi.typings.interaction.InteractionManager

sealed interface InteractionManagerEvents<T> {
	object click : InteractionManagerEvents<InteractionEvent>
	object mousedown : InteractionManagerEvents<InteractionEvent>
	object mousemove : InteractionManagerEvents<InteractionEvent>
	object mouseout : InteractionManagerEvents<InteractionEvent>
	object mouseover : InteractionManagerEvents<InteractionEvent>
	object mouseup : InteractionManagerEvents<InteractionEvent>
	object mouseupoutside : InteractionManagerEvents<InteractionEvent>
	object pointercancel : InteractionManagerEvents<InteractionEvent>
	object pointerdown : InteractionManagerEvents<InteractionEvent>
	object pointermove : InteractionManagerEvents<InteractionEvent>
	object pointerout : InteractionManagerEvents<InteractionEvent>
	object pointerover : InteractionManagerEvents<InteractionEvent>
	object pointertap : InteractionManagerEvents<InteractionEvent>
	object pointerup : InteractionManagerEvents<InteractionEvent>
	object pointerupoutside : InteractionManagerEvents<InteractionEvent>
	object rightclick : InteractionManagerEvents<InteractionEvent>
	object rightdown : InteractionManagerEvents<InteractionEvent>
	object rightup : InteractionManagerEvents<InteractionEvent>
	object rightupoutside : InteractionManagerEvents<InteractionEvent>
	object tap : InteractionManagerEvents<InteractionEvent>
	object touchcancel : InteractionManagerEvents<InteractionEvent>
	object touchend : InteractionManagerEvents<InteractionEvent>
	object touchendoutside : InteractionManagerEvents<InteractionEvent>
	object touchmove : InteractionManagerEvents<InteractionEvent>
	object touchstart : InteractionManagerEvents<InteractionEvent>
}

fun <T : InteractionManagerEvents<out E>, E> InteractionManager.emit(event: T, vararg arguments: E) = emit(event::class.simpleName!!, arguments.unsafeCast<Array<Any?>>())
fun <T : Any> InteractionManager.on(event: InteractionManagerEvents<T>, callback: (T) -> Unit) = on(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> InteractionManager.once(event: InteractionManagerEvents<T>, callback: (T) -> Unit) = once(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> InteractionManager.off(event: InteractionManagerEvents<T>, callback: (T) -> Unit) = off(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)

