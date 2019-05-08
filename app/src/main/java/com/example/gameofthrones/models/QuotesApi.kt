package com.example.gameofthrones.models

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import java.lang.reflect.InvocationHandler

class QuotesApi {
    companion object {

        private const val URL = "https://got-quotes.herokuapp.com/quotes"

        const val TAG = "QuotesApi"

        fun requestQuotes(responseHandler: (QuoteResponse?) -> Unit,
                          errorHandler: (ANError) -> Unit) {
            request(URL, mapOf("char" to "tyrion"), responseHandler, errorHandler)
        }


        private inline fun <reified T> request(url: String, parameter: Map<String, String>?,
                                               crossinline responseHandler: (T?) -> Unit,
                                               crossinline errorHandler: (ANError) -> Unit) {

            AndroidNetworking.get(url)
                .addQueryParameter(parameter)
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(
                    T::class.java,
                    object : ParsedRequestListener<T> {
                        override fun onResponse(response: T) {
                            response?.apply(responseHandler)
                        }

                        override fun onError(anError: ANError?) {
                            anError?.apply(errorHandler)
                        }
                    }
                )
        }
    }
}