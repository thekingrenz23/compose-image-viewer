package com.fusiontechph.renz.imageviewer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun FusionImageViewer() {
    var offset by remember { mutableStateOf(Offset.Zero) }
    var zoom by remember { mutableStateOf(1f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .pointerInput(Unit) {
                detectTransformGestures { centroid, pan, gestureZoom, _ ->
                    val oldScale = zoom
                    val newScale = maxOf(1f, minOf(3f, zoom * gestureZoom))
                    offset = (offset + centroid / oldScale) -
                            (centroid / newScale + pan / oldScale)
                    zoom = newScale
                }
            }
    ) {
        Image(
            painter = rememberImagePainter("https://i.imgflip.com/1bij.jpg"),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    val transLateX = -offset.x * zoom
                    val translateY = -offset.y * zoom

                    translationX = transLateX
                    translationY = translateY
                    scaleX = zoom
                    scaleY = zoom
                    transformOrigin = TransformOrigin(0f, 0f)
                },
            contentScale = ContentScale.Fit
        )
    }
}