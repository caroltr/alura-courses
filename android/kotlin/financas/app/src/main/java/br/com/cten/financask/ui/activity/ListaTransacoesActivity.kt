package br.com.cten.financask.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import br.com.cten.financask.R
import br.com.cten.financask.delegate.TransacaoDelegate
import br.com.cten.financask.model.Tipo
import br.com.cten.financask.model.Transacao
import br.com.cten.financask.ui.ResumoView
import br.com.cten.financask.ui.adapter.ListaTransacoesAdapter
import br.com.cten.financask.ui.dialog.AdicionaTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

/**
 * Created by Carol on 22/11/2017.
 */
class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf() // recebe uma lista vazia

    // Parâmetros na notação Pascal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_lista_transacoes)

        // val = final e var = variaveis mutáveis
        // Named Parameter: nomear os parametros, para que nao seja necessario se preocupar com ordem
        // val transacoes: List<Transacao> = transacoesDeExemplo()

        configuraResumo()
        configuraLista()
        configuraFab()

        // Object expression
        /*lista_transacoes_adiciona_receita.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) { }
        })*/
        // this@ListaTransacoesActivity ou no Kotlin pode-se usar apenas this dentro de classes anônimas
    }

    private fun configuraFab() {
        lista_transacoes_adiciona_receita.setOnClickListener {
            chamaDialogTheAdicao(Tipo.RECEITA)
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            chamaDialogTheAdicao(Tipo.DESPESA)
        }
    }

    private fun chamaDialogTheAdicao(tipo: Tipo) {
        AdicionaTransacaoDialog(this, window.decorView as ViewGroup)
                .chama(tipo, object : TransacaoDelegate {
                    override fun delegate(transacao: Transacao) {

                        atualizaTransacoes(transacao)
                        lista_transacoes_adiciona_menu.close(true)
                    }
                })
    }

    private fun atualizaTransacoes(transacao: Transacao) {
        transacoes.add(transacao)
        configuraLista()
        configuraResumo()
    }

    private fun configuraResumo() {
        val view = window.decorView // tela da activity
        val resumoView = ResumoView(this, view, transacoes)

        resumoView.atualiza()
    }


    private fun configuraLista() {
        // findViewById<ListView>(R.id.lista_transacoes_listview)
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }
}