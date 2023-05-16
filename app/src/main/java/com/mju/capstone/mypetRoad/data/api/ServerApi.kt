package com.mju.capstone.mypetRoad.data.api

import com.mju.capstone.mypetRoad.domain.model.Login
import com.mju.capstone.mypetRoad.domain.model.Pet
import com.mju.capstone.mypetRoad.domain.model.User
import com.mju.capstone.mypetRoad.data.dto.signUp.LoginDto
import com.mju.capstone.mypetRoad.data.dto.signUp.PetDto
import com.mju.capstone.mypetRoad.data.dto.signUp.UserDto
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ServerApi {
    @POST("/api/login")
    fun login(@Body request: Login): Call<ResponseBody>

    @POST("/api/users")
    @Headers("Authorization: Bearer {jwt_token}")
    fun postUser(@Body request: User): Call<UserDto>

    @POST("/api/pets")
    @Headers("Authorization: Bearer {jwt_token}")
//    fun postPet(@Body request: Pet, @Path("jwt_token") jwt: String?): Call<PetDto>
    fun postPet(@Body request: Pet): Call<PetDto>
}