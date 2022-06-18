package com.example.whac_a_mole.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.whac_a_mole.R
import com.example.whac_a_mole.databinding.FragmentGameBinding
import java.lang.RuntimeException


class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding = null")

    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(requireActivity().application)
        )[GameViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.startGame(view)
        visibleOneMole()
        clickListener()
        observeViewModel()
    }

    private fun clickListener() {
        binding.ivAsOval1.setOnClickListener {
            binding.ivAsOval1.isVisible = false
            viewModel.filterOnClick(1)
            viewModel.nextRandomOval()

        }
        binding.ivAsOval2.setOnClickListener {
            binding.ivAsOval2.isVisible = false
            viewModel.filterOnClick(2)
            viewModel.nextRandomOval()

        }
        binding.ivAsOval3.setOnClickListener {
            binding.ivAsOval3.isVisible = false
            viewModel.filterOnClick(3)
            viewModel.nextRandomOval()

        }
        binding.ivAsOval4.setOnClickListener {
            binding.ivAsOval4.isVisible = false
            viewModel.filterOnClick(4)
            viewModel.nextRandomOval()

        }
        binding.ivAsOval5.setOnClickListener {
            binding.ivAsOval5.isVisible = false
            viewModel.filterOnClick(5)
            viewModel.nextRandomOval()

        }
        binding.ivAsOval6.setOnClickListener {
            binding.ivAsOval6.isVisible = false
            viewModel.filterOnClick(6)
            viewModel.nextRandomOval()

        }
        binding.ivAsOval7.setOnClickListener {
            binding.ivAsOval7.isVisible = false
            viewModel.filterOnClick(7)
            viewModel.nextRandomOval()

        }
        binding.ivAsOval8.setOnClickListener {
            binding.ivAsOval8.isVisible = false
            viewModel.filterOnClick(8)
            viewModel.nextRandomOval()

        }
    }

    private fun invisibleMole() {
        binding.ivAsOval1.isVisible = false
        binding.ivAsOval2.isVisible = false
        binding.ivAsOval3.isVisible = false
        binding.ivAsOval4.isVisible = false
        binding.ivAsOval5.isVisible = false
        binding.ivAsOval6.isVisible = false
        binding.ivAsOval7.isVisible = false
        binding.ivAsOval8.isVisible = false
    }

    private fun visibleOneMole() {
        viewModel.ovalWithMole.observe(viewLifecycleOwner) {
            invisibleMole()
            when (viewModel.ovalWithMole.value) {
                1 -> binding.ivAsOval1.isVisible = true
                2 -> binding.ivAsOval2.isVisible = true
                3 -> binding.ivAsOval3.isVisible = true
                4 -> binding.ivAsOval4.isVisible = true
                5 -> binding.ivAsOval5.isVisible = true
                6 -> binding.ivAsOval6.isVisible = true
                7 -> binding.ivAsOval7.isVisible = true
                8 -> binding.ivAsOval8.isVisible = true
            }
        }
    }

    private fun observeViewModel() {
        viewModel.formatedTime.observe(viewLifecycleOwner) {
            binding.tvTimer.text = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}