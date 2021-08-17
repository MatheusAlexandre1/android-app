package com.bluedot.movie.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bluedot.core.base.BaseViewModel
import com.bluedot.core.model.Movie
import com.bluedot.data.helpers.Response
import com.bluedot.data.model.MovieType
import com.bluedot.data.repository.MovieRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class MovieListViewModel(private val repository: MovieRepository) : BaseViewModel() {

    private val _movieList = MutableLiveData<Response<MutableList<Movie>>>()
    val movieList: LiveData<Response<MutableList<Movie>>>
        get() = _movieList

    fun getMovies(type: MovieType, page: Int = 1) {
        repository.getMoviesByType(type, page)
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .map { _movieList.postValue(Response.Success(it.map(::Movie).toMutableList())) }
            .catch { _movieList.postValue(Response.Failure(it)) }
            .launchIn(viewModelScope)
    }
}