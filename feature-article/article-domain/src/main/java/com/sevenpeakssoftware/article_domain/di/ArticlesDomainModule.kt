package com.sevenpeakssoftware.article_domain.di

import com.sevenpeakssoftware.article_domain.repository.ArticleRepository
import com.sevenpeakssoftware.article_domain.usecase.GetArticlesUsecase
import com.sevenpeakssoftware.article_domain.usecase.SyncArticlesUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoSet

@Module
@InstallIn(ViewModelComponent::class)
object ArticlesDomainModule {

    @ViewModelScoped
    @Provides
    @IntoSet
    fun provideGetCars(
        homeRepository: ArticleRepository
    ): GetArticlesUsecase {
        return GetArticlesUsecase(homeRepository)
    }

    @ViewModelScoped
    @Provides
    @IntoSet
    fun provideSyncData(
        homeRepository: ArticleRepository
    ): SyncArticlesUsecase {
        return SyncArticlesUsecase(homeRepository)
    }
}

//@Module
//@InstallIn(SingletonComponent::class)
//object CarSyncModule{
//    @Provides
//    @Singleton
//    fun provideSyncData(
//        homeRepository: ArticleRepository
//    ): SyncData {
//        return SyncData(homeRepository)
//    }
//}

