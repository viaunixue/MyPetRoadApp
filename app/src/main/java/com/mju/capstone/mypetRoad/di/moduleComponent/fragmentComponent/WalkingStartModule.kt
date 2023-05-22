package com.mju.capstone.mypetRoad.di.moduleComponent.fragmentComponent

import androidx.fragment.app.Fragment
import com.mju.capstone.mypetRoad.views.feature.map.mapFragment.navermap.MarkerFactory
import com.mju.capstone.mypetRoad.views.feature.map.mapFragment.navermap.NaverMapHandler
import com.mju.capstone.mypetRoad.views.feature.walking.WalkingHomeFragment
import com.mju.capstone.mypetRoad.views.feature.walking.WalkingStartFragment
import com.naver.maps.map.NaverMap
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi

//@ExperimentalCoroutinesApi
//@Module
//@InstallIn(FragmentComponent::class)
//object WalkingStartModule {
//    // fragment 매개변수는 Dagger Hilt가 자동으로 제공하는 의존성
//    @Provides
//    fun provideWalkingStartMapFragment(fragment: Fragment) = fragment as WalkingStartFragment
//
//    @Provides
//    fun provideWalkingStartNaverMap(walkingStartFragment : WalkingStartFragment) = walkingStartFragment.naverMap
//
//    @Provides
//    fun provideWalkingStartMarkerFactory() = MarkerFactory()
//
//    @Provides
//    @FragmentScoped
//    fun provideWalkingStartNaverMapHandler(
//        markerFactory: MarkerFactory,
//        naverMap: NaverMap
//    ) = NaverMapHandler(markerFactory, naverMap)
//}