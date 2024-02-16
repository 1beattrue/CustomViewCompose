package edu.mirea.onebeattrue.customviewcompose.samples

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun CanvasTest() {
    var points by rememberSaveable {
        mutableStateOf<List<Offset>>(listOf())
    }
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
            .pointerInput(key1 = Unit) {
                detectTapGestures { offset ->
                    points = points + offset
                }
            }
    ) {
        drawPath(
            path = Path().apply {
                points.forEachIndexed { index, offset ->
                    if (index == 0) {
                        moveTo(offset.x, offset.y)
                        drawCircle(
                            center = offset,
                            radius = 10.dp.toPx(),
                            brush = Brush.linearGradient(listOf(Color.Red, Color.Yellow)),
                            style = Fill
                        )
                    } else {
                        lineTo(offset.x, offset.y)
                        drawCircle(
                            center = offset,
                            radius = 10.dp.toPx(),
                            brush = Brush.linearGradient(listOf(Color.Red, Color.Yellow)),
                            style = Fill
                        )
                    }
                }
            },
            brush = Brush.linearGradient(listOf(Color.Red, Color.Yellow)),
            style = Stroke(20.dp.toPx()),
        )
    }
}

@Composable
fun Dp.toPx() = with(LocalDensity.current) {
    this@toPx.toPx()
}