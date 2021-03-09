package com.example.printfulsimplelist.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.printfulsimplelist.R
import androidx.lifecycle.ViewModelProviders
import java.util.EnumSet.of


class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_main, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)


        viewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
        viewModel.newsData.observe(viewLifecycleOwner, Observer {

        })
        // Inflate the layout for this fragment
        return view
    }


}