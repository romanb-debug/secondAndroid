package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.core.graphics.toColorInt

class ActivityB : ComponentActivity() {

    private lateinit var root: View

    companion object {
        private const val KEY_BG = "key_bg"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activityb)
        root = findViewById(R.id.rootB)
        val buttonOpenC: Button = findViewById(R.id.buttonOpenC)

        if (savedInstanceState == null) {
            intent?.getStringExtra("color")?.let { hex ->
                if (isValidHex(hex)) setBackgroundHex(hex)
            }
        } else {
            savedInstanceState.getString(KEY_BG)?.let { setBackgroundHex(it) }
        }

        buttonOpenC.setOnClickListener {
            startActivity(android.content.Intent(this, ActivityC::class.java))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        (root.tag as? String)?.let { outState.putString(KEY_BG, it) }
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