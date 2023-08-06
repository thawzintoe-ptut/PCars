object Build {
    private const val androidBuildToolsVersion = "7.0.4"
    const val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"

    private const val hiltAndroidGradlePluginVersion = "2.44.2"
    const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltAndroidGradlePluginVersion"

    private const val kspPluginVersion = "1.6.10-1.0.4"
    const val kspAndroidGradlePlugin = "com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:$kspPluginVersion"
}
