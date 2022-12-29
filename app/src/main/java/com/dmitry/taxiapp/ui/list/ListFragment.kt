package com.dmitry.taxiapp.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmitry.taxiapp.R
import com.dmitry.taxiapp.databinding.FragmentListBinding
import com.dmitry.taxiapp.model.Order

class ListFragment : Fragment(R.layout.fragment_list) {

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding get() = _binding!!
    lateinit var viewModel: ListViewModel
    private val ordersAdapter by lazy {
        OrdersAdapter() {
            navigateToDetails(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ListViewModel::class.java]
        _binding = FragmentListBinding.bind(view)

        initView()
        subscribeUi()
    }

    private fun initView() {
        binding.rvOrders.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = ordersAdapter
        }
    }

    private fun subscribeUi() {
        viewModel.listOrders.observe(viewLifecycleOwner) {
            ordersAdapter.setData(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToDetails(order: Order) {
        val direction = ListFragmentDirections.actionListFragmentToDetailsFragment(order)
        findNavController().navigate(direction)
    }
}