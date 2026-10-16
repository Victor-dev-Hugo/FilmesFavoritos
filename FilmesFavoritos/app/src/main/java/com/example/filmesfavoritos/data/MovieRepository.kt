package com.example.filmesfavoritos.data

import com.example.filmesfavoritos.model.Movie

/**
 * Fonte de dados dos filmes favoritos (em memória).
 * Para persistir de verdade, basta trocar a implementação por Room ou DataStore
 * mantendo a mesma interface pública.
 */
class MovieRepository {

    private val movies = mutableListOf(
        Movie(1, "Interestelar", 4.5f, "https://picsum.photos/seed/interestelar/300/450"),
        Movie(2, "O Senhor dos Anéis", 5f, "https://picsum.photos/seed/senhoraneis/300/450"),
        Movie(3, "Cidade de Deus", 4f, "https://picsum.photos/seed/cidadedeus/300/450")
    )
    private var nextId = 4L

    fun getAll(): List<Movie> = movies.toList()

    fun add(name: String, rating: Float, photoUrl: String) {
        movies.add(Movie(nextId++, name, rating, photoUrl))
    }

    fun update(id: Long, name: String, rating: Float, photoUrl: String) {
        val index = movies.indexOfFirst { it.id == id }
        if (index >= 0) {
            movies[index] = movies[index].copy(name = name, rating = rating, photoUrl = photoUrl)
        }
    }

    fun remove(id: Long) {
        movies.removeAll { it.id == id }
    }
}
