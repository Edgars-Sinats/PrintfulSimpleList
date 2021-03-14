package com.example.printfulsimplelist.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.printfulsimplelist.R
import com.example.printfulsimplelist.databinding.FragmentDetailBinding
import com.example.printfulsimplelist.ui.shared.SharedViewModel

class DetailFragment : Fragment() {

    private lateinit var viewModel: SharedViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        setHasOptionsMenu(true)
        navController = Navigation.findNavController(
                requireActivity(), R.id.nav_host
        )

        viewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
//        viewModel.selectedArticle.observe(this, Observer {
//            Log.i(LOG_TAG, "Selected article: ${it.title}")
//        })



        val binding = FragmentDetailBinding.inflate(
                inflater, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
//        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == android.R.id.home) {
            navController.navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }
}