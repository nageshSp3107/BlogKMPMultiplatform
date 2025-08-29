package com.example.blogmultiplatform.components

import androidx.compose.runtime.Composable
import com.example.blogmultiplatform.models.Theme
import com.example.blogmultiplatform.styles.NavigationTextStyle
import com.example.blogmultiplatform.util.Constants.FONT_FAMILY
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh

@Composable
fun SidePanel(){

    Column(
        modifier = Modifier
            .padding (leftRight = 40.px, topBottom = 50.px)
            .width(250.px)
            .height(100.vh)
            .position(Position.Fixed)
            .backgroundColor(Theme.Secondary.rgb)
            .zIndex(9)
    ) {

        SpanText(
            modifier = Modifier
                .margin (bottom = 30.px)
                .color(Theme.HalfWhite.rgb)
                .fontFamily(FONT_FAMILY),
            text = "Dashboard"
        )

        NavigationItem(
            modifier = Modifier.margin (bottom = 24.px),
            selectedItem = true,
            title = "Home",
            onClick = {}
        )

        NavigationItem(
            modifier = Modifier.margin (bottom = 24.px),
            selectedItem = false,
            title = "Create Post",
            onClick = {}
        )

        NavigationItem(
            modifier = Modifier.margin (bottom = 24.px),
            selectedItem = false,
            title = "My Posts",
            onClick = {}
        )

        NavigationItem(
            modifier = Modifier.margin (bottom = 24.px),
            selectedItem = false,
            title = "Logout",
            onClick = {}
        )

    }
}

@Composable
fun NavigationItem(
    modifier: Modifier,
    selectedItem: Boolean = false,
    title:String,
    onClick:() -> Unit
){
    Row(
        modifier = NavigationTextStyle.toModifier()
            .id("navItem")
            .then(modifier)
            .cursor(Cursor.Pointer)
            .onClick { onClick.invoke() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        SpanText(
            modifier = Modifier
                .id("navigationText")
                .then(modifier)
                .fontFamily(FONT_FAMILY)
                .fontSize(16.px),
            text = title
        )


    }
}
