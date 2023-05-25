package com.example.hrclicker.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hrclicker.R
import com.example.hrclicker.dataBase.User
import com.example.hrclicker.dataBase.UserRepository
import com.example.hrclicker.functions.containsEmoticons
import com.example.hrclicker.functions.hasMoreThan16Characters
import com.example.hrclicker.screens.nav.Screen
import com.example.hrclicker.ui.theme.HR_dark_blue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(navController: NavController, userRepository: UserRepository) {

    var user: User? by remember { mutableStateOf(null) }
    val context = LocalContext.current

    LaunchedEffect(true){

            userRepository.performDatabaseOperation(Dispatchers.IO){
                val userList = userRepository.allUsers()
                if (userList.isNotEmpty()){
                    user = userList[0]
                }
            }

    }
    Surface(Modifier.fillMaxSize(), color = HR_dark_blue) {

        if (user != null){

            Column(Modifier.fillMaxWidth(), Arrangement.Top, Alignment.CenterHorizontally) {

                Text("HaloRuns", fontSize = 25.sp, color = Color.White, fontWeight = FontWeight.Bold)
                Text("Username: ${user!!.name}", fontSize = 20.sp, color = Color.White)

                Button(onClick = {
                    navController.navigate(route = "score_screen")
                }) {
                    Text(text = "Runner List")
                }
                Button(onClick = {
                    navController.navigate(route = "battle_screen"){
                        popUpTo(Screen.Home.route){
                            inclusive = true
                        }
                    }
                }) {
                    Text(text = "Battle!")
                }
                Button(onClick = {
                    navController.navigate(route = "clicker_screen")
                }) {
                    Text(text = "Grind")
                }

                //Image(painter = painterResource(R.drawable.ce), contentDescription = "")
            }
        }


        else{

            var name: String by remember {
                mutableStateOf("")
            }

            Column(Modifier.fillMaxWidth(),Arrangement.Center, Alignment.CenterHorizontally) {

                Row(Modifier.align(CenterHorizontally), Arrangement.Center) {

                    Text(text = "Username: ", color = Color.White,modifier = Modifier.align(CenterVertically))
                    TextField(value = name,
                        onValueChange = {name = it},
                        colors = TextFieldDefaults.textFieldColors(Color.White)
                    )
                }
                Button(onClick = {

                    if (hasMoreThan16Characters(name) || containsEmoticons(name)){
                        Toast.makeText(
                            context,
                            "max 16 characters! no Emoticons!",
                            Toast.LENGTH_LONG)
                            .show()
                    }else{

                        userRepository.performDatabaseOperation(Dispatchers.IO){
                            val userList = userRepository.allUsers()
                            val tempUser = if (userList.isEmpty()){
                                userRepository.addUser(
                                    User(
                                        name,
                                        100, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                    )
                                )
                                userList[0]
                            } else{
                                userList[0]
                            }
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
