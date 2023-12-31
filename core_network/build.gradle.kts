apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.articleData))
    "implementation"(project(Modules.core))
    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.okHttpLoggingInterceptor)
    "implementation"(Retrofit.moshiConverter)
    "debugImplementation"(Common.chuck)
    "testImplementation"(Testing.mockWebServer)
}
