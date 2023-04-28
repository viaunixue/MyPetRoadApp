package com.mju.capstone.mypetRoad.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mju.capstone.mypetRoad.data.entity.AddressHistoryEntity
import okhttp3.Address

@Dao
interface AddressHistoryDao {
    @Query("SELECT * FROM MapInfo")
    suspend fun getAllAddress(): List<AddressHistoryEntity>

    @Insert
    suspend fun insertAddress(address: AddressHistoryEntity)

    @Insert
    suspend fun deleteAddress(address: AddressHistoryEntity)

    @Query("delete from MapInfo")
    suspend fun deleteAllAddresses()
}