package com.cesararellano.fragmentsexample

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import java.lang.ClassCastException

const val TAG = "ModificadorFragment"

class ModificadorFragment : Fragment(), SeekBar.OnSeekBarChangeListener {

    private lateinit var textField: EditText
    private var callbackActivity : OnModifiedTextListener? = null
    private var textSize = 10

    interface OnModifiedTextListener {
        fun onPressedButton( text: String, textSize: Int )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            callbackActivity = context as OnModifiedTextListener
        } catch (e: ClassCastException) {
            throw  ClassCastException("$context debe implementar la interfaz OnModifiedTextListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.modificador_fragment, container, false) // Control al Fragment Manager
        val seekBar : SeekBar = view.findViewById( R.id.seekBar )

        val modifiedTextButton = view.findViewById<Button>(R.id.modifiedTextButton)
        textField = view.findViewById(R.id.textToBeModified)
        modifiedTextButton.setOnClickListener { button: View ->  onPressedModifiedTextButton(button) }
        seekBar.setOnSeekBarChangeListener(this)
        return view
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onPressedModifiedTextButton(button: View) {
        callbackActivity?.onPressedButton(textField.text.toString(), textSize)
    }

    override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
        Log.d(TAG, "SeekBar position: $progress")
        textSize = progress
    }


    override fun onStartTrackingTouch(seekbar: SeekBar?) {
        Log.d(TAG, "User starts to touch the seekBar")
    }

    override fun onStopTrackingTouch(seekbar: SeekBar?) {
        Log.d(TAG, "Finish touching the seekBar")
    }
}