package com.dreamisi.moviebase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dreamisi.moviebase.data.models.Movie

class MoviesListAdapter(
    context: Context,
    private val onClick: (movieId: Int) -> Unit
) :
    RecyclerView.Adapter<MoviesListAdapter.MoviesListViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var movies = listOf<Movie>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListViewHolder {
        return MoviesListViewHolder(
            itemView = inflater.inflate(R.layout.view_holder_movie, parent, false),
            onClick = onClick
        )
    }

    override fun onBindViewHolder(holder: MoviesListViewHolder, position: Int) {
        holder.onBind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    fun bindMovies(newMovies: List<Movie>) {
        movies = newMovies
    }

    class MoviesListViewHolder(
        itemView: View,
        private val onClick: (movieId: Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val image: ImageView = itemView.findViewById(R.id.movie_image)
        private val name: TextView = itemView.findViewById(R.id.film_name_movies_list)
        private val duration: TextView = itemView.findViewById(R.id.duration)
        private val favorite: ImageView = itemView.findViewById(R.id.favorite)
        private val genre: TextView = itemView.findViewById(R.id.film_genre)
        private val reviews: TextView = itemView.findViewById(R.id.reviews_movies_list)
        private val pg: ImageView = itemView.findViewById(R.id.pg)
        private val rating_1_st: ImageView = itemView.findViewById(R.id.first_star_movies_list)
        private val rating_2_st: ImageView = itemView.findViewById(R.id.second_star_movies_list)
        private val rating_3_st: ImageView = itemView.findViewById(R.id.third_star_movies_list)
        private val rating_4_st: ImageView = itemView.findViewById(R.id.fourth_star_movies_list)
        private val rating_5_st: ImageView = itemView.findViewById(R.id.fifth_star_movies_list)

        fun onBind(movie: Movie) {
            itemView.setOnClickListener { onClick.invoke(movie.id) }
            image.setImageResource(movie.image)
            name.text = movie.name
            duration.text = itemView.context.getString(R.string.MIN, movie.duration)
            when (movie.favorite) {
                false -> favorite.setImageResource(R.drawable.like_not_active)
                true -> favorite.setImageResource(R.drawable.like_active)
            }
            genre.text = movie.genre
            reviews.text = itemView.context.getString(R.string.reviews, movie.reviews)
            pg.setImageResource(movie.pg)
            listOf(
                rating_1_st,
                rating_2_st,
                rating_3_st,
                rating_4_st,
                rating_5_st
            ).forEachIndexed { index, imageView ->
                val start_index = index + 1
                val start_res = if (start_index <= movie.rating) {
                    R.drawable.red_star_movies_list
                } else {
                    R.drawable.gray_star_movies_list
                }
                imageView.setImageResource(start_res)
            }


        }

    }

}