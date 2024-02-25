package com.example.pizzalab

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzalab.adapters.PizzaAdapter

class MainActivity : AppCompatActivity(), PizzaAdapter.OnItemClickListener {

    private lateinit var verticalRecyclerView: RecyclerView
    private lateinit var pizzaAdapter: PizzaAdapter
    private lateinit var pizzaList: ArrayList<Pizza>
    private lateinit var filteredPizzaList: ArrayList<Pizza>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        verticalRecyclerView = findViewById(R.id.verticalRecyclerView)
        verticalRecyclerView.layoutManager = LinearLayoutManager(this)
        verticalRecyclerView.addItemDecoration(SpaceItemDecoration(10))
        pizzaList = ArrayList()
        pizzaList.add(
            Pizza(
                R.drawable.pizzazhulen,
                "Пицца Жюльен",
                "Цыпленок, шампиньоны, соус сливочный с грибами, красный лук, чеснок, моцарелла, смесь сыров чеддер и пармезан, фирменный соус альфредо",
                "3.900 тг"
            )
        )
        pizzaList.add(
            Pizza(
                R.drawable.pizzanaruto,
                "Наруто Пицца",
                "Куриные кусочки, соус терияки, ананасы, моцарелла, фирменный соус альфредо",
                "3.900 тг"
            )
        )
        pizzaList.add(
            Pizza(
                R.drawable.pizzacheesechicken,
                "Сырный цыпленок",
                "Цыпленок, моцарелла, сыры чеддер и пармезан, сырный соус, томаты, соус альфредо, чеснок",
                "4.400 тг"
            )
        )
        pizzaList.add(
            Pizza(
                R.drawable.pizzahamcheese,
                "Ветчина и сыр",
                "Ветчина из цыпленка, моцарелла, соус альфредо",
                "3.100 тг"
            )
        )
        pizzaList.add(
            Pizza(
                R.drawable.pizzapeppfresh,
                "Пепперони фреш",
                "Пепперони из цыпленка, увеличенная порция моцареллы, томаты, томатный соус",
                "3.000 тг"
            )
        )
        pizzaAdapter = PizzaAdapter(pizzaList, this)
        verticalRecyclerView.adapter = pizzaAdapter


        filteredPizzaList = ArrayList()
        filteredPizzaList.addAll(pizzaList)
        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val filteredPizzas = pizzaList.filter {
                    it.name.contains(
                        query,
                        ignoreCase = true
                    ) || it.description.contains(query, ignoreCase = true)
                }
                if (filteredPizzas.isNotEmpty()) {
                    filterPizzaList(filteredPizzas)
                } else {
                    Toast.makeText(this@MainActivity, "К сожалению, пицца не найдена. Попробуйте снова", Toast.LENGTH_LONG).show()
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterPizzaList(pizzaList.filter {
                    it.name.contains(
                        newText,
                        ignoreCase = true
                    ) || it.description.contains(newText, ignoreCase = true)
                })
                return true
            }
        })
    }

    private fun filterPizzaList(filteredPizzas: List<Pizza>) {
        filteredPizzaList.clear()
        filteredPizzaList.addAll(filteredPizzas)
        pizzaAdapter.updateList(filteredPizzas)
        pizzaAdapter.notifyDataSetChanged()
    }

    override fun onItemClick(pizza: Pizza) {
        val intent = Intent(this, PizzaDetailsActivity::class.java)
        intent.putExtra("pizza", pizza)
        startActivity(intent)
    }

    class SpaceItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect, view: View,
            parent: RecyclerView, state: RecyclerView.State
        ) {
            outRect.left = space
            outRect.right = space
        }
    }
}