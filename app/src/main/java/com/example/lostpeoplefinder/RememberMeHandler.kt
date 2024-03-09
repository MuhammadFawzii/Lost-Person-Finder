package com.example.lostpeoplefinder

import android.content.Context
import android.content.SharedPreferences


class RememberMeHandler private constructor(context: Context) {

    companion object {
        private var usersSession: SharedPreferences? = null
        private var editor: SharedPreferences.Editor? = null
        private var context: Context? = null

        //Session names
        const val session_rememberMe = "rememberMeSession"
        private const val IS_REMEMBERME = "IsRememberMe"
        const val KEY_SESSIONEMAIL = "email"
        const val KEY_SESSIONPASSWORD = "password"
        const val IS_ADMIN = "isAdmin"


        fun getInstance(context: Context): RememberMeHandler {
            if (usersSession == null) {
                synchronized(RememberMeHandler::class.java) {
                    if (usersSession == null) {
                        context.applicationContext?.let {
                            usersSession = it.getSharedPreferences(session_rememberMe, Context.MODE_PRIVATE)
                            editor = usersSession!!.edit()
                            this.context = it
                        }
                    }
                }
            }
            return RememberMeHandler(context)
        }
    }

    fun clearRememberMeSession() {
        editor?.clear()?.apply()
    }
    fun createRememberMeSession(email: String?, password: String?, isAdmin: Boolean) {
        editor?.putBoolean(IS_REMEMBERME, true)
        editor?.putString(KEY_SESSIONEMAIL, email)
        editor?.putString(KEY_SESSIONPASSWORD, password)
        editor?.putBoolean(IS_ADMIN, isAdmin)
        editor?.commit()
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
