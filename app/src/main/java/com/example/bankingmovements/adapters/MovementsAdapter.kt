package com.example.bankingmovements.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bankingmovements.R
import com.example.bankingmovements.data.Movement
import com.example.bankingmovements.databinding.MovementRecycleViewBinding

class MovementsAdapter (private var dataSet: List<Movement>) : RecyclerView.Adapter<MovementsAdapter.MovementViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovementViewHolder {
        val binding = MovementRecycleViewBinding.inflate(LayoutInflater.from(parent.context))
        return MovementViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovementViewHolder, position: Int) {
        holder.render(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

    fun updateData(dataSet: List<Movement>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }

    class MovementViewHolder(private val binding: MovementRecycleViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun render(movement: Movement) {
            binding.quantity.text = itemView.context.getString(R.string.quantity, String.format("%.2f", movement.quantity))
            binding.date.text = movement.date
        }
    }
}