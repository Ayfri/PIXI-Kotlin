plugins {
	kotlin("js")
	`js-conventions`
	`publish-conventions`
}

dependencies {
	implementation(npm("@pixi/events", Versions.pixi))
	
	api(project(":pixi"))
}
