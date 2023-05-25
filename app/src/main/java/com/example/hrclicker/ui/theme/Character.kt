package com.example.hrclicker.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hrclicker.R


@Composable
fun Character(
    modifier: Modifier = Modifier,
    name: String,
    characterPainter: Painter,
    currentHp: Int,
    maxHp: Int,
    nameColor : Color = Color.White,
    hpColor:Color = Color.Green,
    lostHpColor :Color = Color.Transparent
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            color = nameColor
        )
        Spacer(modifier = Modifier.height(5.dp))
        Bar(
            modifier = Modifier
                .height(30.dp)
                .width(70.dp),
            barCount = maxHp,
            activeBars = currentHp,
            activeBarsColor = hpColor,
            inActiveBarsColor = lostHpColor,
            currentHp = currentHp,
            maxHp = maxHp
        )
        Image(painter = characterPainter, contentDescription = name)
    }
}


@Composable
private fun Bar(
    modifier: Modifier = Modifier,
    activeBars: Int = 0,
    barCount: Int = 20,
    activeBarsColor: Color = Color.Green,
    inActiveBarsColor: Color = Color.Gray,
    barCornerRadius: Float = 0f,
    currentHp: Int,
    maxHp: Int
) {
    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        val barWidth = remember {
            constraints.maxWidth.toFloat() /  barCount.toFloat()
        }
        Canvas(modifier = modifier) {
            for (i in 0..barCount) {
                drawRoundRect(
                    color = if (i in 0..activeBars) activeBarsColor else inActiveBarsColor,
                    topLeft = Offset(i * barWidth +barWidth/2 , 0f),
                    size = Size(barWidth, constraints.maxHeight.toFloat()),
                    cornerRadius = CornerRadius(barCornerRadius)
                )
            }
        }
        Text(text = "$currentHp/$maxHp ", color = Color.White, fontSize = 12.sp)
    }
}

@Preview
@Composable
fun CharPrev() {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Character(
            name = "Kronos",
            characterPainter = painterResource(id = R.drawable.ic_launcher_foreground),
            currentHp = 50,
            maxHp = 100
        )
    }
}