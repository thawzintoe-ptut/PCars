apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.okHttpLoggingInterceptor)
    "implementation"(Retrofit.moshiConverter)
    "implementation"(Timber.name)
    "implementation"(Compose.viewModelCompose)
}
