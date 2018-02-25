package br.com.cten.financask.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Carol on 14/02/2018.
 */

fun String.limitaEmAte(caracteres: Int) : String {
    if(this.length > caracteres) {
        val primeiroCaracter = 0
        return "${this.substring(primeiroCaracter, caracteres)}..." // String Template
    }

    return this
}

fun String.converteParaCalendar() : Calendar {
    // No Kotlin não é obrigatório a implementação de checked exceptions. Todas serão unchecked

    val formatoBrasileiro = SimpleDateFormat("dd/MM/yyyy")
    val dataConvertida: Date = formatoBrasileiro.parse(this)
    val data = Calendar.getInstance()
    data.time = dataConvertida

    return data
}