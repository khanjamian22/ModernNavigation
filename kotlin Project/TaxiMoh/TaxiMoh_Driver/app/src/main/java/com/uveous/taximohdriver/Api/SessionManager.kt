package com.uveous.taximohdriver.Api

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.uveous.taximohdriver.R


class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_ID = "user_id"
        const val USER_NAME= "user_name"
        const val USER_PHONENO= "user_number"
        const val USER_vehicle_type= "user_vehicle_type"
        const val USER_Availabilty= "user_availabilty"
        const val USER_Availabilty1= "user_availabilty1"
        const val REQUEST_ID= "user_request"
        const val REQUEST_Accept= "user_accept"
    }

    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun saverequestAccept(token: String) {
        val editor = prefs.edit()
        editor.putString(REQUEST_Accept, token)
        editor.apply()
    }

    fun saveAvailabity(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_Availabilty, token)
        editor.apply()
    }
    fun saveAvailabity1(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_Availabilty1, token)
        editor.apply()
    }

    fun savename(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_NAME, token)
        editor.apply()
    }

    fun savenumber(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_PHONENO, token)
        editor.apply()
    }

    fun saveuserid(userid: Int) {
        val editor = prefs.edit()
        editor.putInt(USER_ID, userid)
        editor.apply()
    }

    fun saverequestid(userid: Int) {
        val editor = prefs.edit()
        editor.putInt(REQUEST_ID, userid)
        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    fun fetchuserrequest(): String? {
        return prefs.getString(REQUEST_Accept, null)
    }
     fun fetchAvailabilty(): String? {
        return prefs.getString(USER_Availabilty, "")
    }

    fun fetchAvailabilty1(): String? {
        return prefs.getString(USER_Availabilty1, "")
    }

    fun fetchusername(): String? {
        return prefs.getString(USER_NAME, null)
    }

    fun fetchuserno(): String? {
        return prefs.getString(USER_PHONENO, null)
    }

    fun fetchuserid(): Int? {
        return prefs.getInt(USER_ID, 0)
    }
     fun fetchrequestid(): Int? {
            return prefs.getInt(REQUEST_ID, 0)
        }

    fun logoutUser() {
        prefs?.edit()?.remove(USER_TOKEN)?.commit()
        prefs?.edit()?.remove(USER_PHONENO)?.commit()
        prefs?.edit()?.remove(USER_ID)?.commit()
        prefs?.edit()?.remove(USER_NAME)?.commit()
        prefs?.edit()?.remove(USER_Availabilty)?.commit()
        prefs?.edit()?.remove(USER_Availabilty1)?.commit()
        prefs?.edit()?.remove(REQUEST_ID)?.commit()
        prefs?.edit()?.remove(REQUEST_Accept)?.commit()

    }
  fun logoutUser1() {
        prefs?.edit()?.remove(USER_Availabilty)?.commit()
        prefs?.edit()?.remove(USER_Availabilty1)?.commit()
        prefs?.edit()?.remove(REQUEST_ID)?.commit()
        prefs?.edit()?.remove(REQUEST_Accept)?.commit()

    }

}