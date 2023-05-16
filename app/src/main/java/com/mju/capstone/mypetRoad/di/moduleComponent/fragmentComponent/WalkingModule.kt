package com.mju.capstone.mypetRoad.di.moduleComponent.fragmentComponent

import androidx.fragment.app.Fragment
import com.mju.capstone.mypetRoad.views.feature.map.mapFragment.navermap.MarkerFactory
import com.mju.capstone.mypetRoad.views.feature.map.mapFragment.navermap.NaverMapHandler
import com.mju.capstone.mypetRoad.views.feature.walking.WalkingFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(FragmentComponent::class)
object WalkingModule {
    // fragment 매개변수는 Dagger Hilt가 자동으로 제공하는 의존성
    @Provides
    fun provideWalkingMapFragment(fragment: Fragment) = fragment as WalkingFragment

    @Provides
    fun provideWalkingNaverMap(walkingFragment: WalkingFragment) = walkingFragment. naverMap

    @Provides
    fun provideWalkingMarkerFactory() = MarkerFactory()

    @Provides
    @FragmentScoped
    fun provideWalkingNaverMapHandler(
        markerFactory: MarkerFactory,
        naverMap: NaverMap
    ) = NaverMapHandler(markerFactory, naverMap)
}