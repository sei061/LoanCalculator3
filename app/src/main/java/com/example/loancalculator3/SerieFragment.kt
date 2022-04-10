package com.example.loancalculator3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.loancalculator3.databinding.SerieFragmentBinding

class SerieFragment : Fragment() {
    private var _binding: SerieFragmentBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SerieFragmentBinding.inflate(inflater, container, false)

        sharedViewModel.loanAmount.observe(viewLifecycleOwner) { loanAmount ->
            binding.serieLoan.setText(loanAmount)
        }

        sharedViewModel.interestRate.observe(viewLifecycleOwner) { interestRate ->
            binding.serieInterest.setText(interestRate)
        }

        sharedViewModel.installments.observe(viewLifecycleOwner) { installments ->
            binding.serieInstallments.setText(installments)

        }
        binding.annuitetButton.setOnClickListener {
            sharedViewModel.setLoanAmount(binding.serieLoan.text.toString())
            sharedViewModel.setInterestRate(binding.serieInterest.text.toString())
            sharedViewModel.setInstallments(binding.serieInstallments.text.toString())
            findNavController().navigate(R.id.action_serieFragment_to_annuitetFragment)

        }

        binding.calculateButton.setOnClickListener {


            sharedViewModel.setLoanAmount(binding.serieLoan.text.toString())
            sharedViewModel.setInterestRate(binding.serieInterest.text.toString())
            sharedViewModel.setInstallments(binding.serieInstallments.text.toString())
            sharedViewModel.calculateSerialPayment()
            var debtString : String = ""
                for (i in sharedViewModel.debtList) {
                    debtString += i
                    debtString += "\n"

            binding.debtList.text = debtString
            }

            var termString : String = ""
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
            var deductionString : String = ""
            for (i in sharedViewModel.deductionList) {
                deductionString += i
                deductionString += "\n"

                binding.deductionList.text = deductionString
            }
            var yearString : String = ""
            for (i in sharedViewModel.numOfYearList) {
                yearString += i
                yearString += "\n"

                binding.yearList.text = yearString
            }


        }

            return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}