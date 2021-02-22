package com.example.stickwars.`class`

import android.content.Context
import android.content.SharedPreferences
import android.media.Image
import android.widget.*
import com.example.stickwars.classEnums.BdSharedPreferences
import com.example.stickwars.classEnums.Classes
import kotlin.random.Random

class Player(nome: String ):Personagem(nome) {

    override var atkStats: Int = 0
    override var defStats: Int = 0
    lateinit var classe: String
    override var expTotal: Double = 0.0

    fun evoluirForca() {
        atkStats = atkStats?.plus(1)
        expTotal = (atkStats+defStats).toDouble() / 5
    }

    fun evoluirDefesa() {
        defStats = defStats?.plus(1)
        expTotal = (atkStats+defStats).toDouble() / 5
    }

    fun setarClasse(nomeClasse: String, imagem: ImageView){
        // Utiliza a enum class Classes para setar a configuração inical de cada Classe
        if(nomeClasse.equals(Classes.Ark.nomeClasse)){
            this.atkStats = Classes.Ark.atkStats
            this.defStats = Classes.Ark.defStats
            this.classe = Classes.Ark.nomeClasse
            this.expTotal = Classes.Ark.expTotal
        }else if(nomeClasse.equals(Classes.War.nomeClasse)){
            this.atkStats = Classes.War.atkStats
            this.defStats = Classes.War.defStats
            this.classe = Classes.War.nomeClasse
            this.expTotal = Classes.War.expTotal
        }else{
            this.atkStats = Classes.Mage.atkStats
            this.defStats = Classes.Mage.defStats
            this.classe = Classes.Mage.nomeClasse
            this.expTotal = Classes.Mage.expTotal
        }

        atualizarImagemClasse(imagem)
    }


    fun setarInfoSalva(atkStats: Int, defStats: Int, classe: String, expTotal: Double){
        this.atkStats = atkStats
        this.defStats = defStats
        this.classe = classe
        this.expTotal = expTotal
    }

    fun salvarDadosPlayer(adicionarPreferences : SharedPreferences.Editor){
        adicionarPreferences.putInt(BdSharedPreferences.playerAtkStats.key, atkStats)
        adicionarPreferences.putInt(BdSharedPreferences.playerDefStats.key, defStats)
        adicionarPreferences.putFloat(BdSharedPreferences.playerExpTotal.key, expTotal.toFloat())
        adicionarPreferences.apply()
    }

    fun atualizarImagemClasse(imagem: ImageView){
        if(classe.equals(Classes.Ark.nomeClasse)){
            imagem.setImageResource(Classes.Ark.img)
        }else if(classe.equals(Classes.War.nomeClasse)){
            imagem.setImageResource(Classes.War.img)
        }else{
            imagem.setImageResource(Classes.Mage.img)
        }
    }

}