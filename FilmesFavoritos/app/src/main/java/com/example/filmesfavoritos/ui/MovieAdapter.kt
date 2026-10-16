package com.example.filmesfavoritos.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.filmesfavoritos.R
import com.example.filmesfavoritos.databinding.ItemMovieBinding
import com.example.filmesfavoritos.model.Movie

class MovieAdapter(
    private val onEdit: (Movie) -> Unit,
    private val onDelete: (Movie) -> Unit
) : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(DiffCallback) {

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.tvName.text = movie.name
            binding.ratingBar.rating = movie.rating
            binding.tvRating.text =
                binding.root.context.getString(R.string.rating_format, movie.rating)

            if (movie.photoUrl.isBlank()) {
                binding.imgPoster.setImageResource(R.drawable.ic_movie_placeholder)
            } else {
                binding.imgPoster.load(movie.photoUrl) {
                    crossfade(true)
                    placeholder(R.drawable.ic_movie_placeholder)
                    error(R.drawable.ic_movie_placeholder)
                }
            }

            binding.btnEdit.setOnClickListener { onEdit(movie) }
            binding.btnDelete.setOnClickListener { onDelete(movie) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
    }
}
