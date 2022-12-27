package com.dmitry.taxiapp.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dmitry.taxiapp.App
import com.dmitry.taxiapp.R
import com.dmitry.taxiapp.databinding.FragmentListBinding

class ListFragment: Fragment(R.layout.fragment_list) {

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)
    }


}