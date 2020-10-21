package com.github.OnikenX.Android_calculator.logic

import com.github.OnikenX.Android_calculator.Operator


class BasicCalculator() {
    var calculatorData = CalculatorData()
    var estado: IEstado = Novo(calculatorData)


    fun getScreen(): String {
        return calculatorData.getScreenText()
    }

    fun addInput(value: Int) {
        estado = estado.addInput(value)
    }

    fun reset() {
        estado = estado.reset()
    }

    fun setOperator(operator: Operator) {
        estado = estado.setOperator(operator)
    }


}