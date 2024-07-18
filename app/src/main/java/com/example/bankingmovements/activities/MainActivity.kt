package com.example.bankingmovements.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bankingmovements.adapters.MovementsAdapter
import com.example.bankingmovements.data.Movement
import com.example.bankingmovements.data.MovementDAO
import com.example.bankingmovements.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: MovementsAdapter
    lateinit var movementDAO: MovementDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movementDAO = MovementDAO(this)
        val movements = movementDAO.findAll()

        adapter = MovementsAdapter(movements)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 1)

        binding.createButton.setOnClickListener() {
            navigateToCreateNewMovementPage()
        }
        setBalance(movements)
    }

    private fun setBalance(movements: List<Movement>) {
        var balance = 0.0
        movements.forEach {
            if (it.positive) {
                balance += it.quantity
            } else {
                balance -= it.quantity
            }
        }
            binding.balance.text = String.format("%.2f $", balance)
    }

    override fun onResume() {
        super.onResume()
        val newMovements = movementDAO.findAll()
        adapter.updateData(newMovements)
        setBalance(newMovements)
    }

    private fun navigateToCreateNewMovementPage() {
        val intent = Intent(this, AddNewMovementActivity::class.java)
        startActivity(intent)
    }
}