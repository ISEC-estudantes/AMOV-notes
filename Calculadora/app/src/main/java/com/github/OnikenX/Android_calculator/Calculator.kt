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

    fun updateScr() {
       screen.text = basicCalculator.getScreen()
    }

    fun addInput(value: Int) {
        basicCalculator.addInput(value)
        updateScr()
    }

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

    fun actionButton(operator: Operator) {
        basicCalculator.setOperator(operator)

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
        }
        setCallersInput()
        setCallersOperators()
    }

}
