package com.example.firstproject.repositories

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.firstproject.App
import com.example.firstproject.models.Profile

object PreferencesRepository {
    private const val FIRST_NAME = "FIRST_NAME"
    private const val LAST_NAME = "LAST_NAME"
    private const val ABOUT = "ABOUT"
    private const val REPOS = "REPOS"
    private const val RATING = "RATING"
    private const val RESPECT = "RESPECT"
    private val prefs:SharedPreferences by lazy {
        val ctx = App.applicationContext()
        PreferenceManager.getDefaultSharedPreferences(ctx)
    }
    fun getProfile(): Profile = Profile(
        prefs.getString(FIRST_NAME, "")!!,
        prefs.getString(LAST_NAME, "")!!,
        prefs.getString(ABOUT, "")!!,
        prefs.getString(REPOS, "")!!,
        prefs.getInt(RATING, 0),
        prefs.getInt(RESPECT, 0)
    )

    fun saveProfile(profile: Profile) {
        with(profile){
            putValue(FIRST_NAME to firstName)
            putValue(LAST_NAME to lastName)
            putValue(ABOUT to about)
            putValue(REPOS to repository)
            putValue(RATING to rating)
            putValue(RESPECT to respect)
        }
    }
    private fun putValue(pair:Pair<String,Any>) = with(prefs.edit()){
        val key = pair.first
        val value = pair.second
        when(value){
            is String -> putString(key,value)
            is Int -> putInt(key,value)
            is Boolean -> putBoolean(key,value)
            is Long -> putLong(key,value)
            is Float -> putFloat(key,value)
            else -> error("Only primitives types can be stored in Shared Preferences")
        }
        apply()
    }
}