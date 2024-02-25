package com.example.pizzalab

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PizzaDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_details)

        val pizza: Pizza? = intent.getParcelableExtra("pizza")

        pizza?.let {
            val imageViewPizza: ImageView = findViewById(R.id.imageViewPizza)
            val textViewName: TextView = findViewById(R.id.textViewName)
            val textViewDescription: TextView = findViewById(R.id.textViewDescription)
            val textViewPrice: TextView = findViewById(R.id.textViewPrice)
            imageViewPizza.setImageResource(it.imageResourceId)
            textViewName.text = it.name
            textViewDescription.text = it.description
            textViewPrice.text = "Добавить в корзину за ${it.price}"
        }
        val backImageView = findViewById<ImageView>(R.id.BackImageView)
        backImageView.setOnClickListener {
            onBackPressed()
        }
    }
}
