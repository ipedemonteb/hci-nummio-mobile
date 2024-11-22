package ar.edu.itba.nummio

import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "phone",
    group = "screen size",
    device = "spec:width=411dp,height=891dp"
)

@Preview(
    name = "tablet",
    group = "screen size",
    device = "spec:width=1280dp,height=800dp,dpi=240"
)

annotation class PreviewScreenSizes()
