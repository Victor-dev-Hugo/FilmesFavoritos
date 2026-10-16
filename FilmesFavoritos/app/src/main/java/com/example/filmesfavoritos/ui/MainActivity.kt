package com.example.filmesfavoritos.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmesfavoritos.R
import com.example.filmesfavoritos.databinding.ActivityMainBinding
import com.example.filmesfavoritos.databinding.DialogEditMovieBinding
import com.example.filmesfavoritos.model.Movie
import com.example.filmesfavoritos.viewmodel.MovieViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        setupRecyclerView()
        observeViewModel()

        binding.fabAdd.setOnClickListener { showMovieDialog(null) }
    }

    private fun setupRecyclerView() {
        adapter = MovieAdapter(
            onEdit = { movie -> showMovieDialog(movie) },
            onDelete = { movie -> confirmDelete(movie) }
        )
        binding.recyclerMovies.layoutManager = LinearLayoutManager(this)
        binding.recyclerMovies.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.movies.observe(this) { movies ->
            adapter.submitList(movies)
            binding.tvEmpty.visibility = if (movies.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    /** Abre o diálogo para adicionar (movie == null) ou editar um filme. */
    private fun showMovieDialog(movie: Movie?) {
        val dialogBinding = DialogEditMovieBinding.inflate(layoutInflater)

        if (movie != null) {
            dialogBinding.inputName.setText(movie.name)
            dialogBinding.inputPhoto.setText(movie.photoUrl)
            dialogBinding.ratingBarEdit.rating = movie.rating
        }

        val dialog: AlertDialog = MaterialAlertDialogBuilder(this)
            .setTitle(if (movie == null) R.string.add_movie else R.string.edit_movie)
            .setView(dialogBinding.root)
            .setNegativeButton(R.string.cancel, null)
            .setPositiveButton(R.string.save, null) // clique tratado abaixo para validar sem fechar
            .create()

        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                val name = dialogBinding.inputName.text?.toString()?.trim().orEmpty()
                val photoUrl = dialogBinding.inputPhoto.text?.toString()?.trim().orEmpty()
                val rating = dialogBinding.ratingBarEdit.rating

                if (name.isEmpty()) {
                    dialogBinding.layoutName.error = getString(R.string.error_name_required)
                    return@setOnClickListener
                }

                if (movie == null) {
                    viewModel.addMovie(name, rating, photoUrl)
                } else {
                    viewModel.updateMovie(movie.id, name, rating, photoUrl)
                }
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    private fun confirmDelete(movie: Movie) {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.delete_title)
            .setMessage(getString(R.string.delete_message, movie.name))
            .setNegativeButton(R.string.cancel, null)
            .setPositiveButton(R.string.delete) { _, _ -> viewModel.removeMovie(movie.id) }
            .show()
    }
}
