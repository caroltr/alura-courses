package br.com.cten.financask.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.Locale

/**
 * Created by Carol on 14/02/2018.
 */

fun BigDecimal.formataParaBrasileiro() : String {
    val formatoBrasileiro = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))
    return formatoBrasileiro.format(this).replace("R$", "R$ ")
}