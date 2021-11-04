package typings.loaders

import typings.VarArgFun
import typings.utils.Dict

typealias ILoaderMiddleware = (resource: LoaderResource, next: VarArgFun<Any, Unit>) -> Unit
typealias ILoaderMiddleware2 = (resource: LoaderResource) -> Unit
typealias ILoaderResource = LoaderResource

typealias OnStartSignalResource = (resource: LoaderResource) -> Unit
typealias OnProgressSignalResource = (resource: LoaderResource, percentage: Number) -> Unit
typealias OnCompleteSignalResource = (resource: LoaderResource) -> Unit
typealias MiddlewareFn = (resource: LoaderResource, next: () -> Unit) -> Unit


typealias OnStartSignal = (loader: Loader) -> Unit
typealias OnProgressSignal = (loader: Loader, resource: LoaderResource) -> Unit
typealias OnLoadSignal = (loader: Loader, resource: LoaderResource) -> Unit
typealias OnCompleteSignal = (loader: Loader, resources: Dict<LoaderResource>) -> Unit
typealias OnErrorSignal = (error: Error, loader: Loader, resource: LoaderResource) -> Unit
