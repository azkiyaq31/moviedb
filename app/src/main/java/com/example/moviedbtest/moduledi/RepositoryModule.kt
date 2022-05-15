package com.example.moviedbtest.moduledi

import com.example.moviedbtest.data.RemoteDataSource
import com.example.moviedbtest.data.repository.MainRepository
import com.example.moviedbtest.db.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        movieDao: MovieDao,
        remoteDataSource: RemoteDataSource,
        coroutineDispatcher: CoroutineDispatcher
    ): MainRepository {
        return MainRepository(movieDao, remoteDataSource, coroutineDispatcher)
    }


}