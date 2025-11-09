package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import java.util.*
import com.example.myapplication.Constants.COLOR

class ActivityA : ComponentActivity() {

    private lateinit var root: View
    private lateinit var getColor: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitya)
        root = findViewById(R.id.rootA)
        getColor = findViewById(R.id.getColor)
        val buttonGen: Button = findViewById(R.id.buttonGenerate)
        val buttonOpenB: Button = findViewById(R.id.buttonOpenB)

        buttonGen.setOnClickListener {
            val hex = generateHexColor()
            getColor.setText(hex)
        }

        buttonOpenB.setOnClickListener {
            val intent = Intent(this, ActivityB::class.java)
            val colorText = getColor.text.toString().trim()
            if (isValidHex(colorText)) {
                intent.putExtra(COLOR, colorText)
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
            startActivity(intent)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    private fun generateHexColor(): String {
        val rnd = Random()
        val rgb = rnd.nextInt(0x1000000)
        return String.format("#%06X", rgb)
    }

    private fun isValidHex(s: String): Boolean {
        return s.matches(Regex("^#([A-Fa-f0-9]{6})$"))
    }
}