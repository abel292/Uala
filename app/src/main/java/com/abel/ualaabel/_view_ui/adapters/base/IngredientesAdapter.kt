package com.abel.ualaabel._view_ui.adapters.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abel.ualaabel.R
import com.bumptech.glide.Glide


class IngredientesAdapter(
    var context: Context,
    lista: List<String>,
    var recyclerView: RecyclerView
) :
    BaseAdapterRecycler<String>() {

    var list = lista

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductoViewHolder(
            inflater,
            parent,
            context)
    }

    override fun onBindViewHolder(holder: HolderBase<String>, position: Int) {
        val producto: String = list[position]
        holder.bind(producto, position)
    }

    override fun getItemCount(): Int = list.size

    //todo Holder
    class ProductoViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        var context: Context,
    ) :
        HolderBase<String>(
            inflater, parent, R.layout.item_ingrediente

        ) {

        private var textViewIngredienteNombre: TextView? = null
        private var imageViewProducto: ImageView? = null


        init {
            textViewIngredienteNombre = itemView.findViewById(R.id.textViewIngredienteNombre)
            //imageViewProducto = itemView.findViewById(R.id.imageViewProducto)
        }

        override fun bind(objeto: String, position: Int) {
            textViewIngredienteNombre?.text = objeto

            /* imageViewProducto?.let {
                 Glide.with(context)
                     .load(objeto.urlImagen)
                     .placeholder(R.drawable.ic_scan)
                     .error(R.drawable.ic_scan)
                     .override(200, 200).centerCrop().into(it)
             }*/
            itemView.setOnClickListener {
            }

        }

    }

    fun filterList(filterdNames: List<String>) {
        this.list = filterdNames
        recyclerView.scrollBy(0, 0)
        recyclerView.visibility = View.VISIBLE
        notifyDataSetChanged()

    }

}