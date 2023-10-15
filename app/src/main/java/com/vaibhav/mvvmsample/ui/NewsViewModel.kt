package com.vaibhav.mvvmsample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaibhav.mvvmsample.AppConstants.COUNTRY
import com.vaibhav.mvvmsample.data.NewsRepository
import com.vaibhav.mvvmsample.data.model.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            newsRepository.getNews(COUNTRY).catch { e -> UiState.Error(e.message.toString()) }
                .collect { _uiState.value = UiState.Success(it) }
        }
    }
}