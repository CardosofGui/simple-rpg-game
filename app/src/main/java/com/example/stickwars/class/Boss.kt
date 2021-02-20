package com.example.stickwars.`class`

import android.content.Context
import android.widget.Toast

class Boss(nome: String,
           override var atkStats: Int,
           override var defStats: Int,
           override var expTotal: Double,
           var nivelBoss: Int,
           var derrotado: Boolean ): Personagem(nome, atkStats, defStats, expTotal){

    fun evoluirChefe(mContext: Context){
        if(derrotado && nivelBoss+1 < Chefoes.values().size){
            this.nome = Chefoes.values().get(nivelBoss+1).nomeChefao
            this.atkStats = Chefoes.values().get(nivelBoss+1).atkStats
            this.defStats = Chefoes.values().get(nivelBoss+1).defStats
            this.expTotal = Chefoes.values().get(nivelBoss+1).expTotal
            this.derrotado = Chefoes.values().get(nivelBoss+1).derrotado
            this.nivelBoss = Chefoes.values().get(nivelBoss+1).nivelBoss
        }else{
            Toast.makeText(mContext, "Você derrotou todos os bosses, Parabens!", Toast.LENGTH_LONG).show()
        }
    }

}