package xyz.juanalegre.moviesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import xyz.juanalegre.moviesapp.R
import xyz.juanalegre.moviesapp.databinding.FragmentMovieDetailBinding


class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var binding: FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailBinding.bind(view)
        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500/${args.backGroundImageUrl}").centerCrop().into(binding.imgBackground)
        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500/${args.posterImageUrl}").centerCrop().into(binding.imgMovie)
        binding.txtDescription.text = args.overview
        binding.tvMovieTitle.text = args.title
        binding.txtLanguage.text = "Lenguaje: ${args.language}"
        binding.txtStars.text = "${args.voteAverage} (${args.voteCount} Reviews)"
        binding.txtRelease.text = "Released ${args.releaseDate}"
    }

}
