package com.example.stickwars.`class`

abstract class Personagem(
    val nome:String,
    var forcaStats:Int,
    var defStats:Int,
    var expTotal:Int
){
    abstract fun evoluirForca():Int

    abstract fun evoluirDefesa():Int

    constructor(nome: String)
}