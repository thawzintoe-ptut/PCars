package com.sevenpeakssoftware.core.di

import com.sevenpeakssoftware.core.CarLogger
import com.sevenpeakssoftware.core.util.Logger
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class LoggerModule {
    @Singleton
    @Binds
    internal abstract fun provideLogger(bind: CarLogger): Logger
}