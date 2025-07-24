package com.example.blogmultiplatform.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.litote.kmongo.id.ObjectIdGenerator

@Serializable
data class User(
    @SerialName("_id")
    val id:String = ObjectIdGenerator.newObjectId<String>().id.toHexString(),
    val userName:String = "",
    val password:String = ""
)
