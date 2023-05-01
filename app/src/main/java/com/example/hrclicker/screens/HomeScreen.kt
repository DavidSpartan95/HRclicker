package com.example.hrclicker.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hrclicker.ui.theme.HR_dark_blue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.net.HttpURLConnection
import java.net.URL
import java.security.SecureRandom
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager
import javax.security.cert.X509Certificate

@Composable
fun HomeScreen(navController: NavController) {
    //TODO this under this launch effect should ultimately print out Runner and Points from the URL
    LaunchedEffect(true) {
        GlobalScope.launch(Dispatchers.IO) {
            val url = URL("https://haloruns.com/runners")
            val urlConnection = url.openConnection() as HttpsURLConnection

            try {
                val insecureTrustManager = object : X509TrustManager {
                    override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                        // Accept all client certificates
                    }

                    override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                        // Accept all server certificates
                    }

                    override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                        return arrayOf()
                    }
                }

                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, arrayOf(insecureTrustManager), SecureRandom())
                urlConnection.sslSocketFactory = sslContext.socketFactory
                urlConnection.hostnameVerifier = HostnameVerifier { _, _ -> true }

                val text = urlConnection.inputStream.bufferedReader().readText()
                Log.d("UrlTest", text)
                println("Hello $text")
            } catch (e: Exception) {
                Log.e("UrlTest", "Error: ${e.message}")
                e.printStackTrace()
            } finally {
                urlConnection.disconnect()
            }
        }
    }

    Surface(Modifier.fillMaxSize(), color = HR_dark_blue) {

        Column(Modifier.fillMaxWidth(), Arrangement.Top, Alignment.CenterHorizontally) {

            Text("HaloRuns", fontSize = 25.sp, color = Color.White, fontWeight = FontWeight.Bold)
            Button(onClick = {
                navController.navigate(route = "score_screen"){

                }
            }) {

            }
        }

    }
}

