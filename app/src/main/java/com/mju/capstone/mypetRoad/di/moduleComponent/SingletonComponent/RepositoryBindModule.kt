package com.mju.capstone.mypetRoad.di.moduleComponent.SingletonComponent

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBindModule {

//    @Binds
//    abstract fun bindFoodApiRepository(
//        restaurantRepositoryImpl: RestaurantRepositoryImpl
//    ): RestaurantRepository
//
//    @Binds
//    abstract fun profileRepository(
//        profileRepositoryImpl: ProfileRepositoryImpl
//    ) : ProfileRepository
//
//    @Binds
//    abstract fun bindSuggestRepository(
//        suggestRepositoryImpl: SuggestRepositoryImpl
//    ): SuggestRepository
//
//    @Binds
//    abstract fun bindHomeRepository(
//        homeRepositoryImpl: HomeRepositoryImpl
//    ): HomeRepository
//
//    @Binds
//    abstract fun bindChatRepository(
//        chatRepositoryImpl: ChatRepositoryImpl
//    ): ChatRepository
//
//    @Binds
//    abstract fun bindMapApiRepository(
//        mapApiRepositoryImpl: MapApiRepositoryImpl
//    ): MapApiRepository
//
//    @Binds
//    abstract fun bindShopApiRepository(
//        shopApiRepositoryImpl: ShopApiRepositoryImpl
//    ): ShopApiRepository
//
//    @Binds
//    abstract fun bindCsRepository(
//        defaultCSRepository: DefaultCSRepository
//    ) : CSRepository
//
//    @Binds
//    abstract fun bindHomeFirstRepository(
//        homeFirstMockRepositoryImpl: HomeFirstMockRepositoryImpl
//    ) : HomeFirstMockRepository
//
//    @Binds
//    abstract fun bindHomeSecondRepository(
//        homeSecondMockRepositoryImpl: HomeSecondMockRepositoryImpl
//    ) : HomeSecondMockRepository
//
//    @Binds
//    abstract fun bindFirebaseRepository(
//        firebaseRepositoryImpl: FirebaseRepositoryImpl
//    ): FirebaseRepository
//
//    @Binds
//    abstract fun bindReviewRepository(
//        reviewRepositoryImpl: ReviewRepositoryImpl
//    ) : ReviewRepository
//
//    @Binds
//    abstract fun bindItemRepository(
//        repository: FirebaseItemRepository
//    ): ItemRepository
}