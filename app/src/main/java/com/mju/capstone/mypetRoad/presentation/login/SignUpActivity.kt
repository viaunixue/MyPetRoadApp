package com.mju.capstone.mypetRoad.presentation.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.*
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.data.api.RetrofitInstance
import com.mju.capstone.mypetRoad.data.domain.dto.GpsModel
import com.mju.capstone.mypetRoad.data.domain.dto.Pet
import com.mju.capstone.mypetRoad.data.domain.dto.User
import com.mju.capstone.mypetRoad.data.response.signUp.PetResponse
import com.mju.capstone.mypetRoad.databinding.ActivitySignUpBinding
import com.mju.capstone.mypetRoad.presentation.base.BaseActivity
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding>() {

    private var checkEye =0
//    var number: String = ""
    private var doubleBackToExit = false

    //create instance of firebae auth
    lateinit var auth: FirebaseAuth

    // get storedVerificationId
    // we will use this match the sent otp from firebase
    private var checkPhone = 0

    //firebase에서 보낼 otp
    lateinit var storedVerificationId: String
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
    }

    override fun getViewBinding(): ActivitySignUpBinding =
        ActivitySignUpBinding.inflate(layoutInflater)

    override fun observeData() {}

    override fun initViews() = with(binding) {
        super.initViews()
        // auth
        auth = FirebaseAuth.getInstance()

//        binding.confirm.visibility = View.INVISIBLE
//        binding.confirmButton.visibility = View.INVISIBLE

        binding.signBtn.setOnClickListener {
            if (true) {
                performRegister()
            } else {
                Toast.makeText(this@SignUpActivity, "전화번호 인증을 하지않았습니다.", Toast.LENGTH_SHORT).show()
            }
        }
        eye.setOnClickListener {
            showAndHide()
        }
    }

    private fun showAndHide(){
        if(checkEye == 0){
            binding.userPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            binding.eye.setImageResource(R.drawable.eyes_show)
            checkEye = 1
        }else{
            binding.userPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.eye.setImageResource(R.drawable.eyes)
            checkEye = 0
        }
    }

    private fun performRegister() {
        val userName = binding.userName.text.toString()
        val password = binding.userPassword.text.toString()
        val id = binding.userId.text.toString()
        val address = binding.userId.text.toString()
        val phone = binding.userPhone.text.toString()
        val weight = binding.petWeight.text.toString().toFloat()
        val species = binding.petSpecies.text.toString()
        val age = binding.petAge.text.toString().toInt()
        val petName = binding.petName.text.toString()
        val petSex = binding.petSex.checkedRadioButtonId.toString()
        val isNeutered = binding.isNeutered.checkedRadioButtonId.toString() == "Neutered"
        if (userName.isEmpty() || password.isEmpty() || id.isEmpty()
            || address.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "기입하지 않은 항목이 있습니다.", Toast.LENGTH_SHORT).show()
        }

        Log.d("SignUpFragment", "Name is " + userName)
        Log.d("SignUpFragment", "password is + $password")

        val retrofitInstance = RetrofitInstance.service

        val userRequest = User(userName, address, id, password, phone)
        val petRequest = Pet(petName, age, petSex, weight, isNeutered, species)
        val petCall = retrofitInstance.postPet(petRequest)
        val userCall = retrofitInstance.postUser(userRequest)
        petCall.enqueue(object : Callback<PetResponse> {
            override fun onResponse(call: Call<PetResponse>, response: Response<PetResponse>) {
                if (response.isSuccessful) {
                    val result: PetResponse? = response.body()
                    Log.d("YJ", "onResponce 성공: " + result?.toString());
                } else {
                    Log.d("YJ", "onResponce 실패")
                }
            }

            override fun onFailure(call: Call<PetResponse>, t: Throwable) {
                // handle error
                Log.d("YJ", "네트워크 에러 : " + t.message.toString())
            }
        })

        // create a user with firebase
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(userName, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                // else if sucessful
                Log.d("Sing", "Successfully created user with uid: ${it.result.user?.uid}")
                Toast.makeText(this, "회원가입이 정상적으로 완료되었습니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener {
                Log.d("Sign", "Faild to create user: ${it.message}")
                Toast.makeText(this, "Faild to create user: ${it.message}", Toast.LENGTH_SHORT)
                    .show()
            }
    }

//    private fun sendotp() {
////        number = binding.editphone.text.toString()
//
//        //get the phone number from edit text and append the country code
//        if (number.isNotEmpty()) {
//            number = "+82$number"
//            sendVerificationCode(number)
//        } else {
//            Toast.makeText(this, "Enter mobile number", Toast.LENGTH_LONG).show()
//        }
//    }

    //this method sends the verification code
    //and starts the callback of verification
    //which is implemented above in onCreate

//    private fun sendVerificationCode(number: String) {
//        val options = PhoneAuthOptions.newBuilder(auth)
//            .setPhoneNumber(number)       // Phone number to verify
//            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//            .setActivity(this)                 // Activity (for callback binding)
//            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
//            .build()
//        PhoneAuthProvider.verifyPhoneNumber(options)
//        Log.d("MGS", "Auth started")
//    }

    private fun back() {
        //startActivity(Intent(this,LoginActivity::class.java))
        this.finish()
    }



    private fun runDelayed(millis: Long, function: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed(function, millis)
    }


//    companion object {
//        const val TAG = "SingUpFragment"
//
//        fun newInstance(): SignUpActivity {
//            return SignUpActivity().apply {
//
//            }
//        }
//    }
}