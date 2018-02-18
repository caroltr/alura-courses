package br.com.cten.financask.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.cten.financask.R
import br.com.cten.financask.model.Tipo
import br.com.cten.financask.model.Transacao
import br.com.cten.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal

/**
 * Created by Carol on 22/11/2017.
 */
class ListaTransacoesActivity : AppCompatActivity() {

    // Parâmetros na notação Pascal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_lista_transacoes)

        // val = final e var = variaveis mutáveis
        // Named Parameter: nomear os parametros, para que nao seja necessario se preocupar com ordem
        val transacoes: List<Transacao> = transacoesDeExemplo()
        configuraLista(transacoes)
    }

    private fun configuraLista(transacoes: List<Transacao>) {
        // findViewById<ListView>(R.id.lista_transacoes_listview)
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

    private fun transacoesDeExemplo(): List<Transacao> {
        return listOf(Transacao(
                tipo = Tipo.DESPESA,
                valor = BigDecimal(20.5)),
                Transacao(valor = BigDecimal(100.0),
                        tipo = Tipo.RECEITA,
                        categoria = "Economia"),
                Transacao(valor = BigDecimal(200.0),
                        tipo = Tipo.DESPESA),
                Transacao(valor = BigDecimal(500.0),
                        categoria = "Prêmio",
                        tipo = Tipo.RECEITA))
    }
}