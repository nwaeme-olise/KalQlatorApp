package com.example.kalqulatorapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.EditText
import android.widget.TextView
import com.example.kalqulatorapp.databinding.ActivityMainBinding
import org.mariuszgromada.math.mxparser.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var  display: EditText
    private lateinit var evaluatedDisplay: TextView
    private lateinit var stringDisplay: SpannableStringBuilder



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        display = binding.expressionEditText
        display.showSoftInputOnFocus = false    //Disable Android keyboard popup
        display.setText("")
        evaluatedDisplay = binding.evaluatedTextView
        stringDisplay = SpannableStringBuilder(display.text.toString())


        binding.buttonDelete.setOnClickListener{
            deleteTextInDisplay()
        }

        binding.buttonDelete.setOnLongClickListener{
            deleteAllTextInDisplay()
            true
        }

        binding.buttonZero.setOnClickListener { updateDisplayOnButtonClick(getString(R.string.zero)) }

        binding.buttonOne.setOnClickListener{ updateDisplayOnButtonClick(getString(R.string.one))}

        binding.buttonTwo.setOnClickListener { updateDisplayOnButtonClick(getString(R.string.two))}

        binding.buttonThree.setOnClickListener { updateDisplayOnButtonClick(getString(R.string.three)) }

        binding.buttonFour.setOnClickListener { updateDisplayOnButtonClick(getString(R.string.four)) }
        
        binding.buttonFive.setOnClickListener { updateDisplayOnButtonClick(getString(R.string.five)) }
        
        binding.buttonSix.setOnClickListener { updateDisplayOnButtonClick(getString(R.string.six)) }
        
        binding.buttonSeven.setOnClickListener { updateDisplayOnButtonClick(getString(R.string.seven)) }
        
        binding.buttonEight.setOnClickListener { updateDisplayOnButtonClick(getString(R.string.eight)) }
        
        binding.buttonNine.setOnClickListener { updateDisplayOnButtonClick(getString(R.string.nine)) }
        
        binding.buttonDot.setOnClickListener { updateDisplayOnButtonClick(getString(R.string.decimal_point)) }
        
        binding.buttonAdd.setOnClickListener { updateDisplayOnButtonClick(getString(R.string.plus)) }
        
        binding.buttonSubtract.setOnClickListener { updateDisplayOnButtonClick(getString(R.string.minus)) }
        
        binding.buttonMultiply.setOnClickListener { updateDisplayOnButtonClick(getString(R.string.times)) }

        binding.buttonDivide.setOnClickListener { updateDisplayOnButtonClick(getString(R.string.divide)) }

        binding.buttonEval.setOnClickListener{
            val expression = display.text.toString()    //Retrieve expression currently in the display
            val evaluatedExpression = Expression(expression).calculate()    //Create Math parser object, pass expression into class constructor and evaluate
            val evaluatedExpressionString = evaluatedExpression.toString()
            evaluatedDisplay.text = evaluatedExpressionString

            //If evaluated expression result is an integer, remove decimal part
            if ((evaluatedExpressionString.last().toString() == "0") &&
                (evaluatedExpressionString.secondToLast().toString() == ".")){
                evaluatedDisplay.text = evaluatedExpressionString.dropLast(2)
            }


        }

    }

    private fun deleteAllTextInDisplay() {
        if (stringDisplay.isEmpty()){
            return
        }
        stringDisplay.clear()
        display.text = stringDisplay
        evaluatedDisplay.text = ""
    }

    private fun deleteTextInDisplay() {
        if (stringDisplay.isEmpty()){
            return
        }
        stringDisplay =
            stringDisplay.subSequence(0, stringDisplay.lastIndex) as SpannableStringBuilder
        display.text = stringDisplay
        evaluatedDisplay.text = ""
    }


    private fun updateDisplayOnButtonClick(buttonStringValue: String){
        //Appends the passed in string value to the text already in the display
        stringDisplay.append(buttonStringValue)
        display.text = stringDisplay
    }

    private fun String.secondToLast(): Char{
        //Returns the second to the last character of a string
        val i = lastIndex - 1
        return get(i)
    }
}

