package com.example.whac_a_mole.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.whac_a_mole.R
import com.example.whac_a_mole.databinding.FragmentResultBinding
import java.lang.RuntimeException


class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding: FragmentResultBinding
        get() = _binding ?: throw RuntimeException("FragmentResultBinding = null")

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
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonMenu.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_welcomeFragment)
        }
        binding.buttonPlay.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_gameFragment)
        }
        viewModel.result.observe(viewLifecycleOwner) {
            binding.tvResultScore.text = String.format(
                getString(R.string.tv_result_score), requireArguments().getInt(RESULT_KEY) // вероятная ошибка вывода
            )
        }
        binding.tvResultRecord.text = String.format(
            getString(R.string.tv_result_record), viewModel.record
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.result.value = 0
    }
    companion object{
        const val RESULT_KEY = "result"
    }
}