package com.sevenpeakssoftware.pcars.di

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.mixpanel.android.mpmetrics.MixpanelAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MixPanelModule {

    @Provides
    @Singleton
    fun provideMixpanelApi(@ApplicationContext appContext: Context): MixpanelAPI{
        return MixpanelAPI.getInstance(
            appContext,
            "b51a8f52680dec1ce9d57ffd7557d10b",
            true
        )
    }
}