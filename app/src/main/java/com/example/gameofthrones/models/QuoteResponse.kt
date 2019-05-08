package com.example.gameofthrones.models

class QuoteResponse (
    val quote : String?,
    val character: String?
) {
    constructor() :this("", "")
}
