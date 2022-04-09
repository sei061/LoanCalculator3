package com.example.loancalculator3

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.loancalculator3.databinding.AnnuitetFragmentBinding

class AnnuitetFragment : Fragment() {

    private var _binding: AnnuitetFragmentBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AnnuitetFragmentBinding.inflate(inflater, container, false)

        sharedViewModel.loanAmount.observe(viewLifecycleOwner) { loanAmount ->
            binding.annuitetLoan.setText(loanAmount)
        }
        sharedViewModel.interestRate.observe(viewLifecycleOwner) { interestRate ->
            binding.annuitetInterest.setText(interestRate)
        }

        sharedViewModel.installments.observe(viewLifecycleOwner) { installments ->
            binding.annuitetInstallments.setText(installments)
        }

        binding.serieButton.setOnClickListener {
            sharedViewModel.setLoanAmount(binding.annuitetLoan.text.toString())
            sharedViewModel.setInterestRate(binding.annuitetInterest.text.toString())
            sharedViewModel.setInstallments(binding.annuitetInstallments.text.toString())
            findNavController().navigate(R.id.action_annuitetFragment_to_serieFragment)
        }

        //create a setonclicklistener for the button that binds the data and returns the calculate annuityfunction
        binding.calculateButton.setOnClickListener {
            sharedViewModel.setLoanAmount(binding.annuitetLoan.text.toString())
            sharedViewModel.setInterestRate(binding.annuitetInterest.text.toString())
            sharedViewModel.setInstallments(binding.annuitetInstallments.text.toString())
            sharedViewModel.calculateAnnuityPayment()
        }



        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    }

