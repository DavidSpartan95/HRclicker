package com.example.hrclicker.dataBase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class UserRepository (private val appDatabase : AppDatabase, private val
coroutineScope : CoroutineScope
) {

    fun addUser(user: User){
        appDatabase.userDao().insertUser(user)
    }
    fun allUsers():List<User>{
        return appDatabase.userDao().getAllUsers()
    }
    fun addPoints(game:String, points:Int){
        appDatabase.userDao().addPoints(game,points)
    }

    fun learnNewMove(newMove:String){
        appDatabase.userDao().learnMove(newMove)
    }
    fun swapMove(move:String, were:Int){
        appDatabase.userDao().replaceMove(move,were)
    }
    fun increaseCap(oldCap:Int){
        appDatabase.userDao().increaseCap(oldCap)
    }
    fun deleteUser(username:String){
        appDatabase.userDao().deleteUserByName(username)
    }
    fun performDatabaseOperation (
        dispatcher: CoroutineDispatcher,
        databaseOperation : suspend () -> Unit
    ) {
        coroutineScope .launch(dispatcher) {
            databaseOperation ()
        }
    }
}