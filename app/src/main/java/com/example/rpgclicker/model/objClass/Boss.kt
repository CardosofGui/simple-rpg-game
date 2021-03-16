package com.example.rpgclicker.model.objClass

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.example.rpgclicker.model.enums.BdSharedPreferences
import com.example.rpgclicker.model.enums.Chefoes

class Boss(nome: String,
           override var atkStats: Int,
           override var defStats: Int,
           var nivelBoss: Int
           ) : Personagem(nome, atkStats, defStats){

    override fun salvarDados(adicionarPreferences : SharedPreferences.Editor) {
        adicionarPreferences.putString(BdSharedPreferences.BOSS_NOME.key, nome).toString()
        adicionarPreferences.putInt(BdSharedPreferences.BOSS_ATK_STATS.key, atkStats)
        adicionarPreferences.putInt(BdSharedPreferences.BOSS_DEF_STATS.key, defStats)
        adicionarPreferences.putInt(BdSharedPreferences.BOSS_NIVEL.key, nivelBoss)
        adicionarPreferences.apply()
    }

    fun evoluirChefe(mContext: Context){
        if(nivelBoss+1 < Chefoes.values().size){
            // Caso tenha um proximo Chefao ele carrega as novas informações
            this.nome = Chefoes.values().get(nivelBoss+1).nomeChefao
            this.atkStats = Chefoes.values().get(nivelBoss+1).atkStats
            this.defStats = Chefoes.values().get(nivelBoss+1).defStats
            this.nivelBoss = Chefoes.values().get(nivelBoss+1).nivelBoss
        }else{
            // Caso não tenha você completou o jogo
            Toast.makeText(mContext, "Você derrotou todos os bosses, Parabens!", Toast.LENGTH_LONG).show()
        }
    }


    fun lifeBoss() : Double = (atkStats + defStats).toDouble() * 50
}