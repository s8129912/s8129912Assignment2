package com.example.s8129912assignment2

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.s8129912assignment2.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var adapter: EntityAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDashboardBinding.bind(view)

        // 1. Grab keypass from the Bundle arguments passed from Login Screen
        val keypass = arguments?.getString("keypass") ?: ""

        // 2. Initialize Adapter with a click action
        adapter = EntityAdapter { selectedEntity ->
            val bundle = Bundle().apply {
                putSerializable("entity", selectedEntity)
            }
            findNavController().navigate(R.id.action_dashboardFragment_to_detailsFragment, bundle)
        }
        binding.recyclerView.adapter = adapter

        // 3. Make Network Call if keypass is present
        if (keypass.isNotEmpty()) {
            viewModel.fetchDashboardData(keypass)
        }

        // 4. Watch Network Results
        viewModel.dashboardState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is NetworkResult.Success -> {
                    binding.progressBar.visibility = View.GONE
                    adapter.submitList(result.data)
                }
                is NetworkResult.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, result.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}