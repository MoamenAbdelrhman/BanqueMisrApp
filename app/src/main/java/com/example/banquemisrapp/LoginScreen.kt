package com.example.banquemisrapp

import android.app.Activity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banquemisrapp.ui.theme.BanqueMisrAppTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.LocaleListCompat
import java.util.Locale
import androidx.compose.foundation.text.input.OutputTransformation
import com.example.banquemisrapp.ui.theme.BmGrey
import com.example.banquemisrapp.ui.theme.BmLightPink
import com.example.banquemisrapp.ui.theme.BmRed

@Composable
fun LoginScreen() {

    val isArabic = LocaleManager.isArabic
    val layoutDirection = if (isArabic) LayoutDirection.Rtl else LayoutDirection.Ltr
    val context = LocalContext.current

    val configuration = LocalConfiguration.current

    val localizedContext = remember(isArabic) {
        val locale = LocaleManager.currentLocale
        val config = android.content.res.Configuration(configuration)
        config.setLocale(locale)
        context.createConfigurationContext(config)
    }

    val usernameState = rememberTextFieldState()
    val passwordState = rememberTextFieldState()
    var passwordVisible by remember { mutableStateOf(false) }

    CompositionLocalProvider(
        LocalLayoutDirection provides layoutDirection,
        LocalContext provides localizedContext
    ) {
        Scaffold(
            containerColor = Color.White
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp),

                ) {
                Spacer(modifier = Modifier.height(48.dp))

                // Header: Logo + Language Switch
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bm_icon),
                        contentDescription = "Banque Misr Logo",
                        modifier = Modifier.height(56.dp)
                    )

                    Text(
                        text = if (isArabic) "English" else "العربية",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = BmRed,
                        modifier = Modifier.clickable {
                            LocaleManager.toggle(context)
                        }
                    )
                }

                Spacer(modifier = Modifier.height(48.dp))

                // Username
                OutlinedTextField(
                    state = usernameState,
                    label = { Text(stringResource(R.string.username)) },
                    modifier = Modifier.fillMaxWidth(),
                    lineLimits = TextFieldLineLimits.SingleLine
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Password
                OutlinedTextField(
                    state = passwordState,
                    label = { Text(stringResource(R.string.password)) },
                    modifier = Modifier.fillMaxWidth(),
                    lineLimits = TextFieldLineLimits.SingleLine,
                    inputTransformation = InputTransformation.maxLength(50),

                    // ✅ ده اللي بيخفي الباسورد فعلاً
                    outputTransformation = if (passwordVisible) null else OutputTransformation {
                        replace(0, length, "•".repeat(length))
                    },

                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            val icon = if (passwordVisible)
                                Icons.Default.VisibilityOff
                            else
                                Icons.Default.Visibility

                            Icon(
                                imageVector = icon,
                                contentDescription = if (passwordVisible) "Hide password" else "Show password",
                                tint = Color.Gray
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(R.string.forgot_username_password),
                    color = BmGrey,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .clickable { },
                    style = TextStyle(
                        textDecoration = TextDecoration.Underline
                    ),
                )

                Spacer(modifier = Modifier.height(40.dp))

                // Login Button
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BmRed,
                        disabledContainerColor = BmLightPink
                    ),
                    shape = RoundedCornerShape(6.dp),
                    enabled = usernameState.text.isNotBlank() &&
                            passwordState.text.isNotBlank()
                ) {
                    Text(
                        text = stringResource(R.string.login),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Contact Us
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(R.string.need_help), color = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(R.string.contact_us),
                        color = Color(0xFFB71C1C),
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.clickable { },
                        fontSize = 16.sp,
                        style = TextStyle(
                            textDecoration = TextDecoration.Underline
                        ),
                    )
                }

                Spacer(modifier = Modifier.height(36.dp))

                HorizontalDivider(Modifier, thickness = 1.dp, color = Color(0xFFE0E0E0))

                Spacer(modifier = Modifier.height(36.dp))

                // Bottom Icons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    BottomNavItem(
                        icon = painterResource(R.drawable.our_products),
                        label = stringResource(R.string.our_products)
                    )
                    BottomNavItem(
                        icon = painterResource(R.drawable.exchange_rate),
                        label = stringResource(R.string.exchange_rate)
                    )
                    BottomNavItem(
                        icon = painterResource(R.drawable.security_tips),
                        label = stringResource(R.string.security_tips)
                    )
                    BottomNavItem(
                        icon = painterResource(R.drawable.nearest_branch_or_atm),
                        label = stringResource(R.string.nearest_branch_or_atm)
                    )
                }
            }
        }
    }

}

@Composable
fun BottomNavItem(icon: Painter, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(72.dp)
    ) {
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = label,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            lineHeight = 22.sp,
            color = Color(0xFF444444)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    BanqueMisrAppTheme {
        LoginScreen()
    }
}
