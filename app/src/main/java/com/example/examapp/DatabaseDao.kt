package com.example.examapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface DatabaseDao {

    @Insert
    fun insert (playerProgress: PlayerProgress)

    @Delete
    fun delete (playerProgress: PlayerProgress)

    @Query("SELECT * FROM `playerProgress`")
    fun getAll(): List<PlayerProgress>

    @Query("SELECT * FROM `playerProgress` WHERE playerName LIKE :playerName")
    fun findByPlayerName (playerName: String): List<PlayerProgress>



}