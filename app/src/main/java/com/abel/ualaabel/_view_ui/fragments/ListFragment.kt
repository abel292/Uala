package com.abel.ualaabel._view_ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.abel.ualaabel.R
import com.abel.ualaabel._model.remote.ryck_y_morti.Personaje
import com.abel.ualaabel._view_model.ApiViewModel
import com.abel.ualaabel._view_ui.adapters.base.OnListenerItemRecyclerView
import com.abel.ualaabel._view_ui.adapters.base.PersonajeAdapter
import com.abel.ualaabel._view_ui.base.BaseFragment
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : BaseFragment(), OnListenerItemRecyclerView<Personaje> {

    val viewModel by lazy {
        ViewModelProviders.of(this).get(ApiViewModel::class.java)
    }
    lateinit var personajeAdapter: PersonajeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.fragment_list, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.list.observe(viewLifecycleOwner, {
            notifyRecyclerViewItems(it)
        })

        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            Log.e("LISTA:", "error $it")
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            Log.e("LISTA:", "loading...")
        })

        viewModel.getPlanes()

    }

    private fun notifyRecyclerViewItems(list: List<Personaje>) {
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        personajeAdapter = PersonajeAdapter(requireContext(), list, recyclerView)
        personajeAdapter.listener = this
        recyclerView.adapter = personajeAdapter
    }


    override fun onClickItem(objects: Personaje, position: Int) {
        fragmentView?.goToWithObjet(R.id.action_listFragment_to_detailFragment, objects)
    }
}