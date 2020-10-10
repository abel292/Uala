package com.abel.ualaabel._view_ui.adapters.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.abel.ualaabel.R
import com.abel.ualaabel._model.remote.ryck_y_morti.Personaje
import com.bumptech.glide.Glide


class PersonajeAdapter(var context: Context, lista: List<Personaje>, var recyclerView: RecyclerView) :
    BaseAdapterRecycler<Personaje>() {

    lateinit var listener: OnListenerItemRecyclerView<Personaje>
    var list = lista

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductoViewHolder(
            inflater,
            parent,
            context,
            listener
        )
    }

    override fun onBindViewHolder(holder: HolderBase<Personaje>, position: Int) {
        val producto: Personaje = list[position]
        holder.bind(producto, position)
    }

    override fun getItemCount(): Int = list.size

    //todo Holder
    class ProductoViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        var context: Context,
        var listener: OnListenerItemRecyclerView<Personaje>
    ) :
        HolderBase<Personaje>(
            inflater, parent, R.layout.item_personaje

        ) {

        private var textViewNombreProducto: TextView? = null
        private var imageViewProducto: ImageView? = null


        init {
            textViewNombreProducto = itemView.findViewById(R.id.textViewNamePersonaje)
            imageViewProducto = itemView.findViewById(R.id.imageViewPersonaje)
        }

        override fun bind(objeto: Personaje, position: Int) {
            textViewNombreProducto?.text = objeto.name

            imageViewProducto?.let {
                Glide.with(context)
                    .load(objeto.image)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .override(200, 200).centerCrop().into(it)
            }
            itemView.setOnClickListener {
                listener.onClickItem(objeto, position)
            }

        }

    }

    fun filterList(filterdNames: List<Personaje>) {
        this.list = filterdNames
        recyclerView.scrollBy(0, 0)
        recyclerView.visibility = View.VISIBLE
        notifyDataSetChanged()

    }

}