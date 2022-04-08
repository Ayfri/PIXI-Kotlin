plugins {
	kotlin("js")
	`js-conventions`
	`publish-conventions`
}

dependencies {
	implementation(npm("@pixi/math-extras", Versions.pixi))
	
	api(project(":pixi"))
}
