package com.mju.capstone.mypetRoad.views

import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.mju.capstone.mypetRoad.R
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
            requestPermissions()
        }, DURATION)
    }

    private fun requestPermissions() {
        val ungrantedPermission = getUngrantedPermission()
        if (ungrantedPermission.isEmpty()) {
            navigateToLoginActivity()
        } else {
            ActivityCompat.requestPermissions(
                this,
                ungrantedPermission,
                PERMISSION_REQUEST_CODE
            )
        }
    }

    private fun getUngrantedPermission(): Array<String> {
        val ungrantedPermissions = mutableListOf<String>()
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(this, permission)
                != PackageManager.PERMISSION_GRANTED) {
                ungrantedPermissions.add(permission)
            }
        }
        return ungrantedPermissions.toTypedArray()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if  (requestCode == PERMISSION_REQUEST_CODE) {
            val allPermissionGranted = grantResults.all { it == PackageManager.PERMISSION_GRANTED }
            if (allPermissionGranted) {
                navigateToLoginActivity()
            } else {
                showPermissionDeniedDialog()
            }
        }
    }

    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(this)
            .setTitle("Permission Denied")
            .setPositiveButton("Permission Setting") { _, _ ->
                openAppSetting()
            }
            .setCancelable(false)
            .show()
    }

    private fun openAppSetting() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.fromParts("package", packageName, null)
        startActivity(intent)
        finish()
    }

    private fun navigateToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
        finish()
    }

    companion object {
        private const val DURATION: Long = 3000
        private const val PERMISSION_REQUEST_CODE = 123
        private val permissions = arrayOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }
}