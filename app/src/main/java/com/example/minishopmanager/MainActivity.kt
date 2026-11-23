package com.example.minishopmanager

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONObject

data class Produit(val nom: String, val prix: Int)

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
        val produits = lireProduitsDepuisJSON()
        val affichage = produits.map { "${it.nom} - ${it.prix} DT" }
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, affichage)

        listView.setOnItemClickListener { _, _, position, _ ->
            val produit = produits[position]

            val dbHelper = DBHelper(this)
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put("nom", produit.nom)
                put("prix", produit.prix)
            }
            db.insert("panier", null, values)
            db.close()

            Toast.makeText(this, "${produit.nom} ajouté au panier", Toast.LENGTH_SHORT).show()
        }

        Log.d("LIFECYCLE", "onCreate appelé")
    }

    private fun lireProduitsDepuisJSON(): ArrayList<Produit> {
        val produits = ArrayList<Produit>()
        val jsonString = assets.open("produits.json").bufferedReader().use { it.readText() }
        val jsonArray = JSONObject(jsonString).getJSONArray("produits")
        for (i in 0 until jsonArray.length()) {
            val p = jsonArray.getJSONObject(i)
            produits.add(Produit(p.getString("nom"), p.getInt("prix")))
        }
        return produits
    }

    override fun onStart() { super.onStart(); Log.d("LIFECYCLE", "onStart appelé") }
    override fun onResume() { super.onResume(); Log.d("LIFECYCLE", "onResume appelé") }
    override fun onPause() { super.onPause(); Log.d("LIFECYCLE", "onPause appelé") }
    override fun onStop() { super.onStop(); Log.d("LIFECYCLE", "onStop appelé") }
    override fun onDestroy() { super.onDestroy(); Log.d("LIFECYCLE", "onDestroy appelé") }
}