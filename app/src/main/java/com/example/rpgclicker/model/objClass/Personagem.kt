package com.example.rpgclicker.model.objClass

import android.content.SharedPreferences

abstract class Personagem(
    open var nome:String,
    open var atkStats: Int = 0,
    open var defStats: Int = 0,
    open var expTotal: Double = 0.0
) {

    abstract fun salvarDados(adicionarPreferences : SharedPreferences.Editor)
}