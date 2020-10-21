package com.github.OnikenX.Android_calculator.logic

import com.github.OnikenX.Android_calculator.Operator


abstract interface IEstado {
    fun addInput(value: Int): IEstado
    fun setOperator(operator: Operator): IEstado
    fun reset(): IEstado
    fun result(): IEstado
}

abstract open class CalculatorLogic(calculatorData: CalculatorData) : IEstado {

    var calculatorData: CalculatorData = calculatorData
    override fun result(): IEstado {
        return this
    }

    override fun addInput(value: Int): IEstado {
        calculatorData.addIntToInput(value)
        return this
    }

    override fun setOperator(operator: Operator): IEstado {
        return this
    }

    override fun reset(): IEstado {
        calculatorData.reset()
        return CalculoParteUm(calculatorData)
    }
}

// classe para quando esta a fazer um calculo, o programa começa com este estado
//argumentos:
//  os argumentos de defalt esperam que este esteja a ser carregado pela primeira vez
//  meter se o int to add o construtor vai ignorar tudo o resto excepto a calculator
class CalculoParteUm(
    calculatorData: CalculatorData,
    intToAdd: Int = -1
) : CalculatorLogic(calculatorData) {
    init {
        calculatorData.reset()
        if (intToAdd != -1) {
            calculatorData.addIntToInput(intToAdd)
        }
    }

    override fun setOperator(operator: Operator): IEstado {
        if (!calculatorData.input.isEmpty()) {
            calculatorData.value1 = calculatorData.input.toDouble()
            return CalculoParteDois(calculatorData, operator)
        }
        return this
    }
}

class CalculoParteDois(calculatorData: CalculatorData, operator: Operator) : CalculatorLogic(calculatorData) {
    init {
        calculatorData.input = ""
        calculatorData.operator = operator
    }

    override fun setOperator(operator: Operator): IEstado {
        if (!calculatorData.input.isEmpty()) {
            calculatorData.value2 = calculatorData.input.toDouble()
            calculatorData.fazConta()
            return CalculoParteDois(calculatorData, operator)
        }
        return this
    }

    override fun result(): IEstado {
        if (!calculatorData.input.isEmpty()) {
            calculatorData.value2 = calculatorData.input.toDouble()
            calculatorData.input = ""
            return Resultado(calculatorData)
        }
        return this
    }

}

class Resultado(calculatorData: CalculatorData) : CalculatorLogic(calculatorData) {
    init {
        calculatorData.fazConta()
    }

    override fun result(): IEstado {
        return Resultado(calculatorData)
    }
    override fun setOperator(operator: Operator): IEstado {
            return CalculoParteDois(calculatorData, operator)
    }

    override fun addInput(value: Int): IEstado {
        return CalculoParteUm(calculatorData, value)
    }


}