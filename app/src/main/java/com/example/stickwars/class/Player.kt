package com.example.stickwars.`class`

import android.content.Context
import android.media.Image
import android.widget.*
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

    fun combateBoss( Chefao: Boss, mContex: Context) {
        var forcaUsuario = atkStats + defStats
        var forcaBoss = Chefao.atkStats + Chefao.defStats
        var somaForcas = forcaUsuario + forcaBoss
        var resultConfront = Random.nextInt(0, somaForcas)

        // Faz um sorteio para saber quem venceu o combate
        if (resultConfront in 0..forcaUsuario) {
            // Se ganhar ocorre isso...
            Toast.makeText(mContex, "Derrotou o Boss ${Chefao.nome} - Lvl. ${Chefao.nivelBoss+1}", Toast.LENGTH_SHORT).show()
            Chefao.derrotado = true
            Chefao.evoluirChefe(mContex)
        }else{
            // Se perder ocorre isso...
            Toast.makeText(mContex, "Perdeu para o Boss ${Chefao.nome} - Lvl. ${Chefao.nivelBoss+1}", Toast.LENGTH_SHORT).show()
        }
    }

    fun setarClasse(nomeClasse: String, imagem: ImageView){
        // Utiliza a enum class Classes para setar a configuração inical de cada Classe
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

    fun setarInfoSalvaStats(atkStats: Int, defStats: Int, classe: String, expTotal: Double,imgClass : ImageView){
        this.atkStats = atkStats
        this.defStats = defStats
        this.classe = classe
        this.expTotal = expTotal

        if(classe.equals(Classes.Ark.nomeClasse)){
            imgClass.setImageResource(Classes.Ark.img)
        }else if(classe.equals(Classes.War.nomeClasse)){
            imgClass.setImageResource(Classes.War.img)
        }else{
            imgClass.setImageResource(Classes.Mage.img)
        }

    }

    fun setarInfoSalva(atkStats: Int, defStats: Int, classe: String, expTotal: Double){
        this.atkStats = atkStats
        this.defStats = defStats
        this.classe = classe
        this.expTotal = expTotal

    }

}