package com.mju.capstone.mypetRoad.data.repository.login

import com.mju.capstone.mypetRoad.data.entity.UserNameEntity
import kotlinx.coroutines.flow.Flow

interface UserNameRepositoryImpl {

    suspend fun saveUserName(userNameEntity: UserNameEntity)

    suspend fun getUserName() : Flow<UserNameEntity>


}