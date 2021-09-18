package com.cesararellano.appwithexpresso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

private const val TAG = "DataActivity"
class DataActivity : AppCompatActivity() {
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        textView = findViewById(R.id.activityDataTitle)
        val name = intent.getStringExtra("NAME").toString()
        textView.text = if( name.isEmpty() )  {
            "No mandó un nombre"
        } else {
            name
        }
        Log.d(TAG, name)
    }
}