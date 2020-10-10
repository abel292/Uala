package com.abel.ualaabel._view_ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.abel.ualaabel.R
import com.abel.ualaabel._model.remote.meals.Meal
import com.abel.ualaabel._view_model.ApiViewModel
import com.abel.ualaabel._view_ui.adapters.base.OnListenerItemRecyclerView
import com.abel.ualaabel._view_ui.adapters.base.PersonajeAdapter
import com.abel.ualaabel._view_ui.base.BaseFragment
import com.abel.ualaabel.utils.extensiones.postDelayed
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : BaseFragment(), OnListenerItemRecyclerView<Meal> {

    val viewModel by lazy {
        ViewModelProviders.of(this).get(ApiViewModel::class.java)
    }
    lateinit var mealAdapter: PersonajeAdapter
    var meals: List<Meal>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.fragment_list, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservables()
        init()
        initListeners()

    }

    override fun initObservables() {
        viewModel.list.observe(viewLifecycleOwner, {
            try {
                notifyRecyclerViewItems(it)
            } catch (e: Exception) {
                Log.e(TAG, e.toString())
            }
        })

        viewModel.mealRandom.observe(viewLifecycleOwner, Observer {
            Log.e("LISTA:", "error $it")
            showBanner(it.strMealThumb)

        })

        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            Log.e("LISTA:", "error $it")
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            Log.e("LISTA:", "loading...")
        })
    }

    override fun init() {
        viewModel.getMealRandom()
    }

    override fun initListeners() {

        editTextTextSearchMeal.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                //after the change calling the method and passing the search input
                //filter(editable.toString())

                if (editable.isNotEmpty())
                    viewModel.getPlanes(editable.toString())
            }
        })
    }


    private fun notifyRecyclerViewItems(list: List<Meal>) {

        meals = list
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        mealAdapter = PersonajeAdapter(requireContext(), list, recyclerView)
        mealAdapter.listener = this
        recyclerView.adapter = mealAdapter
    }


    override fun onClickItem(objects: Meal, position: Int) {
        fragmentView?.goToWithObjet(R.id.action_listFragment_to_detailFragment, objects)
    }

    private fun showBanner(url: String) {

        Glide.with(requireContext())
            .load(url)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(imageViewBanner)

        postDelayed(10000) {
            viewModel.getMealRandom()
        }
    }


}