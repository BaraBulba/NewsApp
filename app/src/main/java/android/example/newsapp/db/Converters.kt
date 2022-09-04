package android.example.newsapp.db

import android.example.newsapp.models.Source
import androidx.room.TypeConverter


class Converters {

    //Рум работает только с примитивными типами, поэтому, нужно конвертировать сорс в стрингу
    @TypeConverter
    fun fromSource(source: Source): String{
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name,name)
    }
}