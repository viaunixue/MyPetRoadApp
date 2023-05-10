package com.mju.capstone.mypetRoad.views

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.mju.capstone.mypetRoad.databinding.ActivitySplashBinding
import com.mju.capstone.mypetRoad.views.base.BaseActivity
import com.mju.capstone.mypetRoad.views.feature.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun getViewBinding(): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)

    override fun observeData() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            //TODO LoginActivity 작성이 끝났다면 MainActivity -> LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }, DURATION)
    }
    companion object{
        private const val DURATION : Long = 3000
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}