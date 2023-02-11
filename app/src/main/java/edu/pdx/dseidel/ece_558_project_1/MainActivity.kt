package edu.pdx.dseidel.ece_558_project_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import edu.pdx.dseidel.ece_558_project_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val operand1String = binding.operand1.text.toString().trim()
        val operand2String = binding.operand2.text.toString().trim()

        val valid : Boolean = (operand1String.isNotBlank() && operand1String.isNotEmpty() && operand2String.isNotBlank() && operand2String.isNotEmpty())


        var result : Double


        /* Button on click listener functions */
        binding.clearButton.setOnClickListener {

        }
        binding.plusButton.setOnClickListener {
            if (valid){
                result = operand1String.toDouble() + operand2String.toDouble()
                binding.finalResult.text = result.toString()
            }

        }
        binding.minusButton.setOnClickListener {
            if (valid){
                result = operand1String.toDouble() - operand2String.toDouble()
                binding.finalResult.text = result.toString()
            }
        }
        binding.multiplyButton.setOnClickListener {
            if (valid){
                result = operand1String.toDouble() * operand2String.toDouble()
                binding.finalResult.text = result.toString()
            }
        }
        binding.divideButton.setOnClickListener{
            if (valid && operand2String.toDouble() != 0.0){
                result = operand1String.toDouble() * operand2String.toDouble()
                binding.finalResult.text = result.toString()
            }
        }
        binding.percentButton.setOnClickListener {

        }
        binding.squareRootButton.setOnClickListener {

        }

    }





}