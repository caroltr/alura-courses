package br.com.cten.financask.extension

import java.text.SimpleDateFormat
import java.util.Calendar

/**
 * Created by Carol on 14/02/2018.
 */

// Extension function: será uma função da classe Calendar
fun Calendar.formataParaBrasileiro() : String {
    val formatoBrasileiro = "dd/MM/yyyy"
    val format = SimpleDateFormat(formatoBrasileiro)
    return format.format(this.time)
}