package de.myprojects.shoestore.home

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import de.myprojects.shoestore.MainViewModel
import de.myprojects.shoestore.R
import de.myprojects.shoestore.databinding.FragmentHomeBinding
import de.myprojects.shoestore.models.Shoe
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private lateinit var listAdapter: ShoeListAdapter
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.home = this

        setHasOptionsMenu(true)
        observeShoes()
        initRecyclerView()

        return binding.root
    }


    private fun observeShoes() {
        viewModel.shoes.observe(viewLifecycleOwner, Observer { shoes ->
            listAdapter.setData(shoes)
        })
    }

    private fun initRecyclerView(){
        listAdapter = ShoeListAdapter()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
    }

    fun navigateDetail() {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //handles navigate to login -> id's of menuItem and fragment destination needs to be the same
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) || super.onOptionsItemSelected(item)
    }
}