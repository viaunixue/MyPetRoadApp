package com.mju.capstone.mypetRoad.data.domain.dto

data class Pet(
    val name: String,
    val age: Int,
    val sex: String,
    val weight: Float,
    val isNeutered: Boolean,
    val species: String
)