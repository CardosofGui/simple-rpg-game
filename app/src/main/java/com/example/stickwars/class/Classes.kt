package com.example.stickwars.`class`

import com.example.stickwars.R

enum class Classes(val nomeClasse: String, val atkStats: Int, val defStats: Int, val expTotal: Double, val img: Int) {
    Ark("Arqueiro", 1, 4, 1.0, R.drawable.arqueiro),
    War("Guerreiro", 4, 1, 1.0, R.drawable.guerreiro),
    Mage("Mago", 3, 2, 1.0, R.drawable.mago)
}