package com.example.rpgclicker.`class`

import android.content.SharedPreferences
import android.widget.*
import com.example.rpgclicker.classEnums.BdSharedPreferences
import com.example.rpgclicker.classEnums.Classes

class Player(nome: String ):Personagem(nome) {

    override var atkStats: Int = 0
    override var defStats: Int = 0
    lateinit var classe: String
    override var expTotal: Double = 0.0

    override fun salvarDados(adicionarPreferences : SharedPreferences.Editor) {
        adicionarPreferences.putInt(BdSharedPreferences.PLAYER_ATK_STATS.key, atkStats)
        adicionarPreferences.putInt(BdSharedPreferences.PLAYER_DEF_STATS.key, defStats)
        adicionarPreferences.putFloat(BdSharedPreferences.PLAYER_EXP_TOTAL.key, expTotal.toFloat())
        adicionarPreferences.apply()
    }

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
        if(nomeClasse.equals(Classes.ARK.nomeClasse)){
            this.atkStats = Classes.ARK.atkStats
            this.defStats = Classes.ARK.defStats
            this.classe = Classes.ARK.nomeClasse
            this.expTotal = Classes.ARK.expTotal
        }else if(nomeClasse.equals(Classes.WAR.nomeClasse)){
            this.atkStats = Classes.WAR.atkStats
            this.defStats = Classes.WAR.defStats
            this.classe = Classes.WAR.nomeClasse
            this.expTotal = Classes.WAR.expTotal
        }else{
            this.atkStats = Classes.MAGE.atkStats
            this.defStats = Classes.MAGE.defStats
            this.classe = Classes.MAGE.nomeClasse
            this.expTotal = Classes.MAGE.expTotal
        }

        atualizarImagemClasse(imagem)
    }


    fun setarInfoSalva(atkStats: Int, defStats: Int, classe: String, expTotal: Double){
        this.atkStats = atkStats
        this.defStats = defStats
        this.classe = classe
        this.expTotal = expTotal
    }


    fun atualizarImagemClasse(imagem: ImageView){
        if(classe.equals(Classes.ARK.nomeClasse)){
            imagem.setImageResource(Classes.ARK.img)
        }else if(classe.equals(Classes.WAR.nomeClasse)){
            imagem.setImageResource(Classes.WAR.img)
        }else{
            imagem.setImageResource(Classes.MAGE.img)
        }
    }

    fun playerPower() : Double = (atkStats + defStats).toDouble() / 10
}