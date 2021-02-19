package com.example.stickwars.`class`

class Mago(
    nome: String,
    forcaStats: Int = 3,
    defStats: Int = 2,
    expTotal: Int) : Personagem(nome, forcaStats, defStats, expTotal
){
    override fun evoluirForca(): Int {
        forcaStats += 2
        return forcaStats
    }

    override fun evoluirDefesa(): Int {
        defStats += 2
        return forcaStats
    }

    constructor(nome: String)
}