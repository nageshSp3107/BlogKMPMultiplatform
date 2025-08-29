package com.example.blogmultiplatform.navigation

sealed class Screen(val route:String) {
    object AdminLogin : Screen("/admin/login")
    object AdminHome : Screen("/admin/")
    object AdminCreate : Screen("/admin/create")
    object AdminMyPosts : Screen("/admin/myposts")
}