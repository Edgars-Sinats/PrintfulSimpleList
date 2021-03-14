package com.example.printfulsimplelist.utilities
//
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

@BindingAdapter("publishedAt")
fun itemTime(view: TextView, value: String) {
    val myFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'")
    val myDate: Date = myFormat.parse(value)
    view.text = myDate.time.toString()

//    val formatter = DateTimeFormatter.BASIC_ISO_DATE
//    val text = formatter.format()
}

@BindingAdapter("author")
fun itemAuthor(view: TextView, value: String) {
//    val formatter = NumberFormat.getCurrencyInstance()
//    val text = "${formatter.format(value)} / each"
    view.text = value
}

@BindingAdapter("urlToImage")
fun itemImg(view: ImageView, imageUrl: String) {
//    val formatter = NumberFormat.getCurrencyInstance()
//    val text = "${formatter.format(value)} / each"
    Glide.with(view.context)
        .load(imageUrl)
        .into(view)
}