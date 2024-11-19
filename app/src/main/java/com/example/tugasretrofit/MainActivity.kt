package com.example.tugasretrofit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tugasretrofit.database.FavListDatabase
import com.example.tugasretrofit.database.FavoritList
import com.example.tugasretrofit.databinding.ActivityMainBinding
import com.example.tugasretrofit.model.Vendors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

//    memudahkan pemanggilan elemen UI
    private lateinit var binding: ActivityMainBinding
//    untuk menyimpan list Favorit
    private lateinit var database: FavListDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the Room database
        database = FavListDatabase.getDatabase(applicationContext)!!

        with(binding) {
            val navController = findNavController(R.id.nav_host_fragment)
            bottomNavigationView.setupWithNavController(navController)
        }

        // Example usage: Add a vendor to favorites
        addVendorToFavorites(Vendors("2024-11-19", "Example Title", "Category", "Description"))
    }

//    menambahkan data vendor ke database favorite
    private fun addVendorToFavorites(vendor: Vendors) {
        val favoriteVendor = FavoritList(
            title = vendor.title,
            description = vendor.description,
            category = vendor.category,
            date = vendor.date
        )

        // Insert data into the database in background thread
        lifecycleScope.launch {
            // Use withContext(Dispatchers.IO) to move database operation to background thread
            withContext(Dispatchers.IO) {
                database.listDao()?.insert(favoriteVendor)
            }

            // Show success message in the main thread after operation completes
            Toast.makeText(this@MainActivity, "${vendor.title} added to favorites!", Toast.LENGTH_SHORT).show()
        }
    }
}
