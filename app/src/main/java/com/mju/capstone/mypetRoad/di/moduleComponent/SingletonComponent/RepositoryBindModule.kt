package com.mju.capstone.mypetRoad.di.moduleComponent.SingletonComponent

import com.mju.capstone.mypetRoad.data.repository.AddressHistoryRepository
import com.mju.capstone.mypetRoad.data.repository.AddressHistoryRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBindModule {
    @Binds
    abstract fun bindMapApiRepository(
        mapApiRepositoryImpl: AddressHistoryRepository
    ): AddressHistoryRepositoryInterface
}