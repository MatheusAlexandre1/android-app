package com.bluedot.home.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bluedot.core.base.BaseViewModel
import com.bluedot.core.extensions.defaultValue
import com.bluedot.core.model.Movie
import com.bluedot.data.helpers.Response
import com.bluedot.data.repository.MovieRepository
import kotlinx.coroutines.flow.*

private const val START_PAGE = 1

class SearchViewModel(private val repository: MovieRepository) : BaseViewModel() {

    private val page = MutableLiveData<Int>().defaultValue(START_PAGE)

    private val _result = MutableLiveData<Response<MutableList<Movie>>>()
    val result: LiveData<Response<MutableList<Movie>>>
        get() = _result

    fun searchMovies(query: String, page: Int = 1) {
        repository.searchMovies(query = query, page = page)
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .map { _result.postValue(Response.Success(it.map(::Movie).toMutableList())) }
            .catch { _result.postValue(Response.Failure(it)) }
            .launchIn(viewModelScope)
    }

    fun updatePage(index: Int) {
        page.value = index
    }

    fun getPage() = page.value ?: START_PAGE
}