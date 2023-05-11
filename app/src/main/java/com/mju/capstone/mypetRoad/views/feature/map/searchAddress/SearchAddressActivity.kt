package com.mju.capstone.mypetRoad.views.feature.map.searchAddress

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.mju.capstone.mypetRoad.databinding.ActivitySearchAddressBinding

class SearchAddressActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchAddressBinding
//    private val viewModel: SearchAddressViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        observeData()
    }

    private fun init() {
//        webViewAddress = // 메인 웹뷰
//        webViewLayout = // 웹뷰가 속한 레이아웃
// 공통 설정
        binding.webViewAddress.settings.run {
            javaScriptEnabled = true// javaScript 허용으로 메인 페이지 띄움
            javaScriptCanOpenWindowsAutomatically = true//javaScript window.open 허용
            setSupportMultipleWindows(true)
        }
        binding.webViewAddress.addJavascriptInterface(AndroidBridge(), "TestApp")
//최초로 웹뷰 로딩
        binding.webViewAddress.loadUrl("https://searchaddress-f9857.web.app")
        binding.webViewAddress.webChromeClient = this@SearchAddressActivity.webChromeClient

    }

    private inner class AndroidBridge {
        // 웹에서 JavaScript로 android 함수를 호출할 수 있도록 도와줌
        @JavascriptInterface
        fun setAddress(arg1: String?, arg2: String?, arg3: String?) {
            // search.php에서 callback 호출되는 함수
            Log.d("arg1.toString()", arg1.toString())
            Log.d("arg2.toString()", arg2.toString())
            Log.d("arg3.toString()", arg3.toString())

            val address = String.format("%s %s", arg2, arg3)
//            viewModel.getAddressInformation(address)
        }
    }

    private fun observeData() = with(binding) {

//        viewModel.locationData.observe(this@SearchAddressActivity) {
//            when (it) {
//                is SearchState.Uninitialized -> {
//                }
//                is SearchState.Loading -> {}
//                is SearchState.Success -> {
//                    val bundle = Bundle().apply {
//                        putParcelable(SEARCH_LOCATION_KEY, it.mapSearchInfoEntity)
//                    }
//                    intent.putExtras(bundle)
//                    setResult(RESULT_OK, intent)
//                    finish()
//                }
//                is SearchState.Error -> {
//                }
//            }
//        }
    }

    private val webChromeClient = object: WebChromeClient() {

        /// ---------- 팝업 열기 ----------
        /// - 카카오 JavaScript SDK의 로그인 기능은 popup을 이용합니다.
        /// - window.open() 호출 시 별도 팝업 webview가 생성되어야 합니다.
        ///
        lateinit var dialog : Dialog

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onCreateWindow(view: WebView, isDialog: Boolean,
                                    isUserGesture: Boolean, resultMsg: Message
        ): Boolean {
            // 웹뷰 만들기
            var childWebView = WebView(view.context)
            Log.d("TAG", "웹뷰 만들기")
            // 부모 웹뷰와 동일하게 웹뷰 설정
            childWebView.run {
                settings.run {
                    javaScriptEnabled = true
                    javaScriptCanOpenWindowsAutomatically = true
                    setSupportMultipleWindows(true)
                }
                layoutParams = view.layoutParams
                webViewClient = view.webViewClient
                webChromeClient = view.webChromeClient
            }

            dialog = Dialog(this@SearchAddressActivity).apply {
                setContentView(childWebView)
                window!!.attributes.width = ViewGroup.LayoutParams.MATCH_PARENT
                window!!.attributes.height = ViewGroup.LayoutParams.MATCH_PARENT
                show()
            }

            //webViewLayout.addView(childWebView)
            // TODO: 화면 추가 이외에 onBackPressed() 와 같이
            //       사용자의 내비게이션 액션 처리를 위해
            //       별도 웹뷰 관리를 권장함
            //   ex) childWebViewList.add(childWebView)

            // 웹뷰 간 연동
            val transport = resultMsg.obj as WebView.WebViewTransport
            transport.webView = childWebView
            resultMsg.sendToTarget()

            return true
        }

        override fun onCloseWindow(window: WebView) {
            super.onCloseWindow(window)
            Log.d("로그 ", "onCloseWindow")
            dialog.dismiss()
            //window!!.destroy()
            // 화면에서 제거하기
            // TODO: 화면 제거 이외에 onBackPressed() 와 같이
            //       사용자의 내비게이션 액션 처리를 위해
            //       별도 웹뷰 array 관리를 권장함
            //   ex) childWebViewList.remove(childWebView)
        }
    }
}