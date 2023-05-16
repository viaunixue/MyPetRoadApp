package com.mju.capstone.mypetRoad.views.feature.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.OnBackPressedCallback
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.databinding.ActivitySignUpBinding
import com.mju.capstone.mypetRoad.views.base.BaseActivity
import com.mju.capstone.mypetRoad.data.retrofit.RetrofitManager
import com.mju.capstone.mypetRoad.views.feature.mygps.myGpsLocation.MyGpsLocationActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding>() {

    private var checkEye =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
        var isFilled = false

        // EditText 뷰들을 찾습니다.
        val editTexts = listOf<EditText>(
            binding.userName,
            binding.userPassword,
            binding.userId,
            binding.userAddress,
            binding.userPhone,
            binding.petWeight,
            binding.petSpecies,
            binding.petAge,
            binding.petName,
        )
        val radioGroups = listOf<RadioGroup>(
            binding.petSex,
            binding.isNeutered
        )

        // EditText 뷰들의 TextChangedListener를 설정합니다.
        editTexts.forEach { editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    // EditText의 텍스트가 변경될 때마다 EditText 뷰들이 모두 기입되었는지 확인합니다.
                    val isAllEditTextsFilled = editTexts.all { editText ->
                        editText.text.isNotBlank() // EditText의 텍스트가 비어있지 않은지 확인합니다.
                    }
                    isFilled = isAllEditTextsFilled

                    if (binding.userId.text.isNotEmpty()
                        && binding.userPassword.text.isNotEmpty()
                        && binding.userId.text.toString().length >= 4
                        && binding.userId.text.toString().first().isLetter()
                        && binding.userPassword.text.toString().length >= 6
                    ) {
                        isFilled = isAllEditTextsFilled
                    } else {
                        isFilled = false
                    }
                    // Button의 clickable 값을 isAllEditTextsFilled 값으로 설정합니다.
                    if(isAllEditTextsFilled
                        && isFilled
                        && binding.petSex.checkedRadioButtonId != -1
                        && binding.isNeutered.checkedRadioButtonId != -1
                    ) {
                        binding.signBtn.isClickable = true
                        binding.signBtn.setBackgroundResource(R.drawable.login_btn)
                        binding.signBtn.setTextColor(getColor(R.color.white))
                    } else {
                        binding.signBtn.isClickable = false
                        binding.signBtn.setBackgroundResource(R.drawable.login_textbox)
                        binding.signBtn.setTextColor(getColor(R.color.black))
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }
        radioGroups.forEach { radioGroup ->
            radioGroup.setOnCheckedChangeListener{ _, _ ->
                if(isFilled
                    && binding.petSex.checkedRadioButtonId != -1
                    && binding.isNeutered.checkedRadioButtonId != -1 ) {
                    binding.signBtn.isClickable = true
                    binding.signBtn.setBackgroundResource(R.drawable.login_btn)
                    binding.signBtn.setTextColor(getColor(R.color.white))
                } else {
                    binding.signBtn.isClickable = false
                    binding.signBtn.setBackgroundResource(R.drawable.login_textbox)
                    binding.signBtn.setTextColor(getColor(R.color.black))
                }
            }

        }

    }

    override fun getViewBinding(): ActivitySignUpBinding =
        ActivitySignUpBinding.inflate(layoutInflater)

    override fun observeData() {}

    override fun initViews() = with(binding) {
        super.initViews()
//        binding.confirm.visibility = View.INVISIBLE
//        binding.confirmButton.visibility = View.INVISIBLE
        userAddress.setOnClickListener{
            registerAddress()
        }

        signBtn.setOnClickListener {
            performRegister()
        }
        eye.setOnClickListener {
            showAndHide()
        }
    }

    private fun registerAddress(){
        val intent = Intent(this, MyGpsLocationActivity::class.java)
        startActivity(intent)
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
        val address = binding.userAddress.text.toString()
        val phone = binding.userPhone.text.toString()
        val weight = binding.petWeight.text.toString().toFloat()
        val species = binding.petSpecies.text.toString()
        val age = binding.petAge.text.toString().toInt()
        val petName = binding.petName.text.toString()
        val petSexId = binding.petSex.checkedRadioButtonId
        val petSex = findViewById<RadioButton>(petSexId).text.toString()
        val isNeutered = binding.isNeutered.checkedRadioButtonId == R.id.Neutered

        Log.d("SignUpFragment", "Name is $userName")
        Log.d("SignUpFragment", "password is $password")

        // User, Pet 데이터 Post
        RetrofitManager.instance.postUser(userName, address, id, password, phone)
        RetrofitManager.instance.postPet(petName, age, petSex, weight, isNeutered, species)
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            this@SignUpActivity.finish()
        }
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