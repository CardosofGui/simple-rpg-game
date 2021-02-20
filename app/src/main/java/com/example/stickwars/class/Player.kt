package com.example.stickwars.`class`

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
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

    fun combateBoss( Chefao: Boss, mContex: Context) {
        var forcaUsuario = atkStats + defStats
        var forcaBoss = Chefao.atkStats + Chefao.defStats
        var somaForcas = forcaUsuario + forcaBoss
        var resultConfront = Random.nextInt(0, somaForcas)

        if (resultConfront in 0..forcaUsuario) {
            Toast.makeText(mContex, "Derrotou o Boss " + Chefao.nome + " " + Chefao.nivelBoss, Toast.LENGTH_SHORT).show()
            Chefao.derrotado = true
            Chefao.evoluirChefe(mContex)
        }else{
            Toast.makeText(mContex, "Perdeu " + Chefao.nome + " " + Chefao.nivelBoss, Toast.LENGTH_SHORT).show()
        }
    }

    fun setarClasse(nomeClasse: String, imagem: ImageView){

        if(nomeClasse.equals(Classes.Ark.nomeClasse)){
            this.atkStats = Classes.Ark.atkStats
            this.defStats = Classes.Ark.defStats
            this.classe = Classes.Ark.nomeClasse
            this.expTotal = Classes.Ark.expTotal
            imagem.setImageResource(Classes.Ark.img)
        }else if(nomeClasse.equals(Classes.War.nomeClasse)){
            this.atkStats = Classes.War.atkStats
            this.defStats = Classes.War.defStats
            this.classe = Classes.War.nomeClasse
            this.expTotal = Classes.War.expTotal
            imagem.setImageResource(Classes.War.img)
        }else{
            this.atkStats = Classes.Mage.atkStats
            this.defStats = Classes.Mage.defStats
            this.classe = Classes.Mage.nomeClasse
            this.expTotal = Classes.Mage.expTotal
            imagem.setImageResource(Classes.Mage.img)
        }

    }

}