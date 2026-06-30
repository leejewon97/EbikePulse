package com.example.ebikepulse.ui.mockup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.compose.foundation.Canvas

private const val CanvasW = 1080f
private const val CanvasH = 2400f

private const val ScreenX = 20f
private const val ScreenY = 70f
private const val ScreenW = 1040f
private const val ScreenH = 2310f

@Composable
fun MockupPhoneFrame(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current
    fun pxToDp(px: Float): Dp = with(density) { px.toDp() }

    val frameW = pxToDp(CanvasW)
    val frameH = pxToDp(CanvasH)

    val screenX = pxToDp(ScreenX)
    val screenY = pxToDp(ScreenY)
    val screenW = pxToDp(ScreenW)
    val screenH = pxToDp(ScreenH)

    Box(modifier = modifier.size(frameW, frameH)) {
        Canvas(modifier = Modifier.fillMaxSize().zIndex(0f)) {
            val bodyColor = Color(0xFF121212)
            val bezelHighlight = Color(0xFF2D2D2D)
            val punchHole = Color(0xFF080808)

            drawRoundRect(
                color = bodyColor,
                cornerRadius = CornerRadius(56f, 56f),
                style = Fill,
            )

            drawRoundRect(
                color = bezelHighlight,
                topLeft = Offset(4f, 4f),
                size = Size(CanvasW - 8f, CanvasH - 8f),
                cornerRadius = CornerRadius(56f, 56f),
                style = Stroke(width = 2f),
            )

            drawRoundRect(
                color = Color.Black,
                topLeft = Offset(ScreenX - 2f, ScreenY - 2f),
                size = Size(ScreenW + 4f, ScreenH + 4f),
                cornerRadius = CornerRadius(32f, 32f),
                style = Fill,
            )

            drawCircle(
                color = punchHole,
                radius = 14f,
                center = Offset(CanvasW / 2f, 46f),
            )

            drawRoundRect(
                color = bezelHighlight,
                topLeft = Offset(CanvasW - 6f, 520f),
                size = Size(4f, 72f),
                cornerRadius = CornerRadius(2f, 2f),
                style = Fill,
            )
            drawRoundRect(
                color = bezelHighlight,
                topLeft = Offset(CanvasW - 6f, 640f),
                size = Size(4f, 52f),
                cornerRadius = CornerRadius(2f, 2f),
                style = Fill,
            )
        }

        Box(
            modifier = Modifier
                .offset(x = screenX, y = screenY)
                .size(screenW, screenH)
                .clip(RoundedCornerShape(32.dp))
                .background(Color.White)
                .zIndex(1f),
        ) {
            content()
        }
    }
}

