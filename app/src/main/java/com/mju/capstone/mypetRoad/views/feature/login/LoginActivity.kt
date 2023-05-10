package com.mju.capstone.mypetRoad.views.feature.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import com.google.firebase.auth.FirebaseAuth
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.databinding.ActivityLoginBinding
import com.mju.capstone.mypetRoad.views.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {


    private var auth : FirebaseAuth? = null
    private var doubleBackToExit = false
    private var checkEye =0

//    lateinit var mGoogleSignInClient : GoogleSignInClient
    lateinit var resultLauncher : ActivityResultLauncher<Intent>


    override fun onStart() {
        super.onStart()
//        val account = GoogleSignIn.getLastSignedInAccount(this)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
//        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)

//        setResultSignUp()
//        var keyHash = Utility.getKeyHash(this)
//        Log.d("hash",keyHash)
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestEmail()
//            .build()

//        mGoogleSignInClient = GoogleSignIn.getClient(this,gso)

//        val callback : (OAuthToken?, Throwable?) -> Unit = { token, error ->
//            if (error != null) {
//            } else if (token != null) {
//                UserApiClient.instance.me { user, error ->
//                    val kakaoId = user!!.id
//                    startActivity(Intent(this, MainActivity::class.java))
//                    finish()
//                }
//            }
//        }

        binding.sign.setOnClickListener {
            registerMove()
        }
        binding.eye.setOnClickListener {
            showAndHide()
        }
//        binding.google.setOnClickListener {
//            val signIntent : Intent = mGoogleSignInClient.signInIntent
//            resultLauncher.launch(signIntent)
//        }
//        binding.kakao.setOnClickListener {
//            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this@LoginActivity)) {
//                UserApiClient.instance.loginWithKakaoTalk(this@LoginActivity, callback = callback)
//            } else {
//                UserApiClient.instance.loginWithKakaoAccount(this@LoginActivity, callback = callback)
//            }
//        }
    }


//    private fun setResultSignUp(){
//        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if(result.resultCode == Activity.RESULT_OK){
////                val task : Task<GoogleSignInAccount> =
////                    GoogleSignIn.getSignedInAccountFromIntent(result.data)
////                handleSignResult(task)
//            } else{
//                Toast.makeText(applicationContext,"로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

//    private fun handleSignResult(completeTask: Task<GoogleSignInAccount>){
//        try {
//            val account = completeTask.getResult(ApiException::class.java)
//            val email = account?.email.toString()
////            val photoUrl = account?.photoUrl.toString()
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//            Toast.makeText(this, "$email 님 로그인되었습니다", Toast.LENGTH_LONG).show()
//        } catch (e : ApiException){
//
//        }
//    }

    override fun initViews() {
        super.initViews()
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


//    private fun Sliding() {
//        val slidePanel = binding.loginframe
//
//        val state = slidePanel.panelState
//        if (state == SlidingUpPanelLayout.PanelState.COLLAPSED) {
//            slidePanel.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
//        } else if (state == SlidingUpPanelLayout.PanelState.EXPANDED) {
//            slidePanel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
//        }
//    }

    override fun getViewBinding(): com.mju.capstone.mypetRoad.databinding.ActivityLoginBinding =
        com.mju.capstone.mypetRoad.databinding.ActivityLoginBinding.inflate(layoutInflater)

    override fun observeData() {}


    private fun registerMove(){
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