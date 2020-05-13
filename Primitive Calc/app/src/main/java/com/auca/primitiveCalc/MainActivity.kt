package com.auca.primitiveCalc

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged

class MainActivity : AppCompatActivity() {

    lateinit var firstOperandInput : EditText
    lateinit var secondOperandInput : EditText
    lateinit var resultOutput : TextView
    lateinit var radioButtonGroup : RadioGroup



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstOperandInput = findViewById(R.id.FirstOperand)
        secondOperandInput = findViewById(R.id.SecondOperand)
        resultOutput = findViewById(R.id.Result)
        radioButtonGroup = findViewById(R.id.Operations)

        firstOperandInput.doAfterTextChanged {
            recalculate()
        }

        secondOperandInput.doAfterTextChanged {
            recalculate()
        }

        radioButtonGroup.setOnCheckedChangeListener { group, checkedId ->
            recalculate()
        }
        
        loadPrefs()
    }

    fun recalculate() {
        val radioButtonID = radioButtonGroup.checkedRadioButtonId
        val radioButton: RadioButton? = radioButtonGroup.findViewById(radioButtonID)


        radioButton?.let {
            val operation = radioButtonGroup.indexOfChild(radioButton)

            // check null value for a and b
            val a = if ( firstOperandInput.text.toString().isNotEmpty() ) {
                    firstOperandInput.text.toString().toLong()
                }
                else {
                    Toast.makeText(getApplicationContext(), "Enter First Operand", Toast.LENGTH_SHORT).show()
                    return
                }

            val b = if ( secondOperandInput.text.toString().isNotEmpty() ) {
                    secondOperandInput.text.toString().toLong()
                }
                else {
                    Toast.makeText(getApplicationContext(), "Enter Second Operand", Toast.LENGTH_SHORT).show()
                    return
                }

            val result: Long

            result = when (operation) {
                0 -> a + b
                1 -> a - b
                2 -> a * b
                3 -> if (b == 0L) {
                    Toast.makeText(getApplicationContext(), "Can not divide by zero", Toast.LENGTH_SHORT).show()
                    resultOutput.text.toString()[0].toLong()
                } else {
                    a / b
                }
                else -> resultOutput.text.toString()[0].toLong()
            }

            resultOutput.text = result.toString()
        } ?: run {
            Toast.makeText(getApplicationContext(), "Chose operation", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStop() {
        super.onStop()
        savePrefs()
    }

    private fun savePrefs() {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("firstOperandInput", firstOperandInput.text.toString())
            putString("secondOperandInput", secondOperandInput.text.toString())
            putString("resultOutput", resultOutput.text.toString())
            putInt("checkedRadioButtonId", radioButtonGroup.checkedRadioButtonId)
            commit()
        }
    }

    private fun loadPrefs() {
        val data = getPreferences(Context.MODE_PRIVATE)
        firstOperandInput.setText(data.getString("firstOperandInput", ""))
        secondOperandInput.setText(data.getString("secondOperandInput", ""))
        resultOutput.setText(data.getString("resultOutput", ""))
        radioButtonGroup.check(data.getInt("checkedRadioButtonId", 1))
    }
}
