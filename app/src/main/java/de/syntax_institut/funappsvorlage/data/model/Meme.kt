package de.syntax_institut.funappsvorlage.data.model

data class Meme(
    val id: Int,
    val name: String,
    val url: String,
    var savedTitle: String = ""
)
