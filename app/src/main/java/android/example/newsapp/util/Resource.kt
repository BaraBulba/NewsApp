package android.example.newsapp.util

import android.example.newsapp.db.ArticleDatabase

// класс для обработки запросов
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data = data, message = message)
    class Loading<T>: Resource<T>()
}