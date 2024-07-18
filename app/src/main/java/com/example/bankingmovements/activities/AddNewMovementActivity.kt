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
import java.time.LocalDate

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
            val positive = !binding.checkbox.isChecked

            val currentDate = LocalDate.now()

            val year = binding.datePicker1.year
            val month = binding.datePicker1.month + 1
            val dayOfMonth = binding.datePicker1.dayOfMonth

            val selectedDate = LocalDate.of(year, month, dayOfMonth)

            if (selectedDate.isBefore(currentDate)) {
                Toast.makeText(this, "This date cant be selected. Select another date.", Toast.LENGTH_LONG).show()
            }else {
                val date = "$year-$month-$dayOfMonth"

                val movement = Movement(-1, amount, date, positive)

                movementDAO.insert(movement)
                Toast.makeText(this, "Movement is added", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }
}