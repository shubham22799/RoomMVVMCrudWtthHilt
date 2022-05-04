package com.example.roommvvmcrudwtthhilt.di

import com.example.roommvvmcrudwtthhilt.repository.SubscriberRepository
import com.example.roommvvmcrudwtthhilt.repository.SubscriberRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class SubscriberRepositoryModule {

    @Binds
    abstract fun providesRepository(imp: SubscriberRepositoryImp): SubscriberRepository
}