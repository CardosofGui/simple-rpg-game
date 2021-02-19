package com.example.stickwars.`class`

class Arqueiro(
    nome: String,
    forcaStats: Int = 1,
    defStats: Int = 4,
    expTotal: Int) : Personagem(nome, forcaStats, defStats, expTotal
){
    override fun evoluirForca(): Int {
        forcaStats += 1
        return forcaStats
    }

    override fun evoluirDefesa(): Int {
        defStats += 3
        return forcaStats
    }

    constructor(nome: String)
}