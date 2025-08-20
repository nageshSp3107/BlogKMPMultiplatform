package com.example.blogmultiplatform.pages.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.blogmultiplatform.models.Theme
import com.example.blogmultiplatform.models.User
import com.example.blogmultiplatform.models.UserWithoutPassword
import com.example.blogmultiplatform.util.Id
import com.example.blogmultiplatform.util.checkUserExistence
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.outline
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.browser.document
import kotlinx.browser.localStorage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Input
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.set
import kotlin.time.Duration.Companion.seconds

@Page()
@Composable
fun LoginScreen(){
    var errorText by remember { mutableStateOf(" ") }
    val context = rememberPageContext()
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding (leftRight = 50.px, top = 80.px, bottom = 24.px)
                .backgroundColor(Theme.LightGray.rgb),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Input(
                type = InputType.Text,
                attrs = Modifier
                    .id(Id.usernameInput)
                    .margin (bottom = 12.px)
                    .width(350.px)
                    .height(54.px)
                    .padding (leftRight = 20.px)
                    .backgroundColor(Colors.White)
                    .border (
                        width = 0.px,
                        style = LineStyle.None,
                        color = Colors.Transparent
                    )
                    .toAttrs {
                        attr("placeholder", "Username")
                    }
            )

            Input(
                type = InputType.Password,
                attrs = Modifier
                    .id(Id.passwordInput)
                    .margin (bottom = 12.px)
                    .width(350.px)
                    .height(54.px)
                    .padding (leftRight = 20.px)
                    .backgroundColor(Colors.White)
                    .border (
                        width = 0.px,
                        style = LineStyle.None,
                        color = Colors.Transparent
                    )
                    .toAttrs {
                        attr("placeholder", "Password")
                    }
            )

            Button(
                attrs = Modifier
                    .width(350.px)
                    .height(54.px)
                    .backgroundColor(Theme.Primary.rgb)
                    .color(Colors.White)
                    .borderRadius (r = 4.px)
                    .fontFamily()
                    .fontWeight(FontWeight.Medium)
                    .fontSize(16.px)
                    .border (
                        width = 0.px,
                        style = LineStyle.None,
                        color = Colors.Transparent
                    )
                    .cursor(Cursor.Pointer)
                    .onClick {
                        scope.launch {
                            val username = (document.getElementById(Id.usernameInput) as HTMLInputElement).value
                            val password = (document.getElementById(Id.passwordInput) as HTMLInputElement).value
                            if (username.isNotEmpty() && password.isNotEmpty()){
                                val user = checkUserExistence(
                                    User(
                                        userName = username,
                                        password = password
                                    )
                                )
                                if (user!=null){
                                    rememberLoggedIn(true, user)
                                    context.router.navigateTo("admin/home")
                                }else{
                                    errorText = "The user doesn't exist,"
                                    delay(3.seconds)
                                    errorText = ""
                                }

                            }else{
                                errorText = "Input fields are empty"
                                delay(3.seconds)
                                errorText = ""
                            }
                        }

                    }
                    .outline (
                        width = 0.px,
                        style = LineStyle.None,
                        color = Colors.Transparent
                    )
                    .toAttrs ()
            ) {
                SpanText(
                    text = "Sign In"
                )
            }
            SpanText(
                modifier = Modifier
                    .width(350.px)
                    .color(Colors.Red)
                    .textAlign(TextAlign.Center),
                text = errorText
            )
        }
    }
}

private fun rememberLoggedIn(
    remember: Boolean,
    userWithoutPassword: UserWithoutPassword?
){
    remember.toString().also { localStorage["remember"] = it }
    if (userWithoutPassword!=null){
        localStorage["userId"] = userWithoutPassword.id
        localStorage["username"] = userWithoutPassword.userName
    }
}