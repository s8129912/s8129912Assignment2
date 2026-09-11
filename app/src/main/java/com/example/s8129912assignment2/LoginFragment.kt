package com.example.s8129912assignment2

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.s8129912assignment2.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Allows Hilt to pass dependencies into this Fragment
class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)

        binding.btnLogin.setOnClickListener {
            val user = binding.etUsername.text.toString().trim()
            val pass = binding.etPassword.text.toString().trim()
            viewModel.login(user, pass)
        }

        // Watch the loader state to show/hide the spinner
        viewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
            binding.btnLogin.isEnabled = !loading
        }

        // Watch the API response outcome
        viewModel.loginResult.observe(viewLifecycleOwner) { result ->
            result.onSuccess { keypass ->
                Toast.makeText(context, "Welcome!", Toast.LENGTH_SHORT).show()

                // Move safely to the Dashboard and hand over the keypass token automatically
                val action = LoginFragmentDirections.actionLoginFragmentToDashboardFragment(keypass)
                findNavController().navigate(action)
            }.onFailure { error ->
                // Requirements state to display appropriate error messages for failed attempts
                Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}