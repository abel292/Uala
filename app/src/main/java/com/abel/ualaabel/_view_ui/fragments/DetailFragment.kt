package com.abel.ualaabel._view_ui.fragments

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.recyclerview.widget.LinearLayoutManager
import com.abel.ualaabel.R
import com.abel.ualaabel._model.remote.meals.Meal
import com.abel.ualaabel._view_ui.adapters.base.IngredientesAdapter
import com.abel.ualaabel._view_ui.base.BaseFragment
import com.abel.ualaabel.utils.CustomsConstantes
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : BaseFragment() {

    lateinit var meal: Meal
    lateinit var ingredientesAdapter: IngredientesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_detail, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArgument()
        initObservables()
        init()
        initListeners()
    }

    override fun initObservables() {

    }

    override fun init() {
    }

    override fun initListeners() {

    }

    private fun initArgument() {

        val argumentProducto = arguments
        meal =
            argumentProducto?.getSerializable(CustomsConstantes.EXTRAS_VIEW_PRODUCT) as Meal
        try {
            cargarDatosAVista()
        } catch (e: Exception) {
            showSnackBar(getString(R.string.error_al_cargar_person))
        }
    }

    private fun cargarDatosAVista() {
        textViewNombre.text = meal.strMeal
        textViewInstrucciones.text = meal.strInstructions

        Glide.with(requireContext())
            .load(meal.strMealThumb)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .centerCrop()
            .into(imageViewImageProducto)

        val listIngredientes = meal.generateListIngredientes()
        notifyRecyclerViewItems(listIngredientes)

        loadVideoTwo(meal.strYoutube)

    }

    private fun notifyRecyclerViewItems(list: List<String>) {

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerViewIngredientes.layoutManager = layoutManager
        ingredientesAdapter = IngredientesAdapter(requireContext(), list, recyclerViewIngredientes)
        recyclerViewIngredientes.adapter = ingredientesAdapter
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun loadVideoTwo(url: String) {

        videoView.setWebChromeClient(object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                activity!!.title = "Loading..."
                activity!!.setProgress(progress * 100)
                if (progress == 100) activity!!.title = "Ready"
            }
        })

        videoView.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
                Log.d("Failure Url :", failingUrl)
            }

            override fun onReceivedSslError(
                view: WebView,
                handler: SslErrorHandler,
                error: SslError
            ) {
                Log.d("Ssl Error:", handler.toString() + "error:" + error)
                handler.proceed()
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }
        videoView.settings.javaScriptEnabled = true
        videoView.settings.loadWithOverviewMode = true
        videoView.settings.useWideViewPort = true
        videoView.settings.domStorageEnabled = true
        videoView.loadUrl(url
        )
    }

}