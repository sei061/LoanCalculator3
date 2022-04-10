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

        //
        binding.calculateButton.setOnClickListener {
            sharedViewModel.setLoanAmount(binding.annuitetLoan.text.toString())
            sharedViewModel.setInterestRate(binding.annuitetInterest.text.toString())
            sharedViewModel.setInstallments(binding.annuitetInstallments.text.toString())
            sharedViewModel.calculateAnnuityPayment()
            var debtString : String = "Debt:\n"
            for (i in sharedViewModel.debtList) {
                debtString += i
                debtString += "\n"

                binding.debtList.text = debtString
            }

            var termString : String = "Terms:\n"
            for (i in sharedViewModel.termList.reversed()) {
                termString += i
                termString += "\n"

                binding.termList.text = termString
            }

            var interestString : String = "Interest:\n"
            for (i in sharedViewModel.interestList) {
                interestString += i
                interestString += "\n"

                binding.interestList.text = interestString
            }
            var deductionString : String = "Deductions:\n"
            for (i in sharedViewModel.deductionList) {
                deductionString += i
                deductionString += "\n"

                binding.deductionList.text = deductionString
            }
            var yearString : String = "Years:\n"
            for (i in sharedViewModel.termList) {
                yearString += i
                yearString += "\n"

                binding.deductionList.text = yearString
            }


        }


        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    }

