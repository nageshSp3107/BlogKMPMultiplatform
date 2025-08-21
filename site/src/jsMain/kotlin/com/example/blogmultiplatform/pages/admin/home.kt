package com.example.blogmultiplatform.pages.admin

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.text.SpanText

@Page()
@Composable
fun HomePage(){
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    )

    SpanText(
        text = "Hello Nagesh!"
    )
}