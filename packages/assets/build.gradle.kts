plugins {
	kotlin("js")
	`js-conventions`
	`publish-conventions`
}

dependencies {
	implementation(npm("@pixi/assets", Versions.pixi))
	
	api(project(":pixi"))
}
