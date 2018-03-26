package br.com.cten.financask.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import br.com.cten.financask.R
import br.com.cten.financask.extension.formataParaBrasileiro
import br.com.cten.financask.model.Resumo
import br.com.cten.financask.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

/**
 * Created by Carol on 18/02/2018.
 */
class ResumoView(private val context: Context,
                 private val view: View,
                 transacoes: List<Transacao>) {

    private val resumo : Resumo = Resumo(transacoes)
    private val corReceita = ContextCompat.getColor(context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)

    fun atualiza() {
        adicionaReceita()
        adicionaDespesa()
        adicionaTotal()
    }

    private fun adicionaReceita() {

        val totalReceita = resumo.receita

        // Safe call: adicionar ? na variavel é equivalente a if(view != null) {}
        //view?.resumo_card_receita?.setTextColor(corReceita)
        //view?.resumo_card_receita?.text = totalReceita.formataParaBrasileiro()

        with(view.resumo_card_receita) {
            setTextColor(corReceita)
            text = totalReceita.formataParaBrasileiro()
        }
    }

    private fun adicionaDespesa() {

        val totalDespesa = resumo.despesa()
        /*view?.let {
            with(it.resumo_card_despesa) {
                setTextColor(corDespesa)
                text = totalDespesa.formataParaBrasileiro()
            }
        }*/
        with(view.resumo_card_despesa) {
            setTextColor(corDespesa)
            text = totalDespesa.formataParaBrasileiro()
        }
    }

    private fun adicionaTotal() {

        val total = resumo.total()
        val cor: Int = corPor(total)
        with(view.resumo_card_total) {
            setTextColor(cor)
            text = total.formataParaBrasileiro()
        }
    }

    private fun corPor(valor: BigDecimal): Int {
        // if (valor.compareTo(BigDecimal.ZERO) >= 0) {
        // no Kotlin não existe tipos primitivos, portanto, pode-se usar os operadores lógicos para
        // fazer comparacoes entre objetos

        if (valor >= BigDecimal.ZERO) {
            return corReceita
        }
        return corDespesa
    }
}