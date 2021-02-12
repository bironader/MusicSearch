package com.bironader.musicsearch.framework.datasource.remote.impl

import android.annotation.SuppressLint
import android.content.Context
import com.bironader.musicsearch.Constant.PREF_NAME
import com.bironader.musicsearch.PreferencesKeys.PREF_TOKEN
import com.bironader.musicsearch.framework.datasource.remote.abstraction.PrefDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PrefDataImpl @Inject constructor(
    @ApplicationContext context: Context
) : PrefDataSource {

    private val sharedPref = context.getSharedPreferences(
        PREF_NAME, Context.MODE_PRIVATE
    )

    @SuppressLint("CommitPrefEdits")
    override fun saveToken(token: String) {
        with(sharedPref.edit())
        {
            putString(PREF_TOKEN, token)
            apply()
        }
    }

    override fun getToken() = sharedPref.getString(PREF_TOKEN, "")!!


}