package com.example.printfulsimplelist.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.printfulsimplelist.LOG_TAG
import com.example.printfulsimplelist.R
import com.example.printfulsimplelist.adapters.MainRecyclerAdapter
import com.example.printfulsimplelist.api.Article

class MainFragment : Fragment() ,
 MainRecyclerAdapter.ArticleItemListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeLayout: SwipeRefreshLayout
    private lateinit var navController: NavController
    private var change: String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }

        Log.i(LOG_TAG, "Fragment 1")
        val view =inflater.inflate(R.layout.fragment_main, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        navController = Navigation.findNavController(
                requireActivity(), R.id.nav_host
        )
        swipeLayout = view.findViewById(R.id.swipeLayout)
        swipeLayout.setOnRefreshListener {
            viewModel.refreshData()
        }

        //123
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        Log.i(LOG_TAG, "Main fragment 1")
        viewModel.articleData.observe(viewLifecycleOwner, Observer {
            val adapter = MainRecyclerAdapter(requireContext(), it, this)
            recyclerView.adapter = adapter
            swipeLayout.isRefreshing = false
        })

        return view
    }

    override fun onArticleItemClick(news: Article) {
        Log.i(LOG_TAG, "Selected title: ${news.title}")

    }


}