package com.mju.capstone.mypetRoad.util

import android.util.Log
import java.math.BigDecimal
import kotlin.math.ceil

object Calories {
    var totalCalories : Int = 0
    var totalSeconds: Long = 0L
    var walkedTime: Long = 0L
    var walkedDistance : Double = 0.0

    //칼로리 = 체중(kg) x 이동 거리(km) x 이동 시간(시간) x 0.05
    fun updateCalories(differenceInSeconds: Long){
        totalSeconds += differenceInSeconds
        walkedTime = totalSeconds / 60
        walkedDistance = DoubleCalc.div(Distance.totalDistance, 1000.0, 2)
        val petWeight : Double = doubleFloor2(Config.pet.weight.toDouble())
        val walkDistance : Double = doubleFloor2(walkedDistance)
        val walkTime : Double = doubleFloor2(walkedTime.toDouble())
        val correctionValue: Double = 0.03

        val calcCalSub = DoubleCalc.mul(petWeight, walkDistance)
        val calcCalSub2 = DoubleCalc.mul(calcCalSub, walkTime)
        val calcCal = DoubleCalc.mul(calcCalSub2, correctionValue)
        val result = (calcCal).toInt()


        totalCalories = result
    }

    fun clearCalories(){
        totalCalories = 0
    }

    /** 위도, 경도, 고도 등의 값을 소수점 2자리까지만 나타내도록 잘라주는 함수  */
    fun doubleFloor2(value: Double): Double {
        val floorValue: Double = DoubleCalc.div(Math.floor(value * 1000000), 1000000.0, 2)


        // 소수점 계산은 정확하게 안나오기 때문에
        // BigDecimal을 사용함
        val result = BigDecimal(floorValue.toString())
        val correctionValue = BigDecimal(0.01.toString()) // 보정값

        // 소수점 자리수가 6자리보다 적으면 보정값을 더해줌
        return if (result.scale() < 2) result.add(correctionValue)
            .toDouble() else result.toDouble() //이런다고 원본 값이 바뀌진 않음 그래서 바로 리턴해줘야댐

        // BigDecimal에서 double로 값을 꺼낼땐 doubleValue()를 사용
    }

    /** double형의 사칙 연산을 도와주는 클래스  */
    internal object DoubleCalc {
        /** double 더하기 a + b  */
        fun add(a: Double, b: Double): Double {
            val d1 = BigDecimal(a.toString())
            val d2 = BigDecimal(b.toString())
            return d1.add(d2).toDouble()
        }

        /** double 빼기 a - b  */
        fun sub(a: Double, b: Double): Double {
            val d1 = BigDecimal(a.toString())
            val d2 = BigDecimal(b.toString())
            return d1.subtract(d2).toDouble()
        }

        /** double 곱하기 a x b  */
        fun mul(a: Double, b: Double): Double {
            val d1 = BigDecimal(a.toString())
            val d2 = BigDecimal(b.toString())
            return d1.multiply(d2).toDouble()
        }

        /** double 나누기 a / b, 나타낼 소수점 자리수 포함 */
        fun div(a: Double, b: Double, num: Int): Double {
            val d1 = BigDecimal(a.toString())
            val d2 = BigDecimal(b.toString())
            return d1.divide(d2, num, BigDecimal.ROUND_DOWN).toDouble()
        }
    }
}