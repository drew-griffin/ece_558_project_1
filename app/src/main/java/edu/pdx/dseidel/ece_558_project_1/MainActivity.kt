/**
 * @brief This code implements a basic calculator on Android
 * This code performs ADD, SUBTRACT, MULTIPLY, AND DIVIDE for two operands
 * This code performs PERCENT OF, and POSITIVE SQUARE ROOT of the first operand
 *
 * @param operand1 input string for operand 1 which the user inputs on keypad. signed decimal
 * @param operand2 input string for operand 2 which the user inputs on keypad. signed decimal
 * @param 7 touch buttons (user pressed on app)
 *
 * @author Drew Seidel (dseidel@pdx.edu)
 * @date   02-10-2022
 * @version 1.0 - Basic Calculator Functionality Implemented
 * @note reference to some of the concepts and methodologies set forth by Roy Kravitz set
 * forth in 'Ohms Law Calculator R2.0' released along with this project
 */

package edu.pdx.dseidel.ece_558_project_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.util.Log
import android.widget.Toast
import edu.pdx.dseidel.ece_558_project_1.databinding.ActivityMainBinding
import kotlin.math.abs


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* Global Variables */
        val operationString : Array<String> = arrayOf(getString(R.string.plus_label), getString(R.string.minus_label),
        getString(R.string.multiply_label), getString(R.string.divide_label), getString(R.string.percent_label), getString(R.string.square_root_label))
        var currentOperation : Int = 0 //{ADD, SUBTRACT, MULTIPLY, DIVIDE, PERCENT, SQUARE_ROOT} -> {0, 1, 2, 3, 4, 5}
        var operand1String : String
        var operand2String  : String
        var valid : Boolean = false
        var extValid : Boolean = false
        var error : Boolean = false
        var result : Double = 0.0

        /**
         * @function updateState
         *
         * @brief function takes in global variables, updates their state,
         * and displays appropriate messages to the android app.
         * State is updated by the buttons. Each corresponding button
         * updates currentOperation (ADD, SUBTRACT, MULTIPLY, DIVIDE, PERCENT, SQUARE_ROOT)
         * according to the button pressed.
         *
         * @note this function takes in the above globals and updates the user view of the app accordingly.
         * It acts as the main processing brains of the app
         */
        fun updateState(){
            operand1String = binding.operand1.text.toString().trim()
            operand2String = binding.operand2.text.toString().trim()
            valid = (operand1String.isNotBlank() && operand1String.isNotEmpty() && operand2String.isNotBlank() && operand2String.isNotEmpty())
            extValid = (operand1String.isNotBlank() && operand1String.isNotEmpty())
            error = false

            /* Processing for both operands valid and is ADD, SUBTRACT, MULTIPLY or DIVIDE */
            if (valid && currentOperation >=0 && currentOperation <= 3){
                binding.operationDisplay.text = operationString[currentOperation]
                when(currentOperation) {
                    0 -> result = operand1String.toDouble() + operand2String.toDouble()
                    1 -> result = operand1String.toDouble() - operand2String.toDouble()
                    2 -> result = operand1String.toDouble() * operand2String.toDouble()
                    3 -> if (operand2String.toDouble() != 0.0) {
                            result = operand1String.toDouble() / operand2String.toDouble()
                        }
                        else { //divide by zero error
                             Toast.makeText(this, getString(R.string.divide_by_zero), Toast.LENGTH_SHORT).show()
                             error = true
                        }
                }
                if(error)
                    binding.finalResult.text = ""
                else
                    binding.finalResult.text = result.toString()
            }
            /* Processing for one operand is present and operation is either PERCENT or POSITIVE SQUARE ROOT*/
            else if(extValid && currentOperation >= 4 && currentOperation <= 5)
            {
                binding.operationDisplay.text = operationString[currentOperation]
                when(currentOperation)
                {
                    4 -> result = operand1String.toDouble() / 100.0
                    5 -> result = Math.sqrt(Math.abs(operand1String.toDouble()))
                    else -> {
                        Toast.makeText(this, getString(R.string.invalid_operation), Toast.LENGTH_SHORT).show()
                        error = true
                    }

                }
                if(error)
                    binding.finalResult.text = ""
                else
                    binding.finalResult.text = result.toString()
            }
            /* Processing for neither operands have numbers (they are both empty */
            else{
                Toast.makeText(this, getString(R.string.invalid_operation), Toast.LENGTH_SHORT).show()
                binding.finalResult.text = ""
            }
        }

        /* Button on click listener functions */
        //clear button is tapped
        binding.clearButton.setOnClickListener {

            //set default messages and clear result
            binding.operand1.setText("")
            binding.operand2.setText("")
            binding.operand1.setHint(R.string.op1_label)
            binding.operand2.setHint(R.string.op2_label)
            binding.finalResult.text = ""
            binding.operationDisplay.text = ""
        }
        //plus button is tapped
        binding.plusButton.setOnClickListener {
            currentOperation = 0
            updateState()
        }
        //minus button is tapped
        binding.minusButton.setOnClickListener {
            currentOperation = 1
            updateState()
        }
        //multiply button is tapped
        binding.multiplyButton.setOnClickListener {
           currentOperation = 2
            updateState()
        }
        //divide button is tapped
        binding.divideButton.setOnClickListener{
            currentOperation = 3
            updateState()
        }
        //percent button is tapped
        binding.percentButton.setOnClickListener {
            currentOperation = 4
            updateState()
        }
        //square root button is tapped
        binding.squareRootButton.setOnClickListener {
            currentOperation = 5
            updateState()
        }
    }
}