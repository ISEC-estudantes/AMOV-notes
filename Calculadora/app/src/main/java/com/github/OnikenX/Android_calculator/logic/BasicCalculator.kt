package com.github.OnikenX.Android_calculator.logic

import com.github.OnikenX.Android_calculator.Operator


class BasicCalculator() {
    var calculatorData = CalculatorData()
    var estado: IEstado = CalculoParteUm(calculatorData)

    fun addInput(value: Int) {
        estado = estado.addInput(value)
    }

    fun reset() {
        estado = estado.reset()
    }

    fun setOperator(operator: Operator) {
        if (operator == Operator.none)
            throw IllegalArgumentException("None is note a possible aritmetic operation")
        estado = estado.setOperator(operator)
    }

    fun result() {
        estado = estado.result()
    }

    fun getScreen() :String{
        return when (estado) {
            is CalculoParteUm -> calculatorData.input
            is CalculoParteDois -> {
                var output: String = when (calculatorData.operator) {
                    Operator.none -> throw IllegalAccessError("None should not exist in operator when Parte2 is a state")
                    Operator.division -> "${calculatorData.value1} / "
                    Operator.multiply -> "${calculatorData.value1} x "
                    Operator.remainer -> "${calculatorData.value1} % "
                    Operator.plus -> "${calculatorData.value1} + "
                    Operator.minus -> "${calculatorData.value1} - "
                }
                if (!calculatorData.input.isEmpty())
                    "$output${calculatorData.input}" else output
            }
            is Resultado -> calculatorData.value1.toString()
            else -> throw IllegalStateException("This state should not be possible")
        }
    }
}