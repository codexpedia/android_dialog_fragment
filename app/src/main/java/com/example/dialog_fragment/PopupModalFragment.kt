package com.example.dialog_fragment

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import kotlinx.android.synthetic.main.fragment_popup_dialog.*

class PopupModalFragment : DialogFragment() {

    private var dialogListener: DialogListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_popup_dialog, container)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = arguments.getString(TITLE, "")
        val message = arguments.getString(MESSAGE, "")

        if (title.isEmpty()) {
            tv_title.visibility = View.GONE
        } else {
            tv_title.text = title
        }

        if (message.isEmpty()) {
            dismiss()
            return
        }

        tv_message.text = message

        tv_confirm.setOnClickListener {
            dismiss()
            if (dialogListener != null) {
                dialogListener!!.onConfirm()
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    fun setDialogListener(listener: DialogListener) {
        this.dialogListener = listener
    }

    interface DialogListener {
        fun onConfirm()
    }

    companion object {
        val TITLE = "dialog_title"
        val MESSAGE = "dialog_message"

        fun newInstance(title: String, message: String): PopupModalFragment {
            val frag = PopupModalFragment()
            val args = Bundle()
            args.putString(TITLE, title)
            args.putString(MESSAGE, message)
            frag.arguments = args
            return frag
        }
    }
}
