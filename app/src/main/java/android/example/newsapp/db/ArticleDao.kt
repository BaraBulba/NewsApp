package android.example.newsapp.db

import android.example.newsapp.models.Article
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArticleDao {

    // аннотация для того, чтобы понять, что делать с объектом, если он уже находится в базе данных
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    //тут обычная функция, т.к. она возвращает live data объект, который не работает с suspend
    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    //Удаление статьи
    @Delete
    suspend fun deleteArticle(article: Article)
}