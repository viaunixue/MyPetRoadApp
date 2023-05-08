package com.mju.capstone.mypetRoad.data.domain.dto

import java.util.*

data class User (
    override val id: Long,
    override val createTime: Date,
    val name: String,
    val address: String,
    val userId: String,
    val password: String,
    val phone: String
): Model(id, createTime){

}