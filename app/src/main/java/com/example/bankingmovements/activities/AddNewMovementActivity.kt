package com.example.bankingmovements.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bankingmovements.R
import com.example.bankingmovements.data.Movement
import com.example.bankingmovements.data.MovementDAO
import com.example.bankingmovements.databinding.ActivityAddNewMovementBinding
import com.example.bankingmovements.databinding.ActivityMainBinding
import java.lang.StringBuilder

class AddNewMovementActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddNewMovementBinding
    lateinit var movementDAO: MovementDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewMovementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movementDAO = MovementDAO(this)

        binding.saveButton.setOnClickListener() {
            val amount = binding.textInputQuantity.text.toString().toDouble()

            val year = binding.datePicker1.year.toString()
            val month = binding.datePicker1.month.toString()

            val dayOfMonth = binding.datePicker1.dayOfMonth.toString()
            val date = "$year-$month-$dayOfMonth"

            val movement = Movement(-1, amount, date)

            movementDAO.insert(movement)
            Toast.makeText(this, "Movement is added",Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}