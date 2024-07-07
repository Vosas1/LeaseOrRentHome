package com.example.victorosaikhuwuomwan_comp304_lab2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SelectHomeTypeActivity : AppCompatActivity() {

    private lateinit var recyclerViewHomes: RecyclerView
    private lateinit var proceedToCheckoutButton: Button
    private lateinit var homeAdapter: HomeAdapter
    private var selectedHomeType: String? = null

    private val homes = listOf(
        HomeData("Apartment", listOf(R.drawable.apartment1), "Apartment at 1234 Park Ave", "$1200/month", "1234 Park Ave, Wonderland"),
        HomeData("Apartment", listOf(R.drawable.apartment2), "Apartment at 5678 Elm St", "$1300/month", "5678 Elm St, Wonderland"),
        HomeData("Detached Home", listOf(R.drawable.detached1), "Detached Home at 4321 Lonely St", "$2500/month", "4321 Lonely St, Riverside"),
        HomeData("Detached Home", listOf(R.drawable.detached2), "Detached Home at 8765 Quiet St", "$2700/month", "8765 Quiet St, Riverside"),
        HomeData("Semi-Detached Home", listOf(R.drawable.semi_detached1), "Semi-Detached Home at 6789 Sharing Wall St", "$1800/month", "6789 Sharing Wall St, New Town"),
        HomeData("Semi-Detached Home", listOf(R.drawable.semi_detached2), "Semi-Detached Home at 1234 Neighbor St", "$1900/month", "1234 Neighbor St, New Town"),
        HomeData("Condominium Apartment", listOf(R.drawable.condominium1), "Condominium at 2468 High Rise Blvd", "$2000/month", "2468 High Rise Blvd, Sky City"),
        HomeData("Condominium Apartment", listOf(R.drawable.condominium2), "Condominium at 1357 Skyline Blvd", "$2100/month", "1357 Skyline Blvd, Sky City"),
        HomeData("Town House", listOf(R.drawable.townhouse1), "Town House at 1357 Community Rd", "$2200/month", "1357 Community Rd, Old Town"),
        HomeData("Town House", listOf(R.drawable.townhouse2), "Town House at 2468 Village Rd", "$2300/month", "2468 Village Rd, Old Town")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_home_type)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Select Home Type Here ---->"

        recyclerViewHomes = findViewById(R.id.recyclerViewHomes)
        proceedToCheckoutButton = findViewById(R.id.buttonProceedToCheckout)

        recyclerViewHomes.layoutManager = LinearLayoutManager(this)
        homeAdapter = HomeAdapter(emptyList()) { home, isSelected ->
            handleHomeSelection(home, isSelected)
        }
        recyclerViewHomes.adapter = homeAdapter

        proceedToCheckoutButton.setOnClickListener {
            navigateToCheckout()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home_types, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        selectedHomeType = when (item.itemId) {
            R.id.apartment -> "Apartment"
            R.id.detached_home -> "Detached Home"
            R.id.semi_detached_home -> "Semi-Detached Home"
            R.id.condominium -> "Condominium Apartment"
            R.id.town_house -> "Town House"
            else -> return false
        }

        val filteredHomes = homes.filter { it.type == selectedHomeType }
        homeAdapter = HomeAdapter(filteredHomes) { home, isSelected ->
            handleHomeSelection(home, isSelected)
        }
        recyclerViewHomes.adapter = homeAdapter

        proceedToCheckoutButton.visibility = View.VISIBLE
        return true
    }

    private fun handleHomeSelection(home: HomeData, isSelected: Boolean) {

    }

    private fun navigateToCheckout() {
        val selectedHomes = homeAdapter.getSelectedHomes()
        val selectedHomesDescriptions = selectedHomes.map { "${it.address}, ${it.price}" }
        val intent = Intent(this, CheckoutActivity::class.java)
        intent.putStringArrayListExtra("selectedHomes", ArrayList(selectedHomesDescriptions))
        intent.putExtra("homeType", selectedHomeType)  // Pass the home type
        startActivity(intent)
    }
}


