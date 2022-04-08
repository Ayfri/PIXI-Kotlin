plugins {
	kotlin("js")
	`js-conventions`
	`publish-conventions`
}

dependencies {
	implementation(npm("@pixi/unsafe-eval", Versions.pixi))
	
	api(project(":pixi"))
}
