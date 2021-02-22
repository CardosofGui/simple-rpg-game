package com.example.stickwars.classEnums

enum class Chefoes (val nomeChefao: String, val atkStats: Int, val defStats: Int, val derrotado: Boolean, val nivelBoss: Int) {
    Chef1("Jinpachi", 5, 5,  false, 0),
    Chef2("Thanos", 10, 10,  false, 1),
    Chef3("Madara", 15, 15,  false, 2),
    Chef4("Negan", 20, 20,  false, 3)
}