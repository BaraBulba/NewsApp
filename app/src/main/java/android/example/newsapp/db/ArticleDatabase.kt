package android.example.newsapp.db

import android.content.Context
import android.example.newsapp.models.Article
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


// Дата классы для Room всегда должны быть абстрактными
// version обновляется каждый раз, когда вносятся изминения, чтобы Рум понимал, что они были
@Database(
    entities = [Article::class],
    version = 1
)
// перечисляем здесь наши классы с конвертацией
@TypeConverters(Converters::class)
abstract class ArticleDatabase: RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object{
        //аннотация для того,чтобы другие потоки могли сразу увидеть, что поток изменил экземпляр
        @Volatile
        private var instance: ArticleDatabase? = null // это синглтон
        // для проверки на то, что у нас всего 1 экземляр базы данных в данный момент
        private val LOCK = Any()

        // всё, что происходит внутри этого блока, другие потоки не смогут получить доступ в одно и то же время
        // мы убеждаемся, что нигде больше ничего не присваевается этому instance
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also{ instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()
    }
}