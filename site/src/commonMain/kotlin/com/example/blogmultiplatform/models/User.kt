package com.example.blogmultiplatform.models

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class User {
    val id:String
    val userName: String
    val password:String
}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class UserWithoutPassword {
    val id:String
    val userName: String
}