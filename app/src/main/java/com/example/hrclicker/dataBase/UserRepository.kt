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
    fun addPoints(game:String){
        appDatabase.userDao().addPoints(game)
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