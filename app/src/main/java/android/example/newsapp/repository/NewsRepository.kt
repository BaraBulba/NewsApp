package android.example.newsapp.repository

import android.example.newsapp.api.RetrofitInstance
import android.example.newsapp.db.ArticleDatabase
import android.example.newsapp.models.Article

// Основная задача, брать данные с нашей базы данных и удаленных ресурсов (наше апи)
class NewsRepository(
    val db: ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode = countryCode, pageNumber = pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article = article)

    //не саспенд функция, т.к. имеет дело с лайв датой
    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article = article)
}