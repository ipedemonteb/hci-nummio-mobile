package ar.edu.itba.nummio.ui.helpful

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

class TopBorderShape(private val borderWidth: Dp) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            Path().apply {
                moveTo(0f, 0f) // Start at top-left
                lineTo(size.width, 0f) // Move to top-right
                lineTo(size.width, with(density) { borderWidth.toPx() }) // Move down to border width on right
                lineTo(0f, with(density) { borderWidth.toPx() }) // Move to border width on left
                close() // Close the path
            }
        )
    }
}