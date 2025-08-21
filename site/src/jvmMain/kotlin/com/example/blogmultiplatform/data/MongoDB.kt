package com.example.blogmultiplatform.data

import com.example.blogmultiplatform.models.User
import com.example.blogmultiplatform.util.password
import com.example.blogmultiplatform.util.userName
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.model.Filters
import com.mongodb.reactivestreams.client.MongoClients
import com.varabyte.kobweb.api.data.add
import com.varabyte.kobweb.api.init.InitApi
import com.varabyte.kobweb.api.init.InitApiContext
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.serialization.json.Json
import org.litote.kmongo.and

@InitApi
fun initMongoDB(context: InitApiContext) {
    System.setProperty(
        "org.litote.kmongo.mapping.service",
        "org.litote.kmongo.serialization.SerializationClassMappingTypeService"
    )
    context.data.add(MongoDB(context))
}

class MongoDB(private val context: InitApiContext) : MongoRepository {
    // then build client
    private val settings = MongoClientSettings.builder()
        .applyConnectionString(ConnectionString("mongodb://localhost:27017"))
        .build()
    private val client = MongoClients.create(settings)
    private val database = client.getDatabase("my_blog")
    private val userCollection = database.getCollection("users")


    override suspend fun checkUserExistence(user: User): User? {
        context.logger.debug(user.toString())
         return try {
             val filter = Filters.and(
                 Filters.eq(userName, user.userName),
                 Filters.eq(password, user.password)
             )
             val value = userCollection.find(
                 and(
                     filter
                 )
             ).awaitFirstOrNull()
             Json.decodeFromString<User>(value?.toJson()?:"")
         }catch (e: Exception){
             context.logger.error(e.message.toString())
             null
         }
    }
}