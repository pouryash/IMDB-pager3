package com.example.imdb_test.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.imdb_test.data.RIM
import com.example.imdb_test.data.ResultWrapper
import com.example.imdb_test.data.Status
import com.example.imdb_test.data.models.DiscoverMovie
import com.example.imdb_test.data.repo.DiscoverMovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class DiscoverMovieViewModel(val repo: DiscoverMovieRepository) : ViewModel() {

    var discoverMovieLiveData: MutableLiveData<RIM<DiscoverMovie>> = MutableLiveData()

    fun getDiscoverMovie(): Flow<PagingData<DiscoverMovie.Movie>> {
        val newResult: Flow<PagingData<DiscoverMovie.Movie>> = repo.getDiscoverMovie(mapOf())
            .cachedIn(viewModelScope)
        return newResult
    }

//    fun getDiscoverMovie(){
//        viewModelScope.launch {
//            discoverMovieLiveData.value = RIM(Status.LOADING)
//            when(val result = repo.getDiscoverMovie(mapOf())){
//                is ResultWrapper.Success -> {
//                    discoverMovieLiveData.value = RIM(Status.SUCCESSFUL, result.value)
//                }
//                is ResultWrapper.NetworkError -> {
//                    discoverMovieLiveData.value = RIM(Status.ERROR, error = result.error?.message)
//                }
//                is ResultWrapper.GenericError -> {
//                    discoverMovieLiveData.value = RIM(Status.ERROR, error = result.error)
//                }
//            }
//        }
//    }

}