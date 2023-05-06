package com.mju.capstone.mypetRoad.di.moduleComponent.SingletonComponent

import com.mju.capstone.mypetRoad.di.annotation.dispatcherModule.DefaultDispatcher
import com.mju.capstone.mypetRoad.di.annotation.dispatcherModule.IoDispatcher
import com.mju.capstone.mypetRoad.di.annotation.dispatcherModule.MainDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {
    @IoDispatcher
    @Provides
    @Singleton
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @DefaultDispatcher
    @Provides
    @Singleton
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @MainDispatcher
    @Provides
    @Singleton
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}