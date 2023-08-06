apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.articleDomain))
    "implementation"(AppStartup.runtime)
    "implementation"(ThreeTen.threetenbp)

    "implementation"(DaggerHilt.hiltAndroid)
    "kapt"(DaggerHilt.hiltCompiler)
}
