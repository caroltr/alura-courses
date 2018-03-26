package br.com.cten.financask.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import br.com.cten.financask.R
import br.com.cten.financask.dao.TransacaoDAO
import br.com.cten.financask.model.Tipo
import br.com.cten.financask.model.Transacao
import br.com.cten.financask.ui.ResumoView
import br.com.cten.financask.ui.adapter.ListaTransacoesAdapter
import br.com.cten.financask.ui.dialog.AdicionaTransacaoDialog
import br.com.cten.financask.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

/**
 * Created by Carol on 22/11/2017.
 */
class ListaTransacoesActivity : AppCompatActivity() {

    // private val transacoes: MutableList<Transacao> = mutableListOf() // recebe uma lista vazia

    private val dao = TransacaoDAO()
    private val transacoes = dao.transacoes

    private val viewDaActivity: View by lazy { // by lazy-> vou inicializar quando a property for utilizada
        window.decorView
    }

    private val viewGroupDaActivity by lazy { // by lazy-> vou inicializar quando a property for utilizada
        viewDaActivity as ViewGroup
    }

    // private lateinit var viewDaActivity: View // lateinit -> digo que a variavel será inicializada depois
    // private var viewDaActivity: View? = null
    // ? -> variavel tbm pode receber valor nulo (null safety)
    // !! -> eu me responsabilizo pelo codigo, sobre uma variavel que pode ser nula (PERIGOSO)

    // Parâmetros na notação Pascal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_lista_transacoes)

        // viewDaActivity = window.decorView // não é necessário inicializar aqui, já que foi inicializado pelo "by lazy"

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
        AdicionaTransacaoDialog(viewGroupDaActivity, this)
                .chama(tipo) { // HOF no final

                        adiciona(it)
                        lista_transacoes_adiciona_menu.close(true)
                }
    }

    private fun adiciona(transacao: Transacao) {
        dao.adiciona(transacao)
        atualizaTransacoes()
    }

    private fun atualizaTransacoes() {
        configuraLista()
        configuraResumo()
    }

    private fun configuraResumo() {
        val resumoView = ResumoView(this, viewDaActivity, transacoes)

        resumoView.atualiza()
    }


    private fun configuraLista() {
        // findViewById<ListView>(R.id.lista_transacoes_listview)
        val listaTransacoesAdapter = ListaTransacoesAdapter(transacoes, this)

        // chama o objeto apenas uma vez, e executa operações com ele
        with(lista_transacoes_listview) {
            // this aqui se refere a listview
            // this@ListaTransacoesActivity
            adapter = listaTransacoesAdapter
            setOnItemClickListener { _, _, position, _ ->
                val transacao = transacoes[position]
                chamaDialogDeAlteracao(transacao, position)
            }

            setOnCreateContextMenuListener { menu, _, _ ->

                menu.add(Menu.NONE, 1, Menu.NONE, "Remover")
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {

        val idDoMenu = item?.itemId

        if(idDoMenu == 1) {
            val adapterMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
            val posicaoDaTransacao = adapterMenuInfo.position

            remove(posicaoDaTransacao)
        }

        return super.onContextItemSelected(item)
    }

    private fun remove(posicao: Int) {
        dao.remove(posicao)
        atualizaTransacoes()
    }

    private fun chamaDialogDeAlteracao(transacao: Transacao, position: Int) {
        AlteraTransacaoDialog(this, viewGroupDaActivity)
                .chama(transacao, { transacaoAlterada ->

                        altera(transacaoAlterada, position)
                })
    }

    private fun altera(transacao: Transacao, position: Int) {
        dao.altera(transacao, position)
        atualizaTransacoes()
    }
}