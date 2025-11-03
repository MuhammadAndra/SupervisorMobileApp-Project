package com.example.supervisormobileapp_project.di

import android.content.Context
import com.example.supervisormobileapp_project.data.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
        fun provideAuthRepository(
        @ApplicationContext
        context: Context
    ): AuthRepository {
        return AuthRepository(context)
    }
}