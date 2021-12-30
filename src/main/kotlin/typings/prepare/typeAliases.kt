package typings.prepare

import typings.core.AbstractRenderer


typealias IFindHook = (item: Any?, queue: Array<Any?>) -> Boolean

typealias IUploadHook = (helper: AbstractRenderer, item: IDisplayObjectExtended) -> Boolean
typealias IUploadHook2 = (helper: BasePrepare, item: IDisplayObjectExtended) -> Boolean
