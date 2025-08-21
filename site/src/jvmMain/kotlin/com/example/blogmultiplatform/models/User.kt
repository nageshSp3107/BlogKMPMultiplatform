package com.example.blogmultiplatform.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
actual data class User(
    @SerialName("_id")
    actual val id: String = org.bson.types.ObjectId().toHexString(),
    @SerialName("userName")
    actual val userName:String = "",
    @SerialName("password")
    actual val password:String = ""
)


@Serializable
actual data class UserWithoutPassword(
    @SerialName("_id")
    actual val id: String = org.bson.types.ObjectId().toHexString(),
    @SerialName("userName")
    actual val userName:String = ""
)
