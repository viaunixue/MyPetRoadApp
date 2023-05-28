package com.mju.capstone.mypetRoad.views.feature.analysis

import androidx.cardview.widget.CardView
import androidx.viewpager.widget.ViewPager
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.databinding.FragmentDailyBinding
import com.mju.capstone.mypetRoad.views.base.BaseFragment
import com.mju.capstone.mypetRoad.widget.Adapter.AnalysisAdapter.AnalysisCardAdapter
import com.mju.capstone.mypetRoad.widget.Adapter.AnalysisAdapter.AnalysisCardPagerAdapter
import com.mju.capstone.mypetRoad.domain.model.CardItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DailyFragment : BaseFragment<FragmentDailyBinding>() {

    override fun getViewBinding() = FragmentDailyBinding.inflate(layoutInflater)

    override fun initViews() {
        super.initViews()
        val viewPager = binding.cardViewPager
        val analysisCardAdapter =
            AnalysisCardPagerAdapter(requireContext())
        analysisCardAdapter.addCardItem(
            CardItem(
                R.drawable.sample_dduzzi,
                "35",
                3.05,
                "250"
            )
        )
        analysisCardAdapter.addCardItem(
            CardItem(
                R.drawable.sample_dog,
                "45",
                3.75,
                "370"
            )
        )
        analysisCardAdapter.addCardItem(
            CardItem(
                R.drawable.sample_map_view,
                "25",
                2.05,
                "220"
            )
        )
        analysisCardAdapter.addCardItem(
            CardItem(
                R.drawable.sample_map_view,
                "60",
                3.35,
                "400"
            )
        )
        analysisCardAdapter.addCardItem(
            CardItem(
                R.drawable.sample_map_view,
                "10",
                1.25,
                "120"
            )
        )

        var mLastOffset = 0f

        viewPager.adapter = analysisCardAdapter
        viewPager.offscreenPageLimit = 5
        viewPager.currentItem = 0
//        binding.cardViewPager.adapter = analysisCardAdapter
//        binding.cardViewPager.offscreenPageLimit = 5
//        binding.cardViewPager.currentItem = 0

        binding.mainDots.setupWithViewPager(binding.cardViewPager)

        binding.cardViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                val realCurrentPosition: Int
                val nextPosition: Int
                val baseElevation: Float = (binding.cardViewPager.adapter as AnalysisCardPagerAdapter).getBaseElevation()
                val realOffset: Float
                val goingLeft: Boolean = mLastOffset > positionOffset

                if (goingLeft) {
                    realCurrentPosition = position + 1
                    nextPosition = position
                    realOffset = 1 - positionOffset
                } else {
                    nextPosition = position + 1
                    realCurrentPosition = position
                    realOffset = positionOffset
                }

                if (nextPosition > (binding.cardViewPager.adapter as AnalysisCardPagerAdapter).count - 1
                    || realCurrentPosition > (binding.cardViewPager.adapter as AnalysisCardPagerAdapter).count - 1) {
                    return
                }

                val currentCard: CardView = (binding.cardViewPager.adapter as AnalysisCardPagerAdapter).getCardViewAt(realCurrentPosition)

                currentCard.scaleX = (1 + 0.1 * (1 - realOffset)).toFloat()
                currentCard.scaleY = (1 + 0.1 * (1 - realOffset)).toFloat()

                currentCard.cardElevation = baseElevation + (baseElevation
                        * (AnalysisCardAdapter.MAX_ELEVATION_FACTOR - 1) * (1 - realOffset))

                val nextCard: CardView = (binding.cardViewPager.adapter as AnalysisCardPagerAdapter).getCardViewAt(nextPosition)

                nextCard.scaleX = (1 + 0.1 * realOffset).toFloat()
                nextCard.scaleY = (1 + 0.1 * realOffset).toFloat()

                nextCard.cardElevation = baseElevation + (baseElevation
                        * (AnalysisCardAdapter.MAX_ELEVATION_FACTOR - 1) * realOffset)

                mLastOffset = positionOffset
            }

            override fun onPageSelected(position: Int) {

            }
        })

    }
}