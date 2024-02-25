package com.example.pizzalab.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzalab.Pizza
import com.example.pizzalab.R

class PizzaAdapter(private var pizzaList: List<Pizza>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder>() {

        fun updateList(list: List<Pizza>) {
            this.pizzaList = list
        }

    inner class PizzaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewPizza: ImageView = itemView.findViewById(R.id.imageViewPizza)
        val textViewPizzaName: TextView = itemView.findViewById(R.id.textViewPizzaName)
        val textViewPizzaDescription: TextView = itemView.findViewById(R.id.textViewPizzaDescription)



        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(pizzaList[position])
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pizza, parent, false)
        return PizzaViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        val currentItem = pizzaList[position]


        holder.textViewPizzaName.text = currentItem.name
        holder.textViewPizzaDescription.text = currentItem.description


        holder.imageViewPizza.setImageResource(currentItem.imageResourceId)
    }


    override fun getItemCount() = pizzaList.size


    interface OnItemClickListener {
        fun onItemClick(pizza: Pizza)
    }

}
