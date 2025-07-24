package com.example.blogmultiplatform.api.data

import com.example.blogmultiplatform.api.models.User

interface MongoRepository {

    suspend fun checkUserExistence(user: User): User?
}