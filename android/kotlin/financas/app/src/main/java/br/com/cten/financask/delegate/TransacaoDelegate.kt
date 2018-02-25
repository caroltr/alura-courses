package br.com.cten.financask.delegate

import br.com.cten.financask.model.Transacao

/**
 * Created by Carol on 25/02/2018.
 */
interface TransacaoDelegate {

        fun delegate (transacao: Transacao)
}