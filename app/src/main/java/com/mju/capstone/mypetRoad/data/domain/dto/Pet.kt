package com.mju.capstone.mypetRoad.data.domain.dto

import java.util.*

data class Pet(
    override val id: Long,
    override val createTime: Date,
    val name: String,
    val age: Int,
    val sex: String,
    val weight: Float,
    val isNeutered: Boolean,
    val species: String
): Model(id, createTime) {

}