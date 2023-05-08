package com.mju.capstone.mypetRoad.data.domain.dto

import java.util.*

abstract class Model(
    open val id: Long,
    open val createTime: Date
){
}