package com.abel.ualaabel._view_ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abel.ualaabel.R
import com.abel.ualaabel._model.remote.ryck_y_morti.Personaje
import com.abel.ualaabel._view_ui.base.BaseFragment
import com.abel.ualaabel.utils.CustomsConstantes
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : BaseFragment() {

    lateinit var personaje: Personaje
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
    }

    private fun initArgument() {

        val argumentProducto = arguments
        personaje =
            argumentProducto?.getSerializable(CustomsConstantes.EXTRAS_VIEW_PRODUCT) as Personaje
        try {
            cargarDatosAVista()
        } catch (e: Exception) {
            showSnackBar(getString(R.string.error_al_cargar_person))
        }
    }

    private fun cargarDatosAVista() {
        textViewNombre.text = personaje.name
        textViewCodigo.text = personaje.created
        textViewCantidad.text = personaje.gender.toString()
        textViewReserva.text = personaje.species
        textViewCompra.text = personaje.url
        textViewVenta.text = personaje.status
        textViewGanancia.text = personaje.type


        Glide.with(requireContext())
            .load(personaje.image)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .centerCrop()
            .into(imageViewImageProducto)
    }

}