package com.example.blogmultiplatform.components

import androidx.compose.runtime.Composable
import com.example.blogmultiplatform.models.Theme
import com.example.blogmultiplatform.navigation.Screen
import com.example.blogmultiplatform.styles.NavigationTextStyle
import com.example.blogmultiplatform.util.Constants.FONT_FAMILY
import com.example.blogmultiplatform.util.logout
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh

@Composable
fun SidePanel(){
    val context = rememberPageContext()
    Column(
        modifier = Modifier
            .padding (leftRight = 40.px, topBottom = 80.px)
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
            modifier = Modifier.margin (bottom = 10.px),
            selectedItem = context.route.path == Screen.AdminHome.route,
            title = "Home",
            onClick = {
                context.router.navigateTo(Screen.AdminHome.route)
            }
        )

        NavigationItem(
            modifier = Modifier.margin (bottom = 10.px),
            selectedItem = context.route.path == Screen.AdminCreate.route,
            title = "Create Post",
            onClick = {
                context.router.navigateTo(Screen.AdminCreate.route)
            }
        )

        NavigationItem(
            modifier = Modifier.margin (bottom = 10.px),
            selectedItem = context.route.path == Screen.AdminMyPosts.route,
            title = "My Posts",
            onClick = {
                context.router.navigateTo(Screen.AdminMyPosts.route)
            }
        )

        NavigationItem(
            modifier = Modifier.margin (bottom = 10.px),
            selectedItem = false,
            title = "Logout",
            onClick = {
                logout()
                context.router.navigateTo(Screen.AdminLogin.route)
            }
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
                .fontSize(16.px)
                .then(
                    if (selectedItem) Modifier.color(Theme.Primary.rgb) else Modifier
                    ),
            text = title
        )


    }
}
