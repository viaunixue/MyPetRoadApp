package com.mju.capstone.mypetRoad.di.moduleComponent.fragmentComponent

import androidx.fragment.app.Fragment
import com.mju.capstone.mypetRoad.presentation.ui.map.MapFragment
import com.mju.capstone.mypetRoad.presentation.ui.map.navermap.MarkerFactory
import com.mju.capstone.mypetRoad.presentation.ui.map.navermap.NaverMapHandler
import com.naver.maps.map.NaverMap
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(FragmentComponent::class)
object MapModule {
    //fragment 매개변수는 Dagger Hilt가 자동으로 제공하는 의존성
    @Provides
    fun provideMapFragment(fragment: Fragment) = fragment as MapFragment

    @Provides
    fun provideNaverMap(mapFragment: MapFragment) = mapFragment.naverMap

    @Provides
    fun provideMarkerFactory() = MarkerFactory()

    //Activity가 생성 됬을 때 inject가 이루어져서 naverMap가 아직 null이다.
    //naverMap의 경우 OnMapReadyCallback 이 후에 값이 들어옴.
    //lazy하게 이루어질 수 있도록 해야함.
    @Provides
    @FragmentScoped
    fun provideNaverMapHandler(
        markerFactory: MarkerFactory,
        naverMap: NaverMap
    ) = NaverMapHandler(markerFactory, naverMap)
}