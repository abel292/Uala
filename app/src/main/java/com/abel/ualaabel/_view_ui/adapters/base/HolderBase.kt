package com.abel.ualaabel._view_ui.adapters.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class HolderBase<T>(
    inflater: LayoutInflater,
    parent: ViewGroup,
    viewInflater: Int
) :
    RecyclerView.ViewHolder(
        inflater.inflate(
            viewInflater,
            parent,
            false
        )
    ) {

    abstract fun bind(objeto: T, position: Int)


}