package com.example.stickwars.`class`

class Player(nome: String ):Personagem(nome) {

    override var atkStats: Int = 0
    override var defStats: Int = 0
    lateinit var classe: String
    override var expTotal: Double = 0.0

    override fun evoluirForca() {
        atkStats = atkStats?.plus(1)
        expTotal = ((atkStats+defStats)/5).toDouble()

    }
    override fun evoluirDefesa() {
        defStats = defStats?.plus(1)
        expTotal = ((atkStats+defStats)/5).toDouble()
    }

    fun setarClasse(nomeClasse: String){

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

    }

}