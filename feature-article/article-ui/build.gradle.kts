apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.articleDomain))
    "debugImplementation"(CustomView.customView)
    "debugImplementation"(CustomView.pool)
    "implementation"(ThreeTen.threetenbp)
    "implementation"(Compose.viewConstraintLayout)
    "implementation"(Compose.hiltNavigationCompose)
    "coreLibraryDesugaring"(Common.desugar)
    configurations.configureEach {
        resolutionStrategy {
            force(Testing.junit4)
            // Temporary workaround for https://issuetracker.google.com/174733673
            force("org.objenesis:objenesis:2.6")
        }
    }
    "implementation"("com.mixpanel.android:mixpanel-android:7.3.0")
}
