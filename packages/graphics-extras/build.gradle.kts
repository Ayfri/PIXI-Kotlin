plugins {
	kotlin("js")
	`js-conventions`
	`publish-conventions`
}

dependencies {
	implementation(npm("@pixi/graphics-extras", Versions.pixi))
	
	api(project(":pixi"))
}
