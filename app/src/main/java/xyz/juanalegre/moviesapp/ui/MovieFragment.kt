package xyz.juanalegre.moviesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import xyz.juanalegre.moviesapp.R
import xyz.juanalegre.moviesapp.core.Resource
import xyz.juanalegre.moviesapp.data.local.AppDatabase
import xyz.juanalegre.moviesapp.data.local.LocalMovieDataSource
import xyz.juanalegre.moviesapp.data.model.Movie
import xyz.juanalegre.moviesapp.data.remote.MovieDataSource
import xyz.juanalegre.moviesapp.databinding.FragmentMovieBinding
import xyz.juanalegre.moviesapp.presentation.MovieViewModel
import xyz.juanalegre.moviesapp.presentation.MovieViewModelFactory
import xyz.juanalegre.moviesapp.repository.MovieRepositoryImpl
import xyz.juanalegre.moviesapp.repository.RetrofitClient
import xyz.juanalegre.moviesapp.ui.adapters.MovieAdapter
import xyz.juanalegre.moviesapp.ui.adapters.concat.PopularConcatAdapter
import xyz.juanalegre.moviesapp.ui.adapters.concat.TopRatedConcatAdapter
import xyz.juanalegre.moviesapp.ui.adapters.concat.UpcomingConcatAdapter

class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {

    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel> { MovieViewModelFactory(MovieRepositoryImpl(
        MovieDataSource(RetrofitClient.webService),
        LocalMovieDataSource(AppDatabase.getDatabase(requireContext()).movieDao())
    )) }
    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)
        concatAdapter = ConcatAdapter()

        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(0, UpcomingConcatAdapter(MovieAdapter(result.data.first.results, this@MovieFragment)))
                        addAdapter(1, TopRatedConcatAdapter(MovieAdapter(result.data.second.results, this@MovieFragment)))
                        addAdapter(2, PopularConcatAdapter(MovieAdapter(result.data.third.results, this@MovieFragment)))
                    }
                    binding.rvMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {}
            }
        })
    }

    override fun onMovieClick(movie: Movie) {
        val action =MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.imagePoster,
            movie.imageBackdrop,
            movie.voteAverage.toFloat(),
            movie.voteCount,
            movie.overview,
            movie.title,
            movie.language,
            movie.realeaseDate
        )
        findNavController().navigate(action)
    }

}