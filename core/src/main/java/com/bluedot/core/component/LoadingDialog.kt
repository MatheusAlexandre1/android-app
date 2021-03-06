package com.bluedot.core.component

import android.app.Dialog
import android.view.LayoutInflater
import androidx.core.content.res.ResourcesCompat
import com.bluedot.core.base.BaseDialogFragment
import com.bluedot.core.databinding.DialogLoadingBinding

class LoadingDialog : BaseDialogFragment<DialogLoadingBinding>() {

    override fun getViewBinding() = DialogLoadingBinding.inflate(LayoutInflater.from(context))

    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        dialog.window?.setBackgroundDrawable(
            ResourcesCompat.getDrawable(
                resources,
                android.R.color.transparent,
                null
            )
        )
    }
}