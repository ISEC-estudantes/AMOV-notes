package com.github.OnikenX.Android_calculator

import android.app.Activity
import android.os.Bundle
import com.github.OnikenX.Android_calculator.logic.BasicCalculator

import kotlinx.android.synthetic.main.calculator.*

enum class Operator {
    remainer, division, multiply, minus, plus, none
}

class Calculator : Activity() {
    var basicCalculator = BasicCalculator()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator)
        setCallers()
    }

    //update do screen
    fun updateScr() {
        screen.text = basicCalculator.getScreen()
    }

    //acoes dos botoes
    fun addInput(value: Int) {
        basicCalculator.addInput(value)
        updateScr()
    }

    fun actionButton(operator: Operator) {
        if (operator == Operator.none)
            basicCalculator.result()
        else basicCalculator.setOperator(operator)
        updateScr()
    }

    // setup dos constrollers para chamar as funcoes dos buttoes
    fun setCallersInput() {
        one.setOnClickListener {
            addInput(1)
        }
        two.setOnClickListener {
            addInput(2)
        }
        tree.setOnClickListener {
            addInput(3)
        }
        four.setOnClickListener {
            addInput(4)
        }
        five.setOnClickListener {
            addInput(5)
        }
        six.setOnClickListener {
            addInput(6)
        }
        seven.setOnClickListener {
            addInput(7)
        }
        eight.setOnClickListener {
            addInput(8)
        }
        nine.setOnClickListener {
            addInput(9)
        }
        zero.setOnClickListener {
            addInput(0)
        }
        point.setOnClickListener {
            addInput(10)
        }
    }


    fun setCallersOperators() {
        equal.setOnClickListener {
            actionButton(Operator.none)
        }
        minus.setOnClickListener {
            actionButton(Operator.minus)
        }
        plus.setOnClickListener {
            actionButton(Operator.plus)
        }
        remainer.setOnClickListener {
            actionButton(Operator.remainer)
        }
        multiply.setOnClickListener {
            actionButton(Operator.multiply)
        }
        division.setOnClickListener {
            actionButton(Operator.division)
        }
    }

    fun setCallers() {
        AC.setOnClickListener {
            basicCalculator.reset()
            updateScr()
        }
        setCallersInput()
        setCallersOperators()
    }

}
