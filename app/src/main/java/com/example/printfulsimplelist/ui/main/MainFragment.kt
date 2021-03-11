package com.example.printfulsimplelist.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.printfulsimplelist.LOG_TAG
import com.example.printfulsimplelist.R
import com.example.printfulsimplelist.adapters.MainRecyclerAdapter
import com.example.printfulsimplelist.adapters.RecyclerAdapter


class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(LOG_TAG, "Fragment 1")
        val view =inflater.inflate(R.layout.fragment_main, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        Log.i(LOG_TAG, "Main fragment 1")
        viewModel.newsData.observe(viewLifecycleOwner, Observer {
            val adapter = MainRecyclerAdapter(requireContext(), it)
            recyclerView.adapter = adapter
        })
        // Inflate the layout for this fragment
        return view
    }


}