package com.github.OnikenX.Android_calculator.logic

import com.github.OnikenX.Android_calculator.Operator


class CalculatorData() {

    //string que guarda o input do que vem
    var input = ""

    // o primeiro valor para a conta e onde se guarda o ultimo resultado
    var value1 = 0.0

    // segundo valor para guardar
    var value2 = 0.0

    //diz em o operador para a conta a fazer
    var operator = Operator.none

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
        else if (input == "-")
            -1.0
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
    
    fun negateInput(){
        input = if (input.find{ c -> c == '-' } != null)
            input.filter{ c -> c == '-'}
        else "-${input}"
    }
    
    fun reset() {
        input = ""
        value2 = 0.0
        value1 = 0.0
        operator = Operator.none

    }


}