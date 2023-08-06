buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Build.androidBuildTools)
        classpath(Build.hiltAndroidGradlePlugin)
        classpath(Build.kotlinGradlePlugin)
        classpath(Detekt.plugin)
        classpath(Build.kspAndroidGradlePlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

tasks.register<io.gitlab.arturbosch.detekt.Detekt>("myDetekt") {
    description = "Runs detekt build."
    setSource(files("src/main/kotlin", "src/test/kotlin"))
    config.setFrom(files("$rootDir/config/detekt/detekt_config.yml"))
    debug = true
    reports {
        xml {
            outputLocation.set(file("build/reports/carDetekt.xml"))
        }
        html.outputLocation.set(file("build/reports/carDetekt.html"))
    }
    include("**/*.kt")
    include("**/*.kts")
    exclude("resources/")
    exclude("build/")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
