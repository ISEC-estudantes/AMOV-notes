package com.github.OnikenX.Android_calculator.logic

import com.github.OnikenX.Android_calculator.Operator


interface IEstado {
    fun addInput(value: Int): IEstado
    fun setOperator(operator: Operator): IEstado
    fun reset(): IEstado
}

open class CalculatorLogic(calculatorData: CalculatorData) : IEstado {

    var calculatorData: CalculatorData = calculatorData

    override fun addInput(value: Int): IEstado {
        calculatorData.addIntToInput(value)
        return this
    }

    override fun setOperator(operator: Operator): IEstado {
        return this
    }

    override fun reset(): IEstado {
        calculatorData.reset()
        return Novo(calculatorData, true)
    }
}

// classe para quando esta a fazer um calculo, o programa começa com este estado
//argumentos:
//  os argumentos de defalt esperam que este esteja a ser carregado pela primeira vez
//  meter se o int to add o construtor vai ignorar tudo o resto excepto a calculator
class Novo(
    calculatorData: CalculatorData,
    inFirstValue: Boolean = true,
    operator: Operator = Operator.none,
    intToAdd: Int = -1
) : CalculatorLogic(calculatorData) {
    init {
        calculatorData.novoEstado(inFirstValue, operator, intToAdd)
    }

    override fun setOperator(operator: Operator): IEstado {
        if (calculatorData.inFirstValue)
            if (!calculatorData.input.isEmpty())
                if (operator != Operator.none) {
                    calculatorData.value1 = calculatorData.input.toDouble()
                    calculatorData.input = ""
                    calculatorData.operator = operator
                    calculatorData.inFirstValue = false
                } else if (!calculatorData.input.isEmpty()) {
                    calculatorData.value2 = calculatorData.input.toDouble()
                    calculatorData.input = ""
                    if (operator != Operator.none) {
                        calculatorData.value2 = calculatorData.input.toDouble()
                        calculatorData.input = ""
                        return Novo(calculatorData, false, operator)
                    } else {
                        return Resultado(calculatorData)
                    }
                }
        return this
    }
}

class Resultado(calculatorData: CalculatorData) : CalculatorLogic(calculatorData) {
    init {
        calculatorData.resultadoMode()
    }

    override fun setOperator(operator: Operator): IEstado {
        if (operator == Operator.none) {
            calculatorData.fazConta()
            return this
        } else
            return Novo(calculatorData, false, operator)
    }

    override fun addInput(value: Int): IEstado {
        return Novo(calculatorData, false, Operator.none, value);
    }


}