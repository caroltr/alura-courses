package br.com.cten.financask.extension

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