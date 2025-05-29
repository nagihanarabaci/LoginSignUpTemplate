package com.example.logintemplate

import android.annotation.SuppressLint
import android.graphics.RuntimeShader
import android.os.Bundle
import android.text.Layout
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.logintemplate.ui.theme.LoginButtonColor
import com.example.logintemplate.ui.theme.LoginTemplateTheme
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginTemplateTheme {
                SayfaGecisleri()

            }
        }
    }
}

@Composable
fun SayfaGecisleri() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "anasayfa") {
        composable("anasayfa") {
            Sayfa(navController = navController)
        }
        composable("sign_up") {
            SignUp(navController = navController)
        }
    }

}


@Composable
fun ArkaPlan() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFFAB400),
                        Color(0xFFFAE7B3),
                        Color(0xFFFFFFFF),
                        Color(0xFFFFFFFF),
                        Color(0xFFFAE7B7),
                        Color(0xABFFC15E)
                    )
                )
            )
    )

    Box {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart),
            painter = painterResource(R.drawable.login2),
            contentScale = ContentScale.FillWidth,
            contentDescription = null,
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 50.dp, 50.dp),
            painter = painterResource(R.drawable.login),
            contentScale = ContentScale.FillWidth,
            contentDescription = null,
        )
    }
}

@Composable
fun CustomCheckBox(checked: MutableState<Boolean>) {
    Checkbox(
        checked = checked.value,
        onCheckedChange = { checked.value = it },
        modifier = Modifier.padding(start = 50.dp),
        colors = colors(
            checkmarkColor = Color.Gray,
            checkedColor = LoginButtonColor,
            uncheckedColor = LoginButtonColor,
            disabledUncheckedColor = LoginButtonColor
        )
    )

}

@Composable
fun CustomAccountRow(
    firstText: String,
    navControllerPage: NavController,
    routePage: String,
    secondText: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(firstText, fontWeight = FontWeight.Bold)
        TextButton (
            onClick = {
                navControllerPage.navigate(routePage)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Black,
                disabledContainerColor = Color.Transparent
            ),
        ) {
            Text(
                secondText,
                fontWeight = FontWeight.ExtraBold,
                color = LoginButtonColor,
                textDecoration = TextDecoration.Underline,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun CustomButton(text: String) {
    Button(
        onClick = { TODO() },
        modifier = Modifier.size(280.dp, 42.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = LoginButtonColor,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(6.dp)

    ) {
        Text(text, fontWeight = FontWeight.Bold)
    }
}


@SuppressLint("UnrememberedMutableState")
@Composable
fun Sayfa(navController: NavController) {

    val localFocus = LocalFocusManager.current
    var loginEmail = remember { mutableStateOf("") }
    var loginPassword = remember { mutableStateOf("") }

    ArkaPlan()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable{
                localFocus.clearFocus()
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        Text(
            "Login",
            fontWeight = FontWeight.Bold,
            fontSize = 50.sp,
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(start = 60.dp)
        )

        CustomTextField(
            loginEmail,
            Icons.Filled.Email,
            "Email",
            KeyboardOptions(keyboardType = KeyboardType.Email),
            mutableStateOf(true)
        )
        CustomTextField(
            loginPassword,
            Icons.Filled.Lock,
            "Password",
            KeyboardOptions(keyboardType = KeyboardType.Password),
            mutableStateOf(false)
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomButton("LOGIN")

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CustomCheckBox(mutableStateOf(false))
            Text("Remember me", fontSize = 10.sp, modifier = Modifier.padding(end = 10.dp))
            Text("Forgot password?", fontSize = 10.sp, modifier = Modifier.padding(start = 80.dp))
        }

        CustomAccountRow("Don't have an account ?", navController, "sign_up", "Sign up")

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LoginTemplateTheme {
        SayfaGecisleri()
    }
}