package com.example.filmesfavoritos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmesfavoritos.data.MovieRepository
import com.example.filmesfavoritos.model.Movie

class MovieViewModel : ViewModel() {

    private val repository = MovieRepository()

    private val _movies = MutableLiveData<List<Movie>>(repository.getAll())
    val movies: LiveData<List<Movie>> = _movies

    fun addMovie(name: String, rating: Float, photoUrl: String) {
        repository.add(name.trim(), rating.coerceIn(0f, 5f), photoUrl.trim())
        refresh()
    }

    fun updateMovie(id: Long, name: String, rating: Float, photoUrl: String) {
        repository.update(id, name.trim(), rating.coerceIn(0f, 5f), photoUrl.trim())
        refresh()
    }

    fun removeMovie(id: Long) {
        repository.remove(id)
        refresh()
    }

    private fun refresh() {
        _movies.value = repository.getAll()
    }
}
