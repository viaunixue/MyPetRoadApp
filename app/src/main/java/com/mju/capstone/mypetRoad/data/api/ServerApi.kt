package com.mju.capstone.mypetRoad.data.api

import com.mju.capstone.mypetRoad.data.dto.signUp.UserResponseDto
import com.mju.capstone.mypetRoad.domain.model.Login
import com.mju.capstone.mypetRoad.domain.model.Pet
import com.mju.capstone.mypetRoad.domain.model.SignUp
import com.mju.capstone.mypetRoad.data.dto.signUp.PetDto
import com.mju.capstone.mypetRoad.data.dto.walkingInfo.WalkingDto
import com.mju.capstone.mypetRoad.data.dto.walkingInfo.WalkingRequestDto
import retrofit2.Call
import retrofit2.http.*

interface ServerApi {
    @POST("/api/login")
    fun login(@Body request: Login): Call<UserResponseDto>

    @POST("/api/users")
    fun postUser(@Body request: SignUp): Call<UserResponseDto>

    @POST("/api/pets")
//    @Headers("Authorization: Bearer {jwt_token}")
//    fun postPet(@Body request: Pet, @Path("jwt_token") jwt: String?): Call<PetDto>
    fun postPet(@Body request: Pet): Call<PetDto>

    @POST("/api/pets/{petId}/walks")
    fun postWalk(
        @Path("petId") petId: Long,
        @Body request: WalkingRequestDto
    ) : Call<WalkingDto>

    @GET("/api/pets/{petId}/walks/lastest")
    fun getLastestWalk(
        @Path("petId") petId: Long
    ) : Call<WalkingDto>

    @GET("/api/pets/{petId}/walks")
    fun getAllWalk(
        @Path("petId") petId: Long,
    ) : Call<List<WalkingDto>>
}