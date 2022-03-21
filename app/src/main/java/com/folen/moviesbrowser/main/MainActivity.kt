package com.folen.moviesbrowser.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.folen.moviesbrowser.R
import com.folen.moviesbrowser.model.Movie
import com.folen.moviesbrowser.restapi.MovieRepository
import com.folen.moviesbrowser.restapi.RetrofitClient

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var myMovies: List<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getMovies()
    }

    private fun getMovies() {
        val retrofitClient = RetrofitClient.getInstance()
        val movieRepository = MovieRepository(retrofitClient)
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(movieRepository)
        ).get(MainViewModel::class.java)
        viewModel.movieList.observe(this) {
            myMovies = it.movie
            Toast.makeText(this, myMovies.toString(), Toast.LENGTH_LONG).show()
        }
        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
        viewModel.getAllMovies()
    }
}