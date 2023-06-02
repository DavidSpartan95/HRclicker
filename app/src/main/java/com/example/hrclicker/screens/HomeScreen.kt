package com.example.hrclicker.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import com.example.hrclicker.dataBase.User
import com.example.hrclicker.dataBase.UserRepository
import com.example.hrclicker.functions.containsEmoticons
import com.example.hrclicker.functions.hasMoreThan16Characters
import com.example.hrclicker.ui.theme.HR_dark_blue
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(navController: NavController, userRepository: UserRepository) {

    var user: User? by remember { mutableStateOf(null) }
    var recompose: Int by remember { mutableStateOf(0) }
    var showPopUp by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(true,recompose){

            userRepository.performDatabaseOperation(Dispatchers.IO){
                val userList = userRepository.allUsers()
                if (userList.isNotEmpty()){
                    user = userList[0]
                }else user = null
            }

    }
    Surface(Modifier.fillMaxSize(), color = HR_dark_blue) {

        if (user != null){
            if (showPopUp) {
                PopUpScreen(
                    yesFun = {
                        userRepository.performDatabaseOperation(Dispatchers.IO) {
                            userRepository.deleteUser(user!!.name)
                            recompose++
                            showPopUp = false
                        }
                    },
                    noFun = { showPopUp = false }
                )
            }
            val userJson = Gson().toJson(user)

            Column(Modifier.fillMaxWidth(), Arrangement.Top, CenterHorizontally) {

                Text("HaloRuns", fontSize = 25.sp, color = Color.White, fontWeight = FontWeight.Bold)
                Text("Username: ${user!!.name}", fontSize = 20.sp, color = Color.White)

                Button(onClick = {
                    navController.navigate(route = "score_screen/$userJson")
                }) {
                    Text(text = "Runner List")
                }
                Button(onClick = {
                    navController.navigate(route = "clicker_screen")
                }) {

                    Text(text = "GrindEasy")

                }
                Button(onClick = {
                    navController.navigate(route = "loadout_screen/$userJson")
                }) {
                    Text(text = "loadout")
                }
                Button(onClick = {
                    /*userRepository.performDatabaseOperation(Dispatchers.IO){
                        userRepository.deleteUser(user!!.name)
                        recompose++
                    }*/
                    showPopUp = true
                }) {
                    Text(text = "NewAcc")
                }
            }
        }

        else{

            var name: String by remember {
                mutableStateOf("")
            }

            Column(Modifier.fillMaxWidth(),Arrangement.Center, CenterHorizontally) {

                Row(Modifier.align(CenterHorizontally), Arrangement.Center) {

                    Text(text = "Username: ", color = Color.White,modifier = Modifier.align(CenterVertically))
                    TextField(value = name,
                        onValueChange = {name = it},
                        colors = TextFieldDefaults.textFieldColors(Color.White)
                    )
                }
                Button(onClick = {

                    if (hasMoreThan16Characters(name) || containsEmoticons(name) || name.isEmpty()){
                        Toast.makeText(
                            context,
                            "Between 0-16 characters! no Emoticons!",
                            Toast.LENGTH_LONG)
                            .show()
                    }else{

                        userRepository.performDatabaseOperation(Dispatchers.IO){
                            userRepository.addUser(
                                    User(
                                        name,
                                        100, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                    )
                                )
                            val tempUser: User = userRepository.allUsers()[0]

                            CoroutineScope(Dispatchers.Main).launch {
                                user = tempUser
                            }
                        }
                    }
                }) {
                    Text(text = "START!")
                }
            }
        }
    }
}

@Composable
fun PopUpScreen(yesFun:()->Unit,noFun:()-> Unit) {
    Popup(
        alignment = Alignment.Center,
    ) {

        Box(
            Modifier
            .background(color = HR_dark_blue, shape = RoundedCornerShape(8.dp))
            .size(250.dp)
            .fillMaxWidth()
            .padding(2.dp)
            .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(8.dp))
            .padding(20.dp)
        ) {
            Text(text = "Are you sure you want to delete your account and make a new one?", color = Color.White)
            Button(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(15.dp),
                onClick = { noFun.invoke() }
            ) {
                Text(text = "NO")
            }
            Button(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(15.dp),
                onClick = { yesFun.invoke()}
            ) {
                Text(text = "YES")
            }
        }
    }
}

