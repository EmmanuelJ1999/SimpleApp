package com.example.simpleapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.simpleapp.R
import com.example.simpleapp.database.AppDataBase
import com.example.simpleapp.database.entities.MovieEntity
import com.example.simpleapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val DATABASE_NAME = "movies_database"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddMovie.setOnClickListener{addMovie()}
    }

    private fun addMovie(){
        val db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            DATABASE_NAME
        ).build()
        val movieDao = db.movieDao()

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
        val db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            DATABASE_NAME
        ).build()
        val movieDao = db.movieDao()

        lifecycleScope.launch{
            val movies = movieDao.getAllMovies()
            runOnUiThread{
                Toast.makeText(binding.layout.context, "${movies.last().name} Registered movie", Toast.LENGTH_SHORT).show()
            }
        }

    }
}