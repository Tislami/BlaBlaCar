package com.zeroone.blablacar.domain.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.zeroone.blablacar.domain.model.Post2
import com.zeroone.blablacar.domain.model.Response
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

interface PostRepository {
    fun newPost(post2: Post2) : Flow<Response<Boolean>>
    fun getAllPost() : Flow<Response<List<Post2>>>
}

class PostRepositoryImpl(private val postCollection: CollectionReference) : PostRepository {


    override fun newPost(post2: Post2) = flow {
        var result: Response<Boolean> = Response.Loading
        emit(result)
        try {
            postCollection.document().set(post2).addOnSuccessListener {
                result = Response.Success(true)
            }.addOnFailureListener{
                result = Response.Error("Oops ${it.message}")
            }.await()
            emit(result)
        }
        catch (e:Exception){
            emit(Response.Error(e.message?:"Unknown"))
        }
    }

    override fun getAllPost(): Flow<Response<List<Post2>>> = callbackFlow {
        val snapshot = postCollection.addSnapshotListener { value, error ->
            val response = if (value != null) {
                val posts = value.toObjects(Post2::class.java)
                Response.Success(posts)
            } else {
                Response.Error(error?.message ?: "")
            }
            trySend(response)
        }
        awaitClose {
            snapshot.remove()
        }
    }

}