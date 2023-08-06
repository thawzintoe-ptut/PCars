package com.sevenpeakssoftware.core

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val carDispatcher: CarDispatcher)

enum class CarDispatcher {
    IO,
    MAIN,
    DEFAULT
}
