package com.mju.capstone.mypetRoad.di.moduleComponent.activityComponent

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavHost
import com.mju.capstone.mypetRoad.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object NavigationModule {

    /**
    supportFragmentManager은 AppCompatActivity 클래스에서만 제공됩니다.
    FragmentManager은 Android API 레벨 11부터 추가된 클래스이며, FragmentActivity에서 사용할 수 있습니다.
    그러나 FragmentActivity는 Android API 레벨 11 이상에서만 지원되기 때문에,
    이전 버전의 Android를 지원해야 하는 경우 AppCompatActivity를 사용해야 합니다.
     */

    @Provides
    fun provideActivity(activity: Activity) = activity as AppCompatActivity

    @Provides
    fun provideFragmentManager(activity: AppCompatActivity) = activity.supportFragmentManager

    //Activity가 생성 됬을 때 inject가 이루어져서 NavHost가 아직 null이다.
    //lazy하게 이루어질 수 있도록 해야함.
    @Provides
    @ActivityScoped
    fun provideNavigationController(fragmentManager: FragmentManager) : NavController =
        (fragmentManager.findFragmentById(R.id.petroad_nav_host_fragment) as NavHost).navController
}