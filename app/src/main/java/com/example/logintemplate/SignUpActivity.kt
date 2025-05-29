package com.example.logintemplate

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.logintemplate.ui.theme.LoginButtonColor
import com.example.logintemplate.ui.theme.LoginTemplateTheme
import java.nio.file.WatchEvent

@SuppressLint("UnrememberedMutableState", "UnrememberedMutableInteractionSource")
@Composable
fun SignUp(navController: NavController) {

    val localFocus = LocalFocusManager.current
    var name = remember { mutableStateOf("") }
    var email = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var confirmPassword = remember { mutableStateOf("") }

    ArkaPlan()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = {
                    localFocus.clearFocus()
                }
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(300.dp))

        Text(
            "Create an account", fontWeight = FontWeight.Bold, fontSize = 20.sp,
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(start = 60.dp)
        )


        CustomTextField(
            name,
            Icons.Filled.Person,
            "Name",
            KeyboardOptions(keyboardType = KeyboardType.Text),
            mutableStateOf(true)
        )
        CustomTextField(
            email,
            Icons.Filled.Email,
            "Email",
            KeyboardOptions(keyboardType = KeyboardType.Email),
            mutableStateOf(true)
        )
        CustomTextField(
            password,
            Icons.Filled.Lock,
            "Password",
            KeyboardOptions(keyboardType = KeyboardType.Password),
            mutableStateOf(false)
        )
        CustomTextField(
            confirmPassword,
            Icons.Filled.Lock,
            "Confirm Password",
            KeyboardOptions(keyboardType = KeyboardType.Password),
            mutableStateOf(false)
        )


        Spacer(modifier = Modifier.height(20.dp))

        CustomButton("SIGN UP")

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CustomCheckBox(mutableStateOf(false))
            Text("Remember me", fontSize = 12.sp)

        }

        CustomAccountRow("Already have an account ?", navController, "anasayfa", "Login Up")

    }

}

@Composable
fun CustomTextField(
    enterString: MutableState<String>,
    icon: ImageVector,
    text: String, keyboardOptions: KeyboardOptions,
    showPassword: MutableState<Boolean>
) {
    val localFocus = LocalFocusManager.current
    TextField(
        value = enterString.value,
        onValueChange = { enterString.value = it },
        label = { Text(text) },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color(0xFFB78503),
            focusedContainerColor = Color.Transparent,
            focusedIndicatorColor = LoginButtonColor,
            focusedLabelColor = Color.Black
        ),
        leadingIcon = { Icon(icon, contentDescription = null, tint = LoginButtonColor) },
        trailingIcon = {
            if (text == "Confirm Password" || text == "Password") {
                Icon(
                    if (showPassword.value) {
                        Icons.Filled.Visibility
                    } else {
                        Icons.Filled.VisibilityOff
                    },
                    contentDescription = "Toggle password visibility",
                    modifier = Modifier.clickable { showPassword.value = !showPassword.value }

                )
            }
        },
        visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardOptions.keyboardType,
            imeAction = ImeAction.Next
        ),
        singleLine = true,
        keyboardActions = KeyboardActions (
            onNext = {
                localFocus.moveFocus(FocusDirection.Down)
            }
        ),
        modifier = Modifier.padding(top = 10.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun SignupPreview() {
    LoginTemplateTheme {

    }
}