package com.example.stickwars.`class`

enum class Classes(val nomeClasse: String, val atkStats: Int, val defStats: Int, val expTotal: Double) {
    Ark("Arqueiro", 1, 4, 1.0),
    War("Guerreiro", 4, 1, 1.0),
    Mage("Mago", 3, 2, 1.0)
}