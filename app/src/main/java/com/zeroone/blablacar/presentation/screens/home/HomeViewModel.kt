package com.zeroone.blablacar.presentation.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeroone.blablacar.domain.model.Post2
import com.zeroone.blablacar.domain.model.Response
import com.zeroone.blablacar.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    val postState = mutableStateOf<List<Post2>>(emptyList())
    val isLoading = mutableStateOf(true)

    init {
        getAllPost()
    }


    fun getAllPost(){
        viewModelScope.launch {
            postRepository.getAllPost().collect{response->
                when(response){
                    is Response.Error -> {

                    }
                    Response.Loading -> {

                    }
                    is Response.Success -> {
                        postState.value = response.data
                    }
                }
            }
        }
    }

}