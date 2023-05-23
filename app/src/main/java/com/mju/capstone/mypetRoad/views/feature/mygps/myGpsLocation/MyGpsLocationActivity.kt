package com.mju.capstone.mypetRoad.views.feature.mygps.myGpsLocation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.mju.capstone.mypetRoad.data.dto.searchAddress.MapSearchInfoDto
import com.mju.capstone.mypetRoad.databinding.ActivityMyGpsLocationBinding
import com.mju.capstone.mypetRoad.views.MainActivity
import com.mju.capstone.mypetRoad.views.feature.map.searchAddress.SearchAddressActivity
import com.mju.capstone.mypetRoad.widget.Adapter.RecentAddrAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyGpsLocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyGpsLocationBinding
    private lateinit var recentAddrAdapter: RecentAddrAdapter

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {

                val bundle = result.data?.extras
                val result = bundle?.get("result")
//
                intent?.putExtra(MY_LOCATION_KEY, result as MapSearchInfoDto)
                setResult(RESULT_OK, intent)
//
//                viewModel.saveRecentSearchItems(result as MapSearchInfoEntity) // db 저장
                finish()
            }
        }

    private val startSearchActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            if (result.resultCode == RESULT_OK){
                val bundle = result.data?.extras // intent로 보내내 extras를 받아옴
                val result = bundle?.get(SEARCH_LOCATION_KEY)
//                viewModel.saveRecentSearchItems(result as MapSearchInfoDto) // db 저장
//                intent?.putExtra(MY_LOCATION_KEY, result)
                setResult(RESULT_OK, intent)
                finish()
            }
        }

    companion object {
        const val MY_LOCATION_KEY = "MY_LOCATION_KEY"
        const val SEARCH_LOCATION_KEY = "SEARCH_LOCATION_KEY"

        fun newIntent(context: Context, mapSearchInfoDto: MapSearchInfoDto){
            Intent(context, MyGpsLocationActivity::class.java).apply {
                putExtra(MainActivity.MY_LOCATION_KEY, mapSearchInfoDto)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyGpsLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSetLocation.setOnClickListener{
//            startForResult.launch(
//
//            )
        }

        binding.btnClear.setOnClickListener {
//            viewmodel.deleteAllAddresses()
            recentAddrAdapter.clear()
            recentAddrAdapter.notifyDataSetChanged()
        }

        binding.etSearch.setOnClickListener {
            startSearchActivityForResult.launch(
                Intent(applicationContext, SearchAddressActivity::class.java)
            )
        }

        binding.ivBack.setOnClickListener {
            finish()
        }

        recentAddrAdapter = RecentAddrAdapter { addressData ->
            intent.putExtra(
                MY_LOCATION_KEY,
                MapSearchInfoDto(
                    fullAddress = addressData.fullAddress ?: "주소 정보 없음",
                    name = addressData.name ?: "주소 정보 없음"
                )
            )
            setResult(RESULT_OK, intent)
            finish()
        }
        observeData()
    }

    private fun observeData() = with(binding) {

    }
}