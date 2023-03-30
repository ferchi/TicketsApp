package com.jfsb.ticketsapp.core.utils

import android.app.Activity
import android.content.Context
import com.jfsb.ticketsapp.R
import java.util.*

object PreferencesManager {

    fun getUserName(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(
            context.getString(R.string.app_name),
            Activity.MODE_PRIVATE
        )
        var username =
            sharedPreferences.getString(context.getString(R.string.resource_user), "")
        if (username != null) {
            if (username.isEmpty()) {
                username = UUID.randomUUID().toString()
                val editor = sharedPreferences.edit()
                editor.putString(context.getString(R.string.resource_user), username)
                editor.apply()
            }
        }
        else{
            return ""
        }
        return username
    }

    fun setUser(context: Context, username: String) {
        val sharedPreferences = context.getSharedPreferences(
            context.getString(R.string.app_name),
            Activity.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.putString(
            context.getString(R.string.resource_user),
            username
        )
        editor.commit()
    }
}