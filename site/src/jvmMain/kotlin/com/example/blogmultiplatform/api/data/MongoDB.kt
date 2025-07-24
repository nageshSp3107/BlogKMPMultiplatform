package com.example.blogmultiplatform.api.data

import com.example.blogmultiplatform.api.models.User
import com.mongodb.reactivestreams.client.MongoClients
import com.varabyte.kobweb.api.data.add
import com.varabyte.kobweb.api.init.InitApiContext
import com.varabyte.kobweb.api.init.InitApi
import kotlinx.coroutines.reactive.awaitFirst
import org.litote.kmongo.and
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.getCollection

@InitApi
fun initMongoDB(context: InitApiContext){
    System.setProperty(
        "org.litote.mongo.test.mapping.service",
        "org.litote.kmongo.serialization.SerializationClassMappingTypeService"
    )
    context.data.add(MongoDB(context))
}

class MongoDB(private val context: InitApiContext) : MongoRepository {
    private val client = MongoClients.create("")
    private val database = client.getDatabase("my_blog")
    private val userCollection = database.getCollection<User>()


    override suspend fun checkUserExistence(user: User): User? {
         return try {
             userCollection
                 .find(
                     and(
                         User::userName eq user.userName,
                         User::password eq user.password
                     )
                 )
                 .awaitFirst()
         }catch (e: Exception){
             context.logger.error(e.message.toString())
             null
         }
    }
}