package com.example.banquemisrapp


import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.Locale

object LocaleManager {
    var isArabic by mutableStateOf(false)
    val currentLocale get() = if (isArabic) Locale("ar") else Locale("en")
    fun init(context: Context) {
        val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        isArabic = prefs.getBoolean("is_arabic", false)
    }

    fun toggle(context: Context) {
        isArabic = !isArabic
        context.getSharedPreferences("settings", Context.MODE_PRIVATE)
            .edit()
            .putBoolean("is_arabic", isArabic)
            .apply()
    }
}