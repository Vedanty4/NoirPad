package com.example.noirpad.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// 🎨 NoirPad Custom Colors
private val NoirDarkColorScheme = darkColorScheme(
    primary = Color(0xFFD4AF37),     // Gold accent
    secondary = Color(0xFF9E9E9E),   // Muted gray
    tertiary = Color(0xFFE57373),    // Soft red
    background = Color(0xFF121212),  // Deep black
    surface = Color(0xFF1E1E1E),     // Slightly lighter than background
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

private val NoirLightColorScheme = lightColorScheme(
    primary = Color(0xFF6A1B9A),     // Rich purple
    secondary = Color(0xFF757575),   // Neutral gray
    tertiary = Color(0xFFE57373),    // Soft red
    background = Color(0xFFFDFDFD),  // Clean white
    surface = Color(0xFFFFFFFF),     // Slightly elevated cards
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onTertiary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

// 🖋 Custom Typography
val NoirTypography = androidx.compose.material3.Typography(
    titleLarge = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        color = Color.Unspecified
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp
    )
)

@Composable
fun NoirPadTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> NoirDarkColorScheme
        else -> NoirLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = NoirTypography, // 🔥 Use custom typography
        content = content
    )
}
