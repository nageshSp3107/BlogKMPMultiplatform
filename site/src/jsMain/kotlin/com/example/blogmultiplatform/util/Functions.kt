package com.example.blogmultiplatform.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.core.rememberPageContext
import kotlinx.browser.localStorage
import org.w3c.dom.get

@Composable
fun isUserLoggedIn(content:@Composable () -> Unit){
    val context = rememberPageContext()
    val remembered = remember { localStorage["remember"].toBoolean() }
    val userId = remember { localStorage["userId"].toString() }
    var userIdExist by remember { mutableStateOf(false) }
    println("userIdExist : $userIdExist and userId: $userId")

    LaunchedEffect(Unit){
        userIdExist = if (userId.isNotEmpty()) checkUserId(userId) else false
        if (!remembered || !userIdExist){
            context.router.navigateTo("/admin/login")
        }
    }
    if (remembered && userIdExist){
        content()
    }else{
        println("Loading...")
    }

}