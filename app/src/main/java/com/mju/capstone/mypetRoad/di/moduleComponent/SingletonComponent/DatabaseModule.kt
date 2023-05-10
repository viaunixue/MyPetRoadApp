package com.mju.capstone.mypetRoad.di.moduleComponent.SingletonComponent

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

//    @Provides
//    @Singleton
//    fun provideMapDB(@ApplicationContext context: Context): MapDB =
//        Room.databaseBuilder(context, MapDB::class.java, "MapStudy.db")
//            .fallbackToDestructiveMigration()
//            .allowMainThreadQueries()
//            .build()


//    @Provides
//    @Singleton
//    fun provideImageDB(@ApplicationContext context : Context): ImageDB =
//        Room.databaseBuilder(context, ImageDB::class.java,"profile")
//            .fallbackToDestructiveMigration()
//            .allowMainThreadQueries()
//            .build()

//    @Provides
//    @Singleton
//    fun provideLikeDB(@ApplicationContext context : Context): LikeDB =
//        Room.databaseBuilder(context, LikeDB::class.java,"Like.db")
//            .fallbackToDestructiveMigration()
//            .allowMainThreadQueries()
//            .build()

//
//    @Provides
//    @Singleton
//    fun ProvideDataStoreRepository(@ApplicationContext context : Context) = UserNameRepository(context)


}