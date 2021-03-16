package com.example.rpgclicker.model.enums

import com.example.rpgclicker.R

enum class Classes(val nomeClasse: String, val atkStats: Int, val defStats: Int, val expTotal: Double, val img: Int) {
    ARK("Arqueiro", 1, 4, 1.0, R.drawable.archer_user),
    WAR("Guerreiro", 4, 1, 1.0, R.drawable.warrior_user),
    MAGE("Mago", 3, 2, 1.0, R.drawable.mage_user)
}