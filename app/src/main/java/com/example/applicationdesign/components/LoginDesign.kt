package com.example.applicationdesign.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import com.example.applicationdesign.ui.theme.PurpleGrey40

@Preview(showBackground = true)
@Composable
fun DesignScreen(){
    Scaffold(containerColor = Color(245, 245, 245)) {
        paddingValues ->  Column (
            modifier = Modifier.padding(paddingValues)
        ){
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier.align(Alignment.BottomCenter)
            ){
                BottomContent()
            }
            TopContent()
        }
    }
    }
}

@Composable
fun BottomContent(){
    Box(modifier = Modifier
        .background(Color.Transparent)
        .fillMaxWidth()
        .fillMaxHeight(0.8f),
        contentAlignment = Alignment.Center,
        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome!", style = TextStyle(fontSize = 30.sp))
            Spacer(modifier = Modifier.height(40.dp))
            Button(onClick = { /*TODO*/ }, modifier = Modifier
                .fillMaxWidth(0.85f)
                .fillMaxHeight(0.1f)
                .background(
                    brush = Brush
                        .linearGradient(
                            colors = listOf(
                                Color(109, 51, 245),
                                Color(46, 138, 243)
                            )
                        ),
                    shape = RoundedCornerShape(100.dp)
                ),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),) {
                Text(text = "Create Account", style = TextStyle(fontSize = 18.sp))
            }
            Spacer(modifier = Modifier.height(15.dp))
            Button(onClick = { /*TODO*/ }, modifier = Modifier
                .fillMaxWidth(0.85f)
                .fillMaxHeight(0.1f)
                .border(
                    2.dp, Brush
                        .linearGradient(
                            colors = listOf(
                                Color(109, 51, 245),
                                Color(46, 138, 243)
                            )
                        ),
                    shape = RoundedCornerShape(100.dp)
                ),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),) {
                Text(text = "Login", style = TextStyle(fontSize = 18.sp,
                    color = Color(46,138,243),
                    fontWeight = FontWeight.Bold))
            }
            Spacer(modifier = Modifier.height(40.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(0.4f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                items(4) {_ -> Box(modifier = Modifier
                    .height(30.dp)
                    .width(30.dp)
                    .clip(
                        CircleShape
                    )
                    .background(brush = Brush
                        .linearGradient(
                            colors = listOf(
                                Color(109, 51, 245),
                                Color(46, 138, 243)
                            )
                        ),))}
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Sign in with another account", style = TextStyle(fontSize = 14.sp, color = PurpleGrey40))
        }
    }
}

@Composable
fun TopContent(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.35f)
        .clip(WaveCutShape())
        .background(Color(47, 135, 245)),
    ){
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
            .background(Color.Transparent),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "LOGO",
                style = TextStyle(
                    fontSize = 40.sp,
                    color = Color.White),
                fontWeight = FontWeight.Bold)
        }
    }
}

class WaveCutShape : Shape{
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val w = size.width
        val h = size.height
        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(0f, h.times(0.75f))
            quadraticBezierTo(
                w.times(0.2f),
                h.times(0.6f),
                w.times(0.4f),
                h.times(0.8f))
            quadraticBezierTo(
                w.times(0.7f),
                h,
                w,
                h.times(0.75f)
            )
            lineTo(w, 0f)
        }
        return Outline.Generic(path)
    }
}

