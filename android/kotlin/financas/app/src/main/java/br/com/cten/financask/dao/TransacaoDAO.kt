package br.com.cten.financask.dao

import br.com.cten.financask.model.Transacao

/**
 * Created by Carol on 26/03/2018.
 */
class TransacaoDAO {

    val transacoes: List<Transacao> = Companion.transacoes

    // Colocamos tudo o que esperamos que permaneça de forma estática
    companion object {
        private val transacoes: MutableList<Transacao> = mutableListOf()
    }

    fun adiciona(transacao : Transacao) {
        Companion.transacoes.add(transacao)
    }

    fun altera(transacao : Transacao, posicao: Int) {
        Companion.transacoes[posicao] = transacao
    }

    fun remove(posicao: Int) {
        Companion.transacoes.removeAt(posicao)
    }
}