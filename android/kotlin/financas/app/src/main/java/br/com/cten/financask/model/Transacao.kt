package br.com.cten.financask.model

import java.math.BigDecimal
import java.util.Calendar

/**
 * Created by Carol on 06/12/2017.
 */
class Transacao(val valor: BigDecimal,
                val categoria: String = "Indefinida",
                val tipo: Tipo,
                val data: Calendar = Calendar.getInstance()) { // definindo valor padrao

    // Sobrecarga de métodos
    /*constructor(valor: BigDecimal, tipo: Tipo)
            : this(valor, "Indefinida", tipo)

    constructor(valor: BigDecimal, tipo: Tipo, data : Calendar)
            : this(valor, "Indefinida", tipo, data)*/

    // trocar variaveis para 'var' caso desejarmos que estas variaveis possam ser modificadas

    // properties: get e set "automaticos"

    // Não são necessários dentro da classe.
    // Podem ser declarados no parametro, que ja terao get e set tbm
    /*var valor: BigDecimal = valor
    var categoria: String = categoria
    var data: Calendar = data*/

    /*set(value) {
        field = value
    }

    get() {
        return field
    }*/

    /*
    Mudar apenas internamente:

    private set

    fun alterarValor() {
        valor = BigDecimal(20.5)
    }
    * */
}