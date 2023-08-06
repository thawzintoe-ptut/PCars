object Compose {
    const val composeVersion = "1.4.2"
    const val composeCompilerVersion = "1.4.6"
    private const val activity_version = "1.6.1"
    private const val compose_bom_version = "2023.01.00"

    const val compose_bom = "androidx.compose:compose-bom:$compose_bom_version"
    const val compose_foundation = "androidx.compose.foundation:foundation"

//    const val material3 = "androidx.compose.material3:material3:1.0.0"
    const val material = "androidx.compose.material:material:1.3.1"

//    const val ui = "androidx.compose.ui:ui:$composeVersion"
    const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"

//    const val runtime = "androidx.compose.runtime:runtime:$composeVersion"
//    const val compiler = "androidx.compose.compiler:compiler:$composeCompilerVersion"
//    const val animation = "androidx.compose.animation:animation:1.2.0-beta02"
//    const val livedata = "androidx.compose.runtime:runtime-livedata:$composeVersion"
//
    private const val navigationVersion = "2.5.3"
    const val navigation = "androidx.navigation:navigation-compose:$navigationVersion"

    private const val hiltNavigationComposeVersion = "1.0.0"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion"

    private const val lifecycleVersion = "2.5.1"
    const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion"

    private const val constraintLayoutVersion = "1.0.1"
    const val viewConstraintLayout = "androidx.constraintlayout:constraintlayout-compose:$constraintLayoutVersion"

    const val activityComposeKtx = "androidx.activity:activity-compose:$activity_version"
}
