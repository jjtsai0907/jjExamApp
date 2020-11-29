package com.example.examapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(PlayerProgress::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun databaseDao() : DatabaseDao



}