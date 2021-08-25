package typings.loaders

import typings.app.Application
import typings.app.IApplicationOptions
import typings.core.Resource
import typings.core.Texture

inline var LoaderResource.texture: Texture<Resource>
	get() = asDynamic().texture
	set(value) {
		asDynamic().texture = value
	}

inline var Application.loader: Loader
	get() = asDynamic().loader as Loader
	set(value) {
		asDynamic().loader = value
	}

inline var IApplicationOptions.sharedLoader: Boolean?
	get() = asDynamic().sharedLoader as? Boolean
	set(value) {
		asDynamic().sharedLoader = value
	}

operator fun ILoaderAdd.invoke(self: Loader, options: IAddOptions, callback: OnCompleteSignal) = asDynamic()(self, options, callback)
operator fun ILoaderAdd.invoke(self: Loader, url: String, options: IAddOptions, callback: OnCompleteSignal) = asDynamic()(self, url, options, callback)
operator fun ILoaderAdd.invoke(self: Loader, url: String, callback: OnCompleteSignal) = asDynamic()(self, url, callback)
operator fun ILoaderAdd.invoke(self: Loader, name: String, url: String, options: IAddOptions, callback: OnCompleteSignal) = asDynamic()(self, name, url, options, callback)
operator fun ILoaderAdd.invoke(self: Loader, name: String, url: String, callback: OnCompleteSignal) = asDynamic()(self, name, url, callback)
operator fun ILoaderAdd.invoke(self: Loader, resources: Array<dynamic, /* IAddOptions | String */>, callback: OnCompleteSignal) = asDynamic()(self, resources, callback)
