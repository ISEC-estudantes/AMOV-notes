package com.github.OnikenX.Android_calculator.logic

import com.github.OnikenX.Android_calculator.Operator
import java.lang.IllegalArgumentException


class CalculatorData() {

    //string que guarda o input do que vem
    var input = ""

    // o primeiro valor para a conta e onde se guarda o ultimo resultado
    var value1 = 0.0

    // segundo valor para guardar
    var value2 = 0.0

    //diz em o operador para a conta a fazer
    var operator = Operator.none

    //diz se deve se preencher o value1 ou value2 para fazer uma conta
    var inFirstValue = true

    fun addIntToInput(value: Int) {
        if (value >= 0 || value <= 9)
            input += value
        else
            if (input.find { c -> c == '.' } != null)
                input += '.'
    }

    fun inputToDouble(): Double {
        return if (input.isEmpty())
            0.0
        else
            input.toDouble()
    }

    fun fazConta() {
        value1 = when (operator) {
            Operator.minus -> value1 - value2
            Operator.plus -> value1 + value2
            Operator.division -> value1 / value2
            Operator.remainer -> value1 % value2
            Operator.multiply -> value1 * value2
            Operator.none -> throw IllegalArgumentException("Can not do an aritmetic operation without operator")
        }

    }

    //faz setup das variaveis quando se vai para fazer uma nova conta ou continuar a fazer
    fun novoEstado(inFirstValue: Boolean, operator: Operator, intToAdd: Int) {
        if (intToAdd != -1) {
            reset()
            addIntToInput(intToAdd)
        } else if (inFirstValue) {
            reset()
        } else {
            fazConta()
            this.operator = operator
        }
        value2 = 0.0
    }

    fun reset() {
        input = ""
        value2 = 0.0
        value1 = 0.0
        inFirstValue = true
        operator = Operator.none
    }

    fun resultadoMode() {
        input = ""
    }

    // formata o que se tem guardado para mostrar no ecra
    fun getScreenText(): String {
        var output = ""
        when (inFirstValue) {
            true -> output = input
            false -> {
                output = when (operator) {
                    Operator.none -> input
                    Operator.division -> "$value1 / "
                    Operator.multiply -> "$value1 x "
                    Operator.remainer -> "$value1 % "
                    Operator.plus -> "$value1 + "
                    Operator.minus -> "$value1 - "
                }
                if (operator != Operator.none && input.toDouble() != 0.0)
                    output = ("$output $input").toString()
            }
        }
        return output
    }


}