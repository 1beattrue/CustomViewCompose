package edu.mirea.onebeattrue.customviewcompose.samples

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun CanvasTest() {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color.Cyan, Color.Magenta),
                    end = Offset(200.dp.toPx(), 250.dp.toPx()),
                    tileMode = TileMode.Mirror
                )
            )
    ) {
        Log.d("Canvas", (size.width == size.height).toString())
        drawPath(
            path = Path().apply {
                moveTo(center.x - 90.dp.toPx(), center.y)
                lineTo(center.x - 60.dp.toPx(), center.y - 90.dp.toPx())
                lineTo(center.x + 60.dp.toPx(), center.y - 90.dp.toPx())
                lineTo(center.x + 90.dp.toPx(), center.y)
                lineTo(center.x + 60.dp.toPx(), center.y + 90.dp.toPx())
                lineTo(center.x - 60.dp.toPx(), center.y + 90.dp.toPx())
                lineTo(center.x - 90.dp.toPx(), center.y)
            },
            color = Color.White,
            style = Fill
        )
    }
}

@Composable
fun Dp.toPx() = with(LocalDensity.current) {
    this@toPx.toPx()
}