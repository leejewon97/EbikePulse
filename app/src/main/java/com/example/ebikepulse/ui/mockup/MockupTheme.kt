package com.example.ebikepulse.ui.mockup

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val MockupPrimary = Color(MockupData.primaryHex)
private val MockupOnPrimary = Color.White

private val MockupLightColors = lightColorScheme(
    primary = MockupPrimary,
    onPrimary = MockupOnPrimary,
    primaryContainer = Color(0xFFB2DFDB),
    onPrimaryContainer = Color(0xFF004D40),
    secondary = Color(0xFF4DB6AC),
    onSecondary = Color.White,
    tertiary = Color(0xFF00695C),
    background = Color(0xFFF5F7F6),
    onBackground = Color(0xFF1C1B1F),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1C1B1F),
    surfaceVariant = Color(0xFFE7EDEB),
    onSurfaceVariant = Color(0xFF5C635F),
    surfaceContainerLow = Color(0xFFF0F4F3),
    outline = Color(0xFFB8C4C0),
    outlineVariant = Color(0xFFD5DFDB),
)

private val MockupTypography = Typography(
    displayLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 52.sp,
        lineHeight = 56.sp,
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp,
        color = Color(0xFF5C635F),
    ),
)

@Composable
fun MockupTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MockupLightColors,
        typography = MockupTypography,
        content = content,
    )
}
