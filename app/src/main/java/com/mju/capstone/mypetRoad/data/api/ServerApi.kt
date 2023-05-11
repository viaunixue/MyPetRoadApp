package com.mju.capstone.mypetRoad.data.api

import com.mju.capstone.mypetRoad.domain.model.Login
import com.mju.capstone.mypetRoad.domain.model.Pet
import com.mju.capstone.mypetRoad.domain.model.User
import com.mju.capstone.mypetRoad.data.dto.signUp.LoginDto
import com.mju.capstone.mypetRoad.data.dto.signUp.PetDto
import com.mju.capstone.mypetRoad.data.dto.signUp.UserDto
import retrofit2.Call
import retrofit2.http.*

interface ServerApi {
    @POST("/api/login")
    fun login(@Body request: Login): Call<LoginDto>

    @POST("/api/users")
    @Headers("accept: application/json",
        "content-type: application/json")
    fun postUser(@Body request: User): Call<UserDto>

    @POST("/api/pets")
    @Headers("accept: application/json",
        "content-type: application/json")
    fun postPet(@Body request: Pet): Call<PetDto>
}