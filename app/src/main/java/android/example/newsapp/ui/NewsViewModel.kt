package android.example.newsapp.ui

import android.example.newsapp.repository.NewsRepository
import androidx.lifecycle.ViewModel

class NewsViewModel(
    val NewsRepository: NewsRepository
): ViewModel() {
}