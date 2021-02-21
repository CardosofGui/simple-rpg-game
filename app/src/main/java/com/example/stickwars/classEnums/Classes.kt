package com.example.stickwars.classEnums

import com.example.stickwars.R

enum class Classes(val nomeClasse: String, val atkStats: Int, val defStats: Int, val expTotal: Double, val img: Int) {
    Ark("Arqueiro", 1, 4, 1.0, R.drawable.archer),
    War("Guerreiro", 4, 1, 1.0, R.drawable.warrior),
    Mage("Mago", 3, 2, 1.0, R.drawable.mage)
}