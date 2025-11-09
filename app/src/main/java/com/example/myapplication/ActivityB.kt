package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.core.graphics.toColorInt
import com.example.myapplication.Constants.COLOR

class ActivityB : ComponentActivity() {

    private lateinit var root: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activityb)
        root = findViewById(R.id.rootB)
        val buttonOpenC: Button = findViewById(R.id.buttonOpenC)

        if (savedInstanceState == null) {
            intent?.getStringExtra(COLOR)?.let { hex ->
                if (isValidHex(hex)) setBackgroundHex(hex)
            }
        }

        buttonOpenC.setOnClickListener {
            startActivity(android.content.Intent(this, ActivityC::class.java))
        }
    }

    private fun setBackgroundHex(hex: String) {
        try {
            root.setBackgroundColor(hex.toColorInt())
            root.tag = hex
        } catch (_: Exception) {}
    }

    private fun isValidHex(s: String?): Boolean {
        if (s == null) return false
        return s.matches(Regex("^#([A-Fa-f0-9]{6})$"))
    }
}