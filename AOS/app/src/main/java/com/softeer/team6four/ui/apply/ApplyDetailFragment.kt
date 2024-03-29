package com.softeer.team6four.ui.apply

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.softeer.team6four.R
import com.softeer.team6four.databinding.FragmentApplyDetailBinding
import com.softeer.team6four.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ApplyDetailFragment : Fragment() {
    private var _binding: FragmentApplyDetailBinding? = null
    private val homeViewModel: HomeViewModel by activityViewModels()
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentApplyDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            viewModel = homeViewModel
            lifecycleOwner = viewLifecycleOwner

            btnNext.setOnClickListener { findNavController().navigate(R.id.action_applyDetailFragment_to_applyTimeFragment) }
            ibBack.setOnClickListener { findNavController().popBackStack() }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}