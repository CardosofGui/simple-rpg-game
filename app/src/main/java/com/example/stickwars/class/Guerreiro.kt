package com.example.stickwars.`class`

class Guerreiro(
    nome: String,
    forcaStats: Int = 4,
    defStats: Int = 1,
    expTotal: Int) : Personagem(nome, forcaStats, defStats, expTotal
){
    override fun evoluirForca(): Int {
        forcaStats += 3
        return forcaStats
    }

    override fun evoluirDefesa(): Int {
        defStats += 1
        return forcaStats
    }

    constructor(nome: String)
}