package android.example.newsapp.repository

import android.example.newsapp.api.RetrofitInstance
import android.example.newsapp.db.ArticleDatabase

// Основная задача, брать данные с нашей базы данных и удаленных ресурсов (наше апи)
class NewsRepository(
    val db: ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode = countryCode, pageNumber = pageNumber)
}