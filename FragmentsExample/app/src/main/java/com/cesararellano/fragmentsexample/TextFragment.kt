package com.cesararellano.fragmentsexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class TextFragment : Fragment() {
    private lateinit var textField: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.text_fragment, container, false)
        textField = view.findViewById(R.id.fragmentText)
        // Inflate the layout for this fragment
        return view
    }

    fun changeText(text: String, size: Int ) {
        textField.text = text
        textField.textSize = size.toFloat()
    }
}