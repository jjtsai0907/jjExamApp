
package com.example.examapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlayerProgress (@PrimaryKey(autoGenerate = true) val playerId: Int,
                           @ColumnInfo(name = "playerName") val playerName: String = "No-Name Nomad",
                           @ColumnInfo(name = "playerWallet") var playerWallet: Int = 0,
                           //@ColumnInfo(name = "playerCountryBeenTo") var playerCountryBeenTo: MutableList<String> = mutableListOf("Sweden"),
                           @ColumnInfo(name = "playerCountCountries") var playerCountCountries: Int = 0,
                           @ColumnInfo(name = "playerCountQuestion") var playerCountQuestion: Int = 0){

}