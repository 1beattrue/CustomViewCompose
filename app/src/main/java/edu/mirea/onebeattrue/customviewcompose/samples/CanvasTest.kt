package edu.mirea.onebeattrue.customviewcompose.samples

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun CanvasTest() {
    var points by rememberSaveable {
        mutableStateOf<List<Point>>(listOf())
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .pointerInput(key1 = Unit) {
                    detectDragGestures(
                        onDragStart = { points += Point(offset = it, isStartPoint = true) },
                        onDrag = { change, _ ->
                            points += change.historical.map {
                                Point(offset = it.position, isStartPoint = false)
                            }
                        }
                    )
                }
        ) {
            drawPath(
                path = Path().apply {
                    points.forEach { point ->
                        if (point.isStartPoint) {
                            moveTo(point.offset.x, point.offset.y)
                        } else {
                            lineTo(point.offset.x, point.offset.y)
                        }
                    }
                },
                brush = Brush.linearGradient(listOf(Color.Cyan, Color.Magenta)),
                style = Stroke(10.dp.toPx()),
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            onClick = { points = listOf() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Text(text = "Clear")
        }
    }
}

data class Point(
    val offset: Offset,
    val isStartPoint: Boolean
)

@Composable
fun Dp.toPx() = with(LocalDensity.current) {
    this@toPx.toPx()
}