package com.example.hrclicker

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hrclicker.screens.nav.SetupNavGraph
import com.example.hrclicker.ui.theme.HR_dark_blue
import com.example.hrclicker.ui.theme.HRclickerTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var navController: NavHostController
        super.onCreate(savedInstanceState)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContent {
            HRclickerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = HR_dark_blue
                ) {
                    navController = rememberNavController()
                    SetupNavGraph(navController = navController)

                }
            }
        }
    }
}
