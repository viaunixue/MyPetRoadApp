package com.mju.capstone.mypetRoad.data.db

//@Database(
//    entities = [
//        AddressHistoryEntity::class],
//    version = 1
//)
//abstract class MapDB : RoomDatabase() {
//    companion object {
//        private var instance: MapDB? = null
//        fun getInstance(_context: MapFragment): MapDB? {
//            if(instance == null) {
//                synchronized(MapDB::class) {
//                    instance = Room.databaseBuilder(_context.applicationContext,
//                    MapDB::class.java, "MapInfo.db")
//                        .allowMainThreadQueries()
//                        .fallbackToDestructiveMigration()
//                        .build()
//                }
//            }
//            return instance
//        }
//    }
//    abstract fun addressHistoryDao(): AddressHistoryDao
//}