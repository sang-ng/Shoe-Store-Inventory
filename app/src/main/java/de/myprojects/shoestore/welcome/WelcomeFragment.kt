package de.myprojects.shoestore.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import de.myprojects.shoestore.R
import de.myprojects.shoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)
        binding.welcome = this

        return binding.root
    }


    //Binding
    fun navigateInstructions() {
        val action = WelcomeFragmentDirections.actionWelcomeFragmentToInstructionFragment()
        findNavController().navigate(action)
    }
}