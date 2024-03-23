package com.example.test.Screens

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.Screens.R
import com.example.Screens.databinding.FragmentPlayBinding


class Play : Fragment() {

    private lateinit var binding: FragmentPlayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setting up onClickListener for the Login button
        binding.button.setOnClickListener {
            val email = binding.email.editText?.text?.toString() ?: ""
            val password = binding.password.editText?.text?.toString() ?: ""

            // Check if email or password is empty
            if (email.isEmpty() || password.isEmpty()){
                if (email.isEmpty()){
                    binding.email.error = "Enter your Email Address"
                }
                if (password.isEmpty()){
                    binding.password.error = "Enter your Password"
                }
            } else {
                // If both email and password are provided, check if email is valid manually
                if (isValidEmail(email)){
                    // Navigate to the homepage if email is valid
                    findNavController().navigate(R.id.action_play_to_homePage)
                }
                else{
                    // Show error if email is not valid
                    binding.email.error = "Enter a Valid Email Address"
                }
            }
        }

        // Clear error message when text changes in the fields
        binding.email.editText?.addTextChangedListener {
            binding.email.error = null
        }
        binding.password.editText?.addTextChangedListener {
            binding.password.error = null
        }
    }

    // Function to check if the provided email is valid using Patterns.EMAIL_ADDRESS matcher
    private fun isValidEmail(email: String): Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
