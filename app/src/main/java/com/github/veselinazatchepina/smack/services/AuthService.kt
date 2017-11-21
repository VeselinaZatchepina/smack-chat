package com.github.veselinazatchepina.smack.services

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.github.veselinazatchepina.smack.utils.URL_LOGIN
import com.github.veselinazatchepina.smack.utils.URL_REGISTER
import org.json.JSONException
import org.json.JSONObject


object AuthService {

    var isLoggedIn = false
    var userEmail = ""
    var authToken = ""

     fun registerUser(context: Context, email: String, password: String, complete: (Boolean) -> Unit) {
         val jsonBody = JSONObject()
         jsonBody.put("email", email)
         jsonBody.put("password", password)
         val requestBody = jsonBody.toString()

         val registerRequest = object : StringRequest(Method.POST, URL_REGISTER, Response.Listener { response ->
            complete(true)
         }, Response.ErrorListener { error ->
             Log.d("ERROR", "Could not register user: $error")
             complete(false)
         }) {
             override fun getBodyContentType(): String {
                 return "application/json; charset=utf-8"
             }

             override fun getBody(): ByteArray {
                 return requestBody.toByteArray()
             }
         }

         Volley.newRequestQueue(context).add(registerRequest)
     }

    fun loginUser(context: Context, email: String, password: String, complete: (Boolean) -> Unit) {
        val jsonBody = JSONObject()
        jsonBody.put("email", email)
        jsonBody.put("password", password)
        val requestBody = jsonBody.toString()

        val loginRequest = object : JsonObjectRequest(Method.POST, URL_LOGIN, null, Response.Listener {
            try {
                userEmail = it.getString("user")
                authToken = it.getString("token")
                isLoggedIn = true
                complete(true)
            } catch (error: JSONException) {
                Log.d("JSON", "EXC:" + error.localizedMessage)
                complete(false)
            }

        }, Response.ErrorListener {
            Log.d("ERROR", "Could not register user: $it")
            complete(false)

        }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }
        }

        Volley.newRequestQueue(context).add(loginRequest)
    }
}