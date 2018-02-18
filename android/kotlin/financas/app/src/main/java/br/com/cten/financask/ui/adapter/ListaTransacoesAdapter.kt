package br.com.cten.financask.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.cten.financask.R
import br.com.cten.financask.extension.formataParaBrasileiro
import br.com.cten.financask.extension.limitaEmAte
import br.com.cten.financask.model.Tipo
import br.com.cten.financask.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

/**
 * Created by Carol on 22/11/2017.
 */
class ListaTransacoesAdapter(private val transacoes: List<Transacao>,
                             private val contexto: Context) : BaseAdapter() {

    // property
    private val limiteDaCategoria = 14

    override fun getView(posicao: Int, view: View?, parent: ViewGroup?): View {
        val viewCriada = LayoutInflater.from(contexto).inflate(R.layout.transacao_item, parent, false)


        val transacao = transacoes[posicao]

        adicionaValor(transacao, viewCriada)
        adicionaIcone(transacao, viewCriada)

        // TextView setting text como property
        adicionaCategoria(viewCriada, transacao)
        adicionaData(viewCriada, transacao)

        return viewCriada
    }

    private fun adicionaData(viewCriada: View, transacao: Transacao) {
        viewCriada.transacao_data.text = transacao.data.formataParaBrasileiro()
    }

    private fun adicionaCategoria(viewCriada: View, transacao: Transacao) {
        viewCriada.transacao_categoria.text = transacao.categoria.limitaEmAte(limiteDaCategoria)
    }

    private fun adicionaIcone(transacao: Transacao, viewCriada: View) {
        val icone: Int = iconePor(transacao)

        viewCriada.transacao_icone
                .setBackgroundResource(icone)
    }

    private fun iconePor(transacao: Transacao): Int {

        // early return
        if (transacao.tipo == Tipo.RECEITA) {
            return R.drawable.icone_transacao_item_receita
        }

        return R.drawable.icone_transacao_item_despesa
    }

    private fun adicionaValor(transacao: Transacao, viewCriada: View) {

        // if expression
        val cor: Int = corPor(transacao.tipo)

        viewCriada.transacao_valor
                .setTextColor(cor)

        viewCriada.transacao_valor.text = transacao.valor.formataParaBrasileiro()
    }

    private fun corPor(tipo: Tipo): Int {
        return if (tipo == Tipo.RECEITA) {
            ContextCompat.getColor(contexto, R.color.receita)
        } else {
            ContextCompat.getColor(contexto, R.color.despesa)
        }
    }


    // Any = Object
    override fun getItem(posicao: Int): Transacao {
        return transacoes[posicao]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }
}