package com.example.mvinews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewsListViewModel: ViewModel(), NewsListContract {
    private val mutableState = MutableStateFlow(NewsListContract.State())
    override val state: StateFlow<NewsListContract.State> = mutableState.asStateFlow()

    private val effectFlow = MutableSharedFlow<NewsListContract.Effect>()
    override val effect: SharedFlow<NewsListContract.Effect> = effectFlow.asSharedFlow()

    override fun event(event: NewsListContract.Event) = when (event) {
        is NewsListContract.Event.OnGetNewsList -> getData()
        is NewsListContract.Event.OnRefresh -> getData(isRefreshing = true)
        is NewsListContract.Event.onNavigateToDetailScreen -> onNavigateToDetailScreen(news = event.news)
        is NewsListContract.Event.OnBackPressed -> onBackPressed()
        is NewsListContract.Event.ShowToast -> showToast(event.message)
    }

    private fun getData(isRefreshing: Boolean = false) {
        if (isRefreshing) {
            mutableState.update {
                NewsListContract.State(refreshing = true)
            }
        }

        viewModelScope.launch {
            val newsList = getNewsList()
            if (newsList.isNotEmpty()) {
                mutableState.update {
                    NewsListContract.State(news = newsList, refreshing = false)
                }
            }
        }
    }

    private fun getNewsList(): List<News> {
        val newsList = listOf(
            News("Title 1", "Description 1"),
            News("Title 2", "Description 2"),
            News("Title 3", "Description 3")
        )

        return newsList
    }


    private fun onNavigateToDetailScreen(news: News) {

    }

    private fun onBackPressed() {
        viewModelScope.launch {
            effectFlow.emit(NewsListContract.Effect.OnBackPressed)
        }
    }

    private fun showToast(message: String) {
        viewModelScope.launch {
            effectFlow.emit(NewsListContract.Effect.ShowToast(message = message))
        }
    }

}