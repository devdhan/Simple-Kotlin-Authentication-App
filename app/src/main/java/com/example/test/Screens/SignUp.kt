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
import com.example.Screens.databinding.FragmentSignUpBinding
import java.util.regex.Pattern


class SignUp : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    //Button Click Handler
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setting up onClickListener for the sign up button
        binding.button2.setOnClickListener {
            val name = binding.name.editText?.text?.toString() ?: ""
            val email = binding.email.editText?.text?.toString() ?: ""
            val password = binding.password.editText?.text?.toString() ?: ""
            val conpass = binding.conpass.editText?.text?.toString() ?: ""

            // Password pattern for at least one number, one special character,
            // one lowercase letter, and one uppercase letter
            val passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")

            // Check if any field is empty or passwords don't match or don't meet requirements
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || conpass.isEmpty()
                || password.length < 8 || password != conpass
                || !passwordPattern.matcher(password).matches()) {
                if (name.isEmpty()) {
                    binding.name.error = "Enter your Full Name"
                }
                if (email.isEmpty()) {
                    binding.email.error = "Enter your Email Address"
                }
                if (password.isEmpty()) {
                    binding.password.error = "Enter your Password"
                    // Check if password meets requirements
                } else if (password.length < 8) {
                    binding.password.error = "Minimum of eight characters"
                } else if (!passwordPattern.matcher(password).matches()) {
                    binding.password.error = "Password must contain at least one number, " +
                            "one special character, " +
                            "one lowercase letter, " +
                            "and one uppercase letter"
                }
                if (conpass.isEmpty()){
                    binding.conpass.error = "Enter your Password"
                }
                // Check if both password and confirm password match
                if (password != conpass){
                    binding.conpass.error = "Password does not match"
                }
            } else {
                // If all fields are filled and passwords match, check if email is valid manually
                if (isValidEmail(email)){
                    // Navigate to the play screen if email is valid
                    findNavController().navigate(R.id.action_signUp_to_play)
                }
                else{
                    // Show error if email is not valid
                    binding.email.error = "Enter a valid Email Address"
                }
            }
        }

        // Clear error message when text changes in the fields
        binding.name.editText?.addTextChangedListener {
            binding.name.error = null
        }
        binding.email.editText?.addTextChangedListener {
            binding.email.error = null
        }
        binding.password.editText?.addTextChangedListener {
            binding.password.error = null
        }
        binding.conpass.editText?.addTextChangedListener {
            binding.conpass.error = null
        }

        // Setting up onClickListener for the button to navigate to the login screen
        binding.button3.setOnClickListener {
            findNavController().navigate(R.id.action_signUp_to_play)
        }
    }

    // Function to check if the provided email is valid using Patterns.EMAIL_ADDRESS matcher
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
