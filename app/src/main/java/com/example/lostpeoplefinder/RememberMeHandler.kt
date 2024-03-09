package com.example.lostpeoplefinder

import android.content.Context
import android.content.SharedPreferences


class RememberMeHandler {

    var usersSession: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var context: Context? = null

    //Session names

    //Session names
    val session_rememberMe = "rememberMeSession"
    private val IS_REMEMBERME = "IsRememberMe"
    val KEY_SESSIONEMAIL = "email"
    val KEY_SESSIONPASSWORD = "password"
    val IS_ADMIN = "isAdmin"


    fun RememberMeHandler(context: Context) {
        this.context = context
        usersSession = context.getSharedPreferences(session_rememberMe, Context.MODE_PRIVATE)
        editor = usersSession!!.edit()

    }

    fun createRememberMeSession(email: String?, password: String?, isadmin: Boolean) {
        editor!!.putBoolean(IS_REMEMBERME, true)
        editor!!.putString(KEY_SESSIONEMAIL, email)
        editor!!.putString(KEY_SESSIONPASSWORD, password)
        editor!!.putBoolean(IS_ADMIN, isadmin)
        editor!!.commit()
    }

    fun getSessionDetails(): HashMap<String, String?>? {
        val userinfo = HashMap<String, String?>()
        userinfo[KEY_SESSIONEMAIL] = usersSession?.getString(KEY_SESSIONEMAIL, null)
        userinfo[KEY_SESSIONPASSWORD] = usersSession?.getString(KEY_SESSIONPASSWORD, null)
        return userinfo
    }

    fun checkRememberMeState(): Boolean {
        return usersSession?.getBoolean(IS_REMEMBERME, false) ?: false
    }

    fun checkIsAdminState(): Boolean {
        return usersSession?.getBoolean(IS_ADMIN, false) ?: false
    }

}