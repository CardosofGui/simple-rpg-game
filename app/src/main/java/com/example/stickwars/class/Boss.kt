package com.example.stickwars.`class`

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.example.stickwars.classEnums.BdSharedPreferences
import com.example.stickwars.classEnums.Chefoes

class Boss(nome: String,
           override var atkStats: Int,
           override var defStats: Int,
           var nivelBoss: Int,
           var derrotado: Boolean )
    : Personagem(nome, atkStats, defStats){

    fun evoluirChefe(mContext: Context){
        if(derrotado && nivelBoss+1 < Chefoes.values().size){
            // Caso tenha um proximo Chefao ele carrega as novas informações
            this.nome = Chefoes.values().get(nivelBoss+1).nomeChefao
            this.atkStats = Chefoes.values().get(nivelBoss+1).atkStats
            this.defStats = Chefoes.values().get(nivelBoss+1).defStats
            this.derrotado = Chefoes.values().get(nivelBoss+1).derrotado
            this.nivelBoss = Chefoes.values().get(nivelBoss+1).nivelBoss
        }else{
            // Caso não tenha você completou o jogo
            Toast.makeText(mContext, "Você derrotou todos os bosses, Parabens!", Toast.LENGTH_LONG).show()
        }
    }

    fun salvarDadosBoss(adicionarPreferences : SharedPreferences.Editor){
        adicionarPreferences.putString(BdSharedPreferences.bossNome.key, nome).toString()
        adicionarPreferences.putInt(BdSharedPreferences.bossAtkStats.key, atkStats)
        adicionarPreferences.putInt(BdSharedPreferences.bossDefStats.key, defStats)
        adicionarPreferences.putInt(BdSharedPreferences.bossNivel.key, nivelBoss)
        adicionarPreferences.putBoolean(BdSharedPreferences.bossDerrotado.key, derrotado)
        adicionarPreferences.apply()
    }


}