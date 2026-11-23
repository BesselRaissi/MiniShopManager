package com.example.minishopmanager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        val btnNext = findViewById<Button>(R.id.btnNext)
        btnNext.setOnClickListener {
            Toast.makeText(this, "Bonjour Raissi Bessel !", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        val listView = findViewById<ListView>(R.id.listViewProduits)

        val products = listOf(
            Product("Téléphone", R.drawable.ic_phone),
            Product("Casque Bluetooth", R.drawable.ic_headphones),
            Product("Montre connectée", R.drawable.ic_watch),
            Product("Chargeur USB", R.drawable.ic_usb),
            Product("Accessoires", R.drawable.ic_accessories),
            Product("Cosmétiques", R.drawable.ic_cosmetics)
        )
        listView.adapter = ProductAdapter(this, products)

        listView.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(this, "Produit sélectionné : ${products[position].name}", Toast.LENGTH_SHORT).show()
        }

        Log.d("LIFECYCLE", "onCreate appelé")
    }

    override fun onStart() { super.onStart(); Log.d("LIFECYCLE", "onStart appelé") }
    override fun onResume() { super.onResume(); Log.d("LIFECYCLE", "onResume appelé") }
    override fun onPause() { super.onPause(); Log.d("LIFECYCLE", "onPause appelé") }
    override fun onStop() { super.onStop(); Log.d("LIFECYCLE", "onStop appelé") }
    override fun onDestroy() { super.onDestroy(); Log.d("LIFECYCLE", "onDestroy appelé") }
}