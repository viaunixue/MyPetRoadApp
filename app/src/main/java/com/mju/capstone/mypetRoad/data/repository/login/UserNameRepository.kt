package com.mju.capstone.mypetRoad.data.repository.login

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.mju.capstone.mypetRoad.data.entity.UserNameEntity
import kotlinx.coroutines.flow.map

const val DataStore_NAME = "USERNAME"

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = DataStore_NAME)

class UserNameRepository(
    private val context: Context
):UserNameRepositoryImpl {

    companion object {
        val NAME = stringPreferencesKey("NAME")
    }

    override suspend fun saveUserName(userNameEntity: UserNameEntity) {
        context.datastore.edit {
            it[NAME] = userNameEntity.userName
        }
    }

    override suspend fun getUserName() = context.datastore.data.map {
        UserNameEntity(
            userName = it[NAME]!!
        )
    }


}