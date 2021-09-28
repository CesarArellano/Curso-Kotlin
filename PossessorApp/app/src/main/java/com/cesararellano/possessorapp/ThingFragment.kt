package com.cesararellano.possessorapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

class ThingFragment : Fragment() {

    private lateinit var thing: ThingDataClass
    private lateinit var nameField: EditText
    private lateinit var priceField: EditText
    private lateinit var serialNumberField: EditText
    private lateinit var dateLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        thing = ThingDataClass()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.thing_fragment, container, false)
        nameField = view.findViewById(R.id.nameEditText)
        priceField = view.findViewById(R.id.priceEditText)
        serialNumberField = view.findViewById(R.id.serialNumberEditText)
        dateLabel = view.findViewById(R.id.dateLabel)
        return view
    }
}