package com.github.veselinazatchepina.smack.services

import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.github.veselinazatchepina.smack.controller.App
import com.github.veselinazatchepina.smack.model.Channel
import com.github.veselinazatchepina.smack.model.Message
import com.github.veselinazatchepina.smack.utils.URL_GET_CHANNELS
import org.json.JSONException


object MessageService {

    val channels = ArrayList<Channel>()
    val messages = ArrayList<Message>()

    fun getChannels(complete: (Boolean) -> Unit) {

        val channelsRequest = object : JsonArrayRequest(Method.GET, URL_GET_CHANNELS, null, Response.Listener {
            try {
                for (x in 0 until it.length()) {
                    val channel = it.getJSONObject(x)
                    val name = channel.getString("name")
                    val chanDesc = channel.getString("description")
                    val channelId = channel.getString("_id")

                    val newChannel = Channel(name, chanDesc, channelId)
                    this.channels.add(newChannel)
                }
                complete(true)
            } catch (e: JSONException) {
                Log.d("JSON", "EXC: " + e.localizedMessage)
                complete(false)
            }

        }, Response.ErrorListener {
            Log.d("ERROR", "Could not retrieve channels")
            complete(false)
        }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Authorization", "Bearer ${ App.sharedPrefs.authToken}")
                return headers
            }
        }
        App.sharedPrefs.requestQueue.add(channelsRequest)
    }



}