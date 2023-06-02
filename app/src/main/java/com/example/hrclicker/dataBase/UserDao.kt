package com.example.hrclicker.dataBase

import androidx.room.*
import com.example.hrclicker.functions.totalPoints
import com.example.hrclicker.runnerData.RunnerList

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User)
    @Delete
    fun deleteUser(user: User)
    @Query("DELETE FROM User WHERE name = :name")
    fun deleteUserByName(name: String)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateExistingUser(user: User)
    @Query("SELECT * FROM User")
    fun getAllUsers(): List<User>
    @Transaction
    fun increaseCap(oldCap:Int){
        val user = getAllUsers()[0]

        for (x in RunnerList){
            if (x.score > totalPoints(user)){
                user.cap = x.score
                break
            }
        }
        updateExistingUser(user)
    }
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
    @Transaction
    fun learnMove(newMove:String){
        val user = getAllUsers()[0]
        user.moves += newMove
        updateExistingUser(user)
    }
    @Transaction
    fun replaceMove(move:String,were:Int){
        val user = getAllUsers()[0]

        when(were){
            1 -> user.move1 = move
            2 -> user.move2 = move
            3 -> user.move3 = move
            4 -> user.move4 = move
        }
        updateExistingUser(user)
    }

}