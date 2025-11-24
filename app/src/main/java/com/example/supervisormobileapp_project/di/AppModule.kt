package com.example.supervisormobileapp_project.di

import android.content.Context
import com.example.supervisormobileapp_project.data.repository.AuthRepository
import com.example.supervisormobileapp_project.data.repository.CompanyRepository
import com.example.supervisormobileapp_project.data.repository.PatrolSpotRepository
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

    @Provides
    @Singleton
    fun provideCompanyRepository(
        @ApplicationContext
        context: Context
    ): CompanyRepository {
        return CompanyRepository(context)
    }

    @Provides
    @Singleton
    fun providePatrolSpotRepository(
        @ApplicationContext
        context: Context
    ): PatrolSpotRepository {
        return PatrolSpotRepository(context)
    }
}