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

        Log.d("LIFECYCLE", "onCreate appelé")
    }

    override fun onStart() { super.onStart(); Log.d("LIFECYCLE", "onStart appelé") }
    override fun onResume() { super.onResume(); Log.d("LIFECYCLE", "onResume appelé") }
    override fun onPause() { super.onPause(); Log.d("LIFECYCLE", "onPause appelé") }
    override fun onStop() { super.onStop(); Log.d("LIFECYCLE", "onStop appelé") }
    override fun onDestroy() { super.onDestroy(); Log.d("LIFECYCLE", "onDestroy appelé") }
}