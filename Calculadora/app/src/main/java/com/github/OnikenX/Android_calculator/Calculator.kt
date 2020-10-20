package com.github.OnikenX.Android_calculator

import android.app.Activity
import android.os.Bundle
import android.util.Log.wtf
import android.util.Log.d

import kotlinx.android.synthetic.main.calculator.*

enum class Operator {
    remainer, division, multiply, minus, plus, none
}

class Calculator : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator)

        val calculator = BasicCalculator()

        calculator.setCallers()
    }


    inner class BasicCalculator {

        var input = ""
        var lastNum = 0.0
        var output = 0.0
        var actualNum = 0.0
        var lastOperator = Operator.none;
        var didOperation = false
        var resultado = 0.0

        fun reset() {
            input = ""
            lastNum = 0.0
            actualNum = 0.0
            lastOperator = Operator.none;
            didOperation = false
            updateScr()
        }

        fun operate(operator: Operator) {

            resultado = when (lastOperator) {
                Operator.minus -> output - actualNum
                Operator.plus -> output + actualNum
                Operator.multiply -> output * actualNum
                Operator.division -> if (actualNum != 0.0) output / actualNum else output
                Operator.remainer -> if (actualNum != 0.0) output % actualNum else 0.0
                Operator.none -> resultado
            }



            didOperation = when (lastOperator) {
                Operator.minus, Operator.plus, Operator.multiply, Operator.division, Operator.remainer -> true
                else -> false
            }

        }

        fun actionButton(operator: Operator) {
            if(operator)



        }

        fun showInput() {
            screen.text = input
        }
        fun showOutput(){
            screen.text = output.toString()
        }

        fun inputToDouble(): Double {
            if (input == "")
                return 0.0
            return input.toDouble()
        }

        fun addInput(value : Int){
            if(value >=0 || value <=9)
                input += value
          else
                if(input.find { c -> c == '.' } != null)
                    input+= '.'
            showInput()
        }

        fun setCallersInput(){

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

        fun setCallersOperators(){

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
                reset()
            }
            setCallersInput()
            setCallersOperators()
        }
    }
}
