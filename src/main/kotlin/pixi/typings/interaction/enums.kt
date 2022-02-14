package pixi.typings.interaction

import seskar.js.Case
import seskar.js.JsInt
import seskar.js.JsUnion

@JsUnion(case = Case.SNAKE)
external enum class Cursor {
	AUTO,
	DEFAULT,
	NONE,
	CONTEXT_MENU,
	HELP,
	POINTER,
	PROGRESS,
	WAIT,
	CELL,
	CROSSHAIR,
	TEXT,
	VERTICAL_TEXT,
	ALIAS,
	COPY,
	MOVE,
	NO_DROP,
	NOT_ALLOWED,
	E_RESIZE,
	N_RESIZE,
	NE_RESIZE,
	NW_RESIZE,
	S_RESIZE,
	SE_RESIZE,
	SW_RESIZE,
	W_RESIZE,
	NS_RESIZE,
	EW_RESIZE,
	NEWS_RESIZE,
	COL_RESIZE,
	NWSE_RESIZE,
	ROW_RESIZE,
	ALL_SCROLL,
	ZOOM_IN,
	ZOOM_OUT,
	GRAB,
	GRABBING
}

@JsUnion
enum class Button {
	@JsInt(0)
	MAIN,
	
	@JsInt(1)
	AUXILIARY,
	
	@JsInt(2)
	SECOND,
	
	@JsInt(3)
	FOURTH,
	
	@JsInt(4)
	FIFTH;
}
