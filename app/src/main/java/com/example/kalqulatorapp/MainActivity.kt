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
        display.showSoftInputOnFocus = false
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

        binding.buttonZero.setOnClickListener { updateDisplayOnButtonClick("0") }

        binding.buttonOne.setOnClickListener{updateDisplayOnButtonClick("1")}

        binding.buttonTwo.setOnClickListener { updateDisplayOnButtonClick("2")}

        binding.buttonThree.setOnClickListener { updateDisplayOnButtonClick("3") }

        binding.buttonFour.setOnClickListener { updateDisplayOnButtonClick("4") }
        
        binding.buttonFive.setOnClickListener { updateDisplayOnButtonClick("5") }
        
        binding.buttonSix.setOnClickListener { updateDisplayOnButtonClick("6") }
        
        binding.buttonSeven.setOnClickListener { updateDisplayOnButtonClick("7") }
        
        binding.buttonEight.setOnClickListener { updateDisplayOnButtonClick("8") }
        
        binding.buttonNine.setOnClickListener { updateDisplayOnButtonClick("9") }
        
        binding.buttonDot.setOnClickListener { updateDisplayOnButtonClick(".") }
        
        binding.buttonAdd.setOnClickListener { updateDisplayOnButtonClick("+") }
        
        binding.buttonSubtract.setOnClickListener { updateDisplayOnButtonClick("-") }
        
        binding.buttonMultiply.setOnClickListener { updateDisplayOnButtonClick(getString(R.string.times)) }

        binding.buttonDivide.setOnClickListener { updateDisplayOnButtonClick(getString(R.string.divide)) }

        binding.buttonEval.setOnClickListener{
            val expression = display.text.toString()
            val evaluatedExpression = Expression(expression).calculate()
            var evaluatedExpressionString = evaluatedExpression.toString()
//            if (evaluatedExpressionString[evaluatedExpressionString.lastIndex].equals("0")  &&
//                evaluatedExpressionString[evaluatedExpressionString.lastIndex-1].equals(".") ){
//                evaluatedExpressionString = evaluatedExpressionString.subSequence(0, evaluatedExpressionString.lastIndex-2) as String
//            }
            evaluatedDisplay.text = evaluatedExpressionString

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
        stringDisplay.append(buttonStringValue)
        display.text = stringDisplay
    }
}

