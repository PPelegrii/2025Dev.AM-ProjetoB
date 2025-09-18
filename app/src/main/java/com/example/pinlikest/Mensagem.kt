package com.example.pinlikest

data class Mensagem(
    val mensagemTitulo: String,
    val mensagemDescricao: String,
    val mensagemRemetente: String,
    val mensagemDestinatario: String
)

object MensagensDatabase {
    val mensagensData = mutableListOf<Mensagem>(
        Mensagem("PrimeiraMensagem","Sua Primeira Mensagem", "Fozi", "OGrandeMago3307"),
        Mensagem("Precisa arrumar a linha 247","Você leu o titulo", "Professor", "Aluno1"),
        Mensagem("Nada mau","mal*", "Fabricio", "Fozi"),
    )

    fun getMensagens(): List<Mensagem> = mensagensData

    fun addMensagem(mensagem: Mensagem) {
        mensagensData.add(mensagem)
    }
}