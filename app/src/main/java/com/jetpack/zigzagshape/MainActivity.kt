package com.jetpack.zigzagshape

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.zigzagshape.ui.theme.ZigZagShapeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZigZagShapeTheme {
                val shape = CustomShape()
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = "ZigZag View",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            )
                        }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .fillMaxHeight(0.6f),
                                shape = shape,
                                backgroundColor = Color.Red
                            ) {
                                Column(
                                    modifier = Modifier
                                        .wrapContentSize()
                                        .background(shape = shape, color = Color.Green),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Welcome to Jetpack",
                                        modifier = Modifier
                                            .padding(30.dp),
                                        color = Color.Black,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun drawCustomPath(size: Size, waveCount: Int): Path {
    val waveLength = size.width / (waveCount + 1)
    val waveHeight = waveLength / 5
    val gap = 3*waveLength/4
    return Path().apply {
        reset()
        moveTo(0f, 0f)
        arcTo(
            rect = Rect(topLeft = Offset(-waveLength/4, 0f),
                bottomRight = Offset(waveLength / 4, waveHeight)),
            startAngleDegrees = 270f,
            sweepAngleDegrees = 90f,
            forceMoveTo = false
        )
        arcTo(
            rect = Rect(topLeft = Offset(waveLength / 4, 0f),
                bottomRight = Offset(gap, waveHeight)),
            startAngleDegrees = 180f,
            sweepAngleDegrees = -180f,
            forceMoveTo = false
        )
        for (i in 1..waveCount) {
            arcTo(
                rect = Rect(topLeft = Offset(gap + waveLength * (i-1), 0f),
                    bottomRight = Offset(gap + waveLength * (i-1) + waveLength/2, waveHeight)),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )
            arcTo(
                rect = Rect(topLeft = Offset(gap + waveLength * (i-1) + waveLength/2, 0f),
                    bottomRight = Offset(gap + waveLength * i, waveHeight)),
                startAngleDegrees = 180f,
                sweepAngleDegrees = -180f,
                forceMoveTo = false
            )
        }
        arcTo(
            rect = Rect(topLeft = Offset(size.width - waveLength/4, 0f),
                bottomRight = Offset(size.width + waveLength/4, waveHeight)),
            startAngleDegrees = 180f,
            sweepAngleDegrees = 90f,
            forceMoveTo = false
        )
        lineTo(size.width, size.height)
        arcTo(
            rect = Rect(
                topLeft = Offset(size.width - waveLength/4, size.height - waveHeight),
                bottomRight = Offset(size.width + waveLength/4, size.height)),
            startAngleDegrees = 90f,
            sweepAngleDegrees = 90f,
            forceMoveTo = false
        )
        arcTo(
            rect = Rect(
                topLeft = Offset(size.width - gap,
                    size.height - waveHeight),
                bottomRight = Offset(size.width - waveLength/4, size.height)),
            startAngleDegrees = 0f,
            sweepAngleDegrees = -180f,
            forceMoveTo = false
        )
        for (i in 1..waveCount) {
            arcTo(rect = Rect(
                topLeft = Offset(size.width - gap - waveLength*(i-1) - waveLength/2,
                    size.height - waveHeight),
                bottomRight = Offset(size.width - gap - waveLength*(i-1),
                    size.height)),
                startAngleDegrees = 0f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )
            arcTo(
                rect = Rect(
                    topLeft = Offset(size.width - gap - waveLength * i,
                        size.height - waveHeight),
                    bottomRight = Offset(size.width - gap - waveLength*(i-1) - waveLength/2,
                        size.height)),
                startAngleDegrees = 0f,
                sweepAngleDegrees = -180f,
                forceMoveTo = false
            )
        }
        arcTo(
            rect = Rect(topLeft = Offset(-waveLength/4, size.height - waveHeight),
                bottomRight = Offset(waveLength/4, size.height)),
            startAngleDegrees = 0f,
            sweepAngleDegrees = 90f,
            forceMoveTo = false
        )
        close()
    }
}


























