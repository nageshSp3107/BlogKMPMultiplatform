package com.example.blogmultiplatform.pages.admin

import androidx.compose.runtime.Composable
import com.example.blogmultiplatform.components.AdminPageLayout
import com.example.blogmultiplatform.models.Theme
import com.example.blogmultiplatform.navigation.Screen
import com.example.blogmultiplatform.util.Image
import com.example.blogmultiplatform.util.isUserLoggedIn
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxHeight
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.framework.annotations.DelicateApi
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.FaPlus
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh
import org.w3c.dom.Window

@Page()
@Composable
fun AdminPage(){
    isUserLoggedIn {
        HomePage()
    }
}

@Composable
fun HomePage(){
    AdminPageLayout{
        HomeContent()
        AddButton()
    }

}

@Composable
private fun HomeContent(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(topBottom = 50.px)
            .maxWidth(1920.px),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .margin(bottom = 30.px)
                .height(150.px),
            src = Image.laugh,
            description = "laugh"
        )

        SpanText(
            modifier = Modifier
                .margin(bottom = 14.px)
                .fontSize(28.px)
                .fontFamily("Roboto"),
            text = "Hello Nagesh"
        )
        SpanText(
            modifier = Modifier
                .fontSize(20.px)
                .fontFamily("Roboto")
                .color(Theme.HalfBlack.rgb),
            text = "The content of joke here"
        )
    }
}


@OptIn(DelicateApi::class)
@Composable
private fun AddButton(){
    val breakPoint = rememberBreakpoint()
    val pagerContext  = rememberPageContext()
    Box (
        modifier = Modifier
            .height(100.vh)
            .fillMaxWidth()
            .maxWidth(1920.px)
            .position(Position.Fixed)
            .zIndex(9),
        contentAlignment = Alignment.BottomEnd
    ){
        Box(
            modifier = Modifier
                .margin(
                    right = if(breakPoint > Breakpoint.MD) 40.px else 20.px,
                    bottom = if(breakPoint > Breakpoint.MD) 40.px else 20.px
                )
                .backgroundColor(Theme.Primary.rgb)
                .size(if(breakPoint > Breakpoint.MD) 80.px else 50.px)
                .borderRadius (14.px)
                .cursor(Cursor.Pointer)
                .onClick {
                    pagerContext.router.navigateTo(Screen.AdminCreate.route)
                },
            contentAlignment = Alignment.Center
        ) {
            FaPlus(
                modifier = Modifier.color(Theme.White.rgb),
                size = IconSize.LG
            )
        }
    }

}