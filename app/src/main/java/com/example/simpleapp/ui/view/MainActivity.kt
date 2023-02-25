package com.example.simpleapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.simpleapp.database.AppDataBase
import com.example.simpleapp.database.dao.MovieDao
import com.example.simpleapp.database.entities.MovieEntity
import com.example.simpleapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var movieDao: MovieDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddMovie.setOnClickListener{addMovie()}
    }

    private fun addMovie(){
        val name = binding.etMovieName.text.toString()
        val rating =  binding.etMovieRating.text.toString().toInt()
        val movie = MovieEntity(
            name = name,
            rating = rating
        )
        lifecycleScope.launch {
            movieDao.insertMovie(movie)
        }
        showLastMovie()
    }

    private fun showLastMovie(){
        lifecycleScope.launch{
            val movies = movieDao.getAllMovies()
            runOnUiThread{
                Toast.makeText(applicationContext, "${movies.last().name} Registered movie", Toast.LENGTH_SHORT).show()
            }
        }
    }
}