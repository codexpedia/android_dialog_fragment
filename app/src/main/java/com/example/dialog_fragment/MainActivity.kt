package com.example.dialog_fragment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity(), PopupModalFragment.DialogListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openDialog(v: View) {
        val fm = supportFragmentManager
        val popupModalFragment = PopupModalFragment.newInstance("Hello!", "How are you!")
        popupModalFragment.show(fm, "popup_modal_fragment")
        popupModalFragment.setDialogListener(this)
    }

    override fun onConfirm() {
        Toast.makeText(this, "Message Read!", Toast.LENGTH_SHORT).show()
    }
}
