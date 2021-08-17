package com.bluedot.movie.ui.list

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluedot.core.base.BaseActivity
import com.bluedot.core.helpers.EndlessScrollListener
import com.bluedot.core.model.Movie
import com.bluedot.core.route.EXTRA_MOVIE_TYPE
import com.bluedot.core.route.intentToMovie
import com.bluedot.core.ui.MovieListAdapter
import com.bluedot.data.helpers.fold
import com.bluedot.data.model.MovieType
import com.bluedot.movie.R
import com.bluedot.movie.databinding.ActivityListBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MovieListActivity : BaseActivity<ActivityListBinding>() {

    private val viewModel: MovieListViewModel by viewModel()

    private val adapter = MovieListAdapter()
    private lateinit var movieType: MovieType

    override fun getViewBinding() = ActivityListBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addObservers()
        addListeners()

        val type = intent.getStringExtra(EXTRA_MOVIE_TYPE)
        type?.let {
            movieType = MovieType.valueOf(it)
            setupUI(movieType)
        }
    }

    private fun addObservers() {
        viewModel.movieList.observe(this) { response ->
            response.fold(::handleError, ::handleSuccess)
        }
    }

    private fun addListeners() {
        binding.ivBack.setOnClickListener { finish() }
    }

    private fun setupUI(type: MovieType) {
        viewModel.getMovies(type)
        binding.tvTitle.setText(
            when (type) {
                MovieType.UPCOMING -> R.string.core_upcoming_movies
                MovieType.POPULAR -> R.string.core_popular_movies
                MovieType.NOW_PLAYING -> R.string.core_now_playing_movies
                MovieType.TOP_RATED -> R.string.core_top_rated_movies
            }
        )
        setupAdapter()
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter.onClick = { movie ->
            startActivity(intentToMovie(movie.id.toString()))
        }
        binding.rvMovies.layoutManager = layoutManager
        binding.rvMovies.adapter = adapter
        setupEndlessScroll(layoutManager, adapter)
    }

    private fun handleSuccess(list: MutableList<Movie>) {
        adapter.isLoading = false
        if (list.isNotEmpty()) {
            adapter.isFullyLoaded = false
            adapter.updateList(list)
        } else {
            adapter.isFullyLoaded = true
            adapter.notifyDataSetChanged()
        }
    }

    private fun setupEndlessScroll(layoutManager: LinearLayoutManager, adapter: MovieListAdapter) {
        val endlessScrollListener = object : EndlessScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemCount: Int) {
                adapter.isLoading = true
                viewModel.getMovies(movieType, page)
            }
        }
        binding.rvMovies.addOnScrollListener(endlessScrollListener)
    }
}