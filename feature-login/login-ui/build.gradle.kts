apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "debugImplementation"(CustomView.customView)
    "debugImplementation"(CustomView.pool)
    "implementation"(ThreeTen.threetenbp)
    "implementation"(Compose.viewConstraintLayout)
    "implementation"(Compose.hiltNavigationCompose)
    "implementation"("com.mixpanel.android:mixpanel-android:7.3.0")
}
