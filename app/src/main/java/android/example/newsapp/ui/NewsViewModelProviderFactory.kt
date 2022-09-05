package android.example.newsapp.ui

import android.app.Application
import android.example.newsapp.repository.NewsRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NewsViewModelProviderFactory(
    val app: Application,
    val newsRepository: NewsRepository
):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(app, newsRepository) as T
    }
}