package com.example.stickwars.classEnums

enum class BdSharedPreferences(val key: String) {
    USUARIO_LOGADO("UsuarioLogado"),
    PLAYER_NOME("playerNome"),
    PLAYER_ATK_STATS("playerAtkStats"),
    PLAYER_DEF_STATS("playerDefStats"),
    PLAYER_CLASSE("playerClasse"),
    PLAYER_EXP_TOTAL("playerExpTotal"),
    BOSS_NOME("bossNome"),
    BOSS_ATK_STATS("bossAtkStats"),
    BOSS_DEF_STATS("bossDefStats"),
    BOSS_NIVEL("bossNivel"),
    BOSS_DERROTADO("bossDerrotado")
}