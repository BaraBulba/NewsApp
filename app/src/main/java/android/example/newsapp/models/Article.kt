package android.example.newsapp.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity(
    tableName = "articles"
)
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
): Serializable {


    // Выскакивала такая ошибка, пришлось переопределять хешкод и проверять на ноль или пустые ответы от АПИ :
    // java.lang.NullPointerException: 'Attempt to invoke virtual method 'int java.lang.String.hashCode()'
    override fun hashCode(): Int {
        var result = id.hashCode()
        if (url.isNullOrEmpty()){
            result = 31 * result + url.hashCode()
        }
        return result
    }
}