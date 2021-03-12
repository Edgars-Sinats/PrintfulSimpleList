package com.example.printfulsimplelist

import android.app.Application
import android.content.Context
import java.io.File

class FileHelper {
    companion object {
        fun saveTextToFile(app: Application, json: String?) {
            val file = File(app.getExternalFilesDir("news"), "news.json")
            file.writeText(json ?: "", Charsets.UTF_8)
        }

        fun readTextFile(app: Application): String? {
            val file = File(app.getExternalFilesDir("news"), "news.json")
            return if(file.exists()){
                file.readText()
            } else null
        }

        fun getTextFromAssets(context: Context, fileName: String): String {
            return context.assets.open(fileName).use {
                it.bufferedReader().use {
                    it.readText()
                }
            }
        }
    }
}