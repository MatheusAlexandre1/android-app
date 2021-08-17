package com.bluedot.core.component

import android.app.Dialog
import android.view.LayoutInflater
import com.bluedot.core.base.BaseDialogFragment
import com.bluedot.core.databinding.DialogErrorBinding

const val ARG_DESCRIPTION = "ARG_DESCRIPTION"

class ErrorDialog : BaseDialogFragment<DialogErrorBinding>() {

    override fun getViewBinding() = DialogErrorBinding.inflate(LayoutInflater.from(context))

    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        arguments?.apply {
            binding.tvDescription.text = getString(ARG_DESCRIPTION)
        }
        binding.btnUnderstood.setOnClickListener { dismiss() }
    }
}