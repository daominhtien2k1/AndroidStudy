package com.example.mvinews

interface NewsListContract: UnidirectionalViewModel<NewsListContract.State, NewsListContract.Effect, NewsListContract.Event> {
    data class State(
        val news: List<News> = listOf(),
        val refreshing: Boolean = false
    )

    sealed class Effect {
        object OnBackPressed : Effect()
        data class ShowToast(val message: String) : Effect()
    }

    sealed class Event {
        data class onNavigateToDetailScreen(val news: News): Event()
        data class OnGetNewsList(val showFavoriteList: Boolean) : Event()
        object OnRefresh: Event()
        object OnBackPressed : Event()
        data class ShowToast(val message: String) : Event()
    }


}