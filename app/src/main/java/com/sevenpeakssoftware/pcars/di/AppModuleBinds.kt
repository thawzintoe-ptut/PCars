package com.sevenpeakssoftware.pcars.di

import com.sevenpeakssoftware.core.AppInitializer
import com.sevenpeakssoftware.sync.CarArticleInitializer
import com.sevenpeakssoftware.sync.ThreeTenBpInitializer
import com.sevenpeakssoftware.sync.TimberInitializer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@InstallIn(SingletonComponent::class)
@Module
abstract class AppModuleBinds {

    @Binds
    @IntoSet
    abstract fun provideCarInitializer(bind: CarArticleInitializer): AppInitializer

    @Binds
    @IntoSet
    abstract fun provideThreeTenbpInitializer(bind: ThreeTenBpInitializer): AppInitializer

    @Binds
    @IntoSet
    abstract fun provideTimberInitializer(bind: TimberInitializer): AppInitializer
}
