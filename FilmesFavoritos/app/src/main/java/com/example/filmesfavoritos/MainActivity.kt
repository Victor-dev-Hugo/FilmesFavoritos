package com.example.filmesfavoritos

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmesfavoritos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieAdapter

    // Lista inicial de filmes favoritos (pode ficar vazia se preferir)
    private val movieList = mutableListOf(
        Movie("Interestelar", "Christopher Nolan"),
        Movie("Cidade de Deus", "Fernando Meirelles"),
        Movie("Parasita", "Bong Joon-ho"),
        Movie("O Poderoso Chefão", "Francis Ford Coppola")
    )

    // Controla qual LayoutManager está ativo
    private var isGridMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MovieAdapter(movieList)
        binding.recyclerView.adapter = adapter
        setGridLayout()

        binding.btnAdd.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val director = binding.etDirector.text.toString().trim()

            if (title.isEmpty() || director.isEmpty()) {
                Toast.makeText(this, "Preencha título e diretor", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            adapter.addMovie(Movie(title, director))
            binding.etTitle.text.clear()
            binding.etDirector.text.clear()
            binding.recyclerView.scrollToPosition(adapter.itemCount - 1)
        }

        binding.btnToggleLayout.setOnClickListener {
            isGridMode = !isGridMode
            if (isGridMode) setGridLayout() else setLinearLayout()
        }
    }

    private fun setGridLayout() {
        // Exemplo de LayoutManager em formato Grade (2 colunas)
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    private fun setLinearLayout() {
        // Exemplo de LayoutManager em formato Lista (vertical)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
