package com.example.stickwars.`class`

class Boss(nome: String,
           override var atkStats: Int,
           override var defStats: Int,
           override var expTotal: Double,
           var nivelBoss: Int,
           var derrotado: Boolean ): Personagem(nome, atkStats, defStats, expTotal){

    fun evoluirChefe(){
        if(derrotado){
            this.nivelBoss += 1
            this.nome = Chefoes.values().get(nivelBoss).nomeChefao
            this.atkStats = Chefoes.values().get(nivelBoss).atkStats
            this.defStats = Chefoes.values().get(nivelBoss).defStats
            this.expTotal = Chefoes.values().get(nivelBoss).expTotal
            this.derrotado = false
        }
    }

    override fun evoluirForca() {

    }

    override fun evoluirDefesa() {

    }

}