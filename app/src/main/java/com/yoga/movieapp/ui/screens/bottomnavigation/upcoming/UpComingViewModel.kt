package com.yoga.movieapp.ui.screens.bottomnavigation.upcoming

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yoga.movieapp.data.model.GenreId
import com.yoga.movieapp.data.model.moviedetail.Genre
import com.yoga.movieapp.data.repository.MovieRepository
import com.yoga.movieapp.utils.AppConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class UpComingViewModel @Inject constructor(val repo: MovieRepository) : ViewModel() {
    var selectedGenre: MutableState<Genre> =
        mutableStateOf(Genre(null, AppConstant.DEFAULT_GENRE_ITEM))
    val filterData = MutableStateFlow<GenreId?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val upcomingMovies = filterData.flatMapLatest {
        repo.upcomingPagingDataSource(it?.genreId)
    }.cachedIn(viewModelScope)
}