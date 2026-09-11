package com.example.s8129912assignment2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.s8129912assignment2.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)

        // Retrieve the entity object passed from the Dashboard
        val entity = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable("entity", Entity::class.java)
        } else {
            @Suppress("DEPRECATION")
            arguments?.getSerializable("entity") as? Entity
        }

        entity?.let {
            binding.tvProperty1.text = it.name ?: "Unknown Name"
            binding.tvDescription.text = it.description

            // Clean up: Hide the view completely if type is null, empty, or literal "N/A"
            if (it.type.isNullOrBlank() || it.type == "N/A") {
                binding.tvProperty2.visibility = View.GONE
            } else {
                binding.tvProperty2.visibility = View.VISIBLE
                binding.tvProperty2.text = getString(R.string.details_type_format, it.type)
            }
        }

        // Set up the back button navigation click handler
        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}