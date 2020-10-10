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
import com.abel.ualaabel._model.remote.meals.Meal
import com.bumptech.glide.Glide


class PersonajeAdapter(var context: Context, lista: List<Meal>, var recyclerView: RecyclerView) :
    BaseAdapterRecycler<Meal>() {

    lateinit var listener: OnListenerItemRecyclerView<Meal>
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

    override fun onBindViewHolder(holder: HolderBase<Meal>, position: Int) {
        val producto: Meal = list[position]
        holder.bind(producto, position)
    }

    override fun getItemCount(): Int = list.size

    //todo Holder
    class ProductoViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        var context: Context,
        var listener: OnListenerItemRecyclerView<Meal>
    ) :
        HolderBase<Meal>(
            inflater, parent, R.layout.item_personaje

        ) {

        private var textViewNombreProducto: TextView? = null
        private var textViewCategory: TextView? = null
        private var imageViewProducto: ImageView? = null


        init {
            textViewNombreProducto = itemView.findViewById(R.id.textViewNamePersonaje)
            textViewCategory = itemView.findViewById(R.id.textViewCategory)
            imageViewProducto = itemView.findViewById(R.id.imageViewPersonaje)
        }

        override fun bind(objeto: Meal, position: Int) {
            textViewNombreProducto?.text = objeto.strMeal
            textViewCategory?.text = objeto.strCategory
            imageViewProducto?.let {
                Glide.with(context)
                    .load(objeto.strMealThumb)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .override(200, 200).centerCrop().into(it)
            }
            itemView.setOnClickListener {
                listener.onClickItem(objeto, position)
            }

        }

    }

    fun filterList(filterdNames: List<Meal>) {
        this.list = filterdNames
        recyclerView.scrollBy(0, 0)
        recyclerView.visibility = View.VISIBLE
        notifyDataSetChanged()

    }

}