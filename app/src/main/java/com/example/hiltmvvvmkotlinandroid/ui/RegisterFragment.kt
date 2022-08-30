package com.example.hiltmvvvmkotlinandroid.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.hiltmvvvmkotlinandroid.R
import com.example.hiltmvvvmkotlinandroid.databinding.FragmentRegisterBinding
import com.example.hiltmvvvmkotlinandroid.model.UserRequest
import com.example.hiltmvvvmkotlinandroid.utils.NetworkResult
import com.example.hiltmvvvmkotlinandroid.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding : FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val authViewModel by viewModels<AuthViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.btnSignUp.setOnClickListener {

            authViewModel.registerUser(UserRequest("NameTest1","nametest1@gmail.com","112456"))
            authViewModel.authLiveData.observe(viewLifecycleOwner, Observer {
                binding.progressBar.visibility = View.GONE
                when(it){
                    is NetworkResult.Success -> {
                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                    }
                    is NetworkResult.Error -> {

                    }
                    is NetworkResult.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}
