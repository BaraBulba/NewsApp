package android.example.newsapp.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class Utils {

    // Для форматирования времени
//    fun DateToTimeFormat (oldstringDate: String?): String? {
//        val p = PrettyTime(Locale.ENGLISH)
//        var isTime: String? = null
//        try {
//            val sdf = SimpleDateFormat(
//                "yyyy-MM-dd'T'HH:mm:ss'Z'",
//                Locale.ENGLISH
//            )
//            val date = sdf.parse(oldstringDate)
//            isTime = p.format(date)
//        } catch (e: ParseException) {
//            e.printStackTrace()
//        }
//        return isTime
//    }

    fun  DateFormat (oldStringDate: String?): String? {
        val newDate: String?
        val dateFormat = SimpleDateFormat("E, d MMM yyyy", Locale.ENGLISH)
        newDate = try {
            val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(oldStringDate)
            dateFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            oldStringDate
        }
        return newDate
    }
}