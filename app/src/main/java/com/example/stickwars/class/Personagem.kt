package com.example.stickwars.`class`

abstract class Personagem(
    open var nome:String,
    open var atkStats: Int = 0,
    open var defStats: Int = 0,
    open var expTotal: Double = 0.0
){

    abstract fun evoluirForca()
    abstract fun evoluirDefesa()

}