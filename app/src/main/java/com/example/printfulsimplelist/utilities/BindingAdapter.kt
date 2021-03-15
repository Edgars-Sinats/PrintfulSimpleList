package com.example.printfulsimplelist.utilities
//
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.printfulsimplelist.R
import java.security.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.TimeMark
import kotlin.time.TimeSource

@BindingAdapter("publishedAt")
fun itemTime(view: TextView, value: String?) {
    val myFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.ROOT)
    myFormat.timeZone = TimeZone.getTimeZone("GMT")
    val myDate : Date
    if (value != null) {
        myDate = myFormat.parse(value)
    }else{
        val date = "2000-00-01T01:00:05Z"
        myDate = myFormat.parse(date)
    }
    view.text = myDate.time.toString()

//    val formatter = DateTimeFormatter.BASIC_ISO_DATE
//    val text = formatter.format()
}

@BindingAdapter("author")
fun itemAuthor(view: TextView, value: String?) {
//    val formatter = NumberFormat.getCurrencyInstance()
//    val text = "${formatter.format(value)} / each"

    view.text = value ?: "Unknown"
}

@BindingAdapter("urlToImage")
fun itemImg(view: ImageView, imageUrl: String?) {
//    val formatter = NumberFormat.getCurrencyInstance()
//    val text = "${formatter.format(value)} / each"

    Glide.with(view.context)
            .load(imageUrl ?: R.drawable.uzvaretajs_lideris_karalis_53012935)
            .into(view)
}