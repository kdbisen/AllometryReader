package com.allometry.allometryreader.screens


import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.allometry.allometryreader.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

//@Preview
@Composable
fun ReaderSplashScreen(
    navController: NavHostController
) {

    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true ) {
        scale.animateTo(targetValue = 0.9f, animationSpec = tween(800){
            OvershootInterpolator(8f).getInterpolation(it)
        })
        delay(2000L)

        if(FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
            navController.navigate(ReaderScreens.LoginScreen.name)
        }else {
            navController.navigate(ReaderScreens.ReaderHomeScreen.name)
        }


    }

    Surface(
        modifier = Modifier
            .padding(15.dp)
            .size(330.dp)
            .scale(scale.value),
        shape = CircleShape,
        border = BorderStroke(width = 2.dp, color = Color.LightGray)
    ) {

        Column(modifier = Modifier.padding(1.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(text = "A. Reader", style = MaterialTheme.typography.h3, color = Color.Red.copy(alpha =0.5f))
            Text(text = "\"Read. Change. YOurself\"", style = MaterialTheme.typography.h5,color = Color.LightGray  )
        }

    }

}