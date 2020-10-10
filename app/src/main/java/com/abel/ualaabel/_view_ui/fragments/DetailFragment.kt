package com.abel.ualaabel._view_ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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

        videoView.setVideoPath(meal.strYoutube);
    }

    private fun notifyRecyclerViewItems(list: List<String>) {

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerViewIngredientes.layoutManager = layoutManager
        ingredientesAdapter = IngredientesAdapter(requireContext(), list, recyclerViewIngredientes)
        recyclerViewIngredientes.adapter = ingredientesAdapter
    }

}