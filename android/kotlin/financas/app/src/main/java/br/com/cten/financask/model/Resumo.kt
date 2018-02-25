package br.com.cten.financask.model

import java.math.BigDecimal

/**
 * Created by Carol on 18/02/2018.
 */
class Resumo(private val transacoes: List<Transacao>) {

    // Função como property
    val receita get() = somaPor(Tipo.RECEITA)

    fun despesa() = somaPor(Tipo.DESPESA)

    private fun somaPor(tipo : Tipo) : BigDecimal {

        // for loop
        /*var totalReceita = BigDecimal.ZERO
        for (transacao in transacoes) {
            if (transacao.tipo == Tipo.RECEITA) {
                totalReceita = totalReceita.plus(transacao.valor)
            }
        }

        return totalReceita*/

        val soma = transacoes
                .filter { it.tipo == tipo } // it ou nomeando a variavel
                .sumByDouble { transacao -> transacao.valor.toDouble() }

        return BigDecimal(soma)
    }

    // Single expression function
    // Dessa forma não é obrigatório deixar o tipo do retorno explícito
    fun total() = receita.subtract(despesa())
}