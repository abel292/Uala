package com.abel.ualaabel._view_ui.base

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.abel.ualaabel._model.remote.ryck_y_morti.Personaje
import com.abel.ualaabel.utils.CustomsConstantes
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {
    protected var mActivity: BaseActivity? = null
    protected var fragmentView: View? = null

    val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "OPEN FRAGMENT $TAG")
        super.onCreate(savedInstanceState)
        mActivity = activity as BaseActivity?
    }

    fun View.goToWithObjet(action: Int, personaje: Personaje) {
        val bundle = Bundle()
        bundle.putSerializable(CustomsConstantes.EXTRAS_VIEW_PRODUCT, personaje)
        findNavController().navigate(action, bundle)
    }

    fun showSnackBar(text: String) {
        mActivity?.let {
            Snackbar.make(it.findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG)
                .show()
        }
    }
}