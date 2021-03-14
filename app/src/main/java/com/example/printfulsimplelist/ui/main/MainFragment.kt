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
import com.example.printfulsimplelist.api.Article
import com.example.printfulsimplelist.ui.shared.SharedViewModel

class MainFragment : Fragment(),
 MainRecyclerAdapter.ArticleItemListener {

    private lateinit var viewModel: SharedViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeLayout: SwipeRefreshLayout
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }

        val view =inflater.inflate(R.layout.fragment_main, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        navController = Navigation.findNavController(
                requireActivity(), R.id.nav_host
        )
        swipeLayout = view.findViewById(R.id.swipeLayout)
        swipeLayout.setOnRefreshListener {
            viewModel.refreshData()
        }

        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel.articleData.observe(viewLifecycleOwner, Observer
        {
            val adapter = MainRecyclerAdapter(requireContext(), it, this)
            recyclerView.adapter = adapter
            swipeLayout.isRefreshing = false
        })

        return view
    }

    override fun onArticleItemClick(article: Article) {
        Log.i(LOG_TAG, "Selected title: ${article.title}")
        Log.i(LOG_TAG, "Selected author: ${article.author}\n urlImg: ${article.urlToImage}")
        Log.i(LOG_TAG, "Selected article: ${article}")
        viewModel.selectedArticle.value = article
        navController.navigate(R.id.action_nav_detail)
    }

}