package com.bluedot.core.base

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.bluedot.core.component.ARG_DESCRIPTION
import com.bluedot.core.component.ErrorDialog
import com.bluedot.core.component.LoadingDialog
import com.bluedot.core.exception.handleException

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    lateinit var binding: T

    abstract fun getViewBinding(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
    }

    //This function has to be called inside the children to configure loading dialog
    fun setupLoading(viewModel: BaseViewModel) {
        val loadingDialog = LoadingDialog()
        viewModel.loading.observe(this) { loading ->
            if (loading) loadingDialog.show(supportFragmentManager, "LOADING_DIALOG")
            else loadingDialog.dismiss()
        }
    }

    fun handleError(error: Throwable) {
        showErrorDialog(handleException(this, error))
    }

    private fun showErrorDialog(@StringRes resId: Int) {
        val errorDialog = ErrorDialog()
        errorDialog.arguments = Bundle().apply { putString(ARG_DESCRIPTION, getString(resId)) }
        errorDialog.show(supportFragmentManager, "")
    }
}