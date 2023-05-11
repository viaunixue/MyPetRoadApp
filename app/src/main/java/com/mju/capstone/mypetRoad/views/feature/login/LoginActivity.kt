package com.mju.capstone.mypetRoad.views.feature.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.data.retrofit.RetrofitManager
import com.mju.capstone.mypetRoad.databinding.ActivityLoginBinding
import com.mju.capstone.mypetRoad.views.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private var doubleBackToExit = false
    private var checkEye =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.login.setOnClickListener {
            if(binding.editId.text.isNotEmpty() && binding.editPassword.text.isNotEmpty()){
                RetrofitManager.instance.postLogin(binding.editId.text.toString(), binding.editPassword.text.toString())
            } else{
                Toast.makeText(this, "아이디 또는 비밀번호를 입력해 주세요", Toast.LENGTH_SHORT).show();
            }
        }
        binding.sign.setOnClickListener {
            registerMove()
        }
        binding.eye.setOnClickListener {
            showAndHide()
        }
    }
    private fun showAndHide(){
        if(checkEye == 0){
            binding.editPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            binding.eye.setImageResource(R.drawable.eyes_show)
            checkEye = 1
        }else{
            binding.editPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.eye.setImageResource(R.drawable.eyes)
            checkEye = 0
        }
    }

    override fun getViewBinding(): com.mju.capstone.mypetRoad.databinding.ActivityLoginBinding =
        com.mju.capstone.mypetRoad.databinding.ActivityLoginBinding.inflate(layoutInflater)

    override fun observeData() {}


    private fun registerMove(){
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
    private fun logInRequest(){
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        if (doubleBackToExit) {
            finishAffinity()
        } else {
            Toast.makeText(this, "종료하시려면 뒤로가기를 한번더 눌러주세요", Toast.LENGTH_SHORT).show()
            doubleBackToExit = true
            runDelayed(1500L) {
                doubleBackToExit = false
            }
        }
    }
    private fun runDelayed(millis: Long, function: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed(function, millis)
    }
}