plugins {
	kotlin("js")
	`js-conventions`
	`publish-conventions`
}

dependencies {
	implementation(npm("@pixi/basis", Versions.pixi))
	
	api(project(":pixi"))
}
