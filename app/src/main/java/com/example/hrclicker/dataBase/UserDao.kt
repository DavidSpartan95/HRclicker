package com.example.hrclicker.dataBase

import androidx.room.*

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateExistingUser(user: User)
    @Query("SELECT * FROM User")
    fun getAllUsers(): List<User>
    @Transaction
    fun addPoints(game: String){
        val users = getAllUsers()
        val user = users[0]
        when(game){
            "CE" -> user.H1atk++
            "H2" -> user.H2atk++
            "H2A"-> user.H2Aatk++
            "H3" -> user.H3atk++
            "H4" -> user.H4atk++
            "H5" -> user.H5atk++
            "Reach" -> user.reachAtk++
            "ODST" -> user.ODSTatk++
            "Infinite" -> user.infinite_atk++
            else -> {}
        }
        updateExistingUser(user)

    }

}