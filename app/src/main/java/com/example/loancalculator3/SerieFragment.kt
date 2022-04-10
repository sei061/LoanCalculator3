package com.example.loancalculator3

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.loancalculator3.databinding.AnnuitetFragmentBinding
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
            //val listView = findNavController().navigate(R.id.listView)
            sharedViewModel.setLoanAmount(binding.serieLoan.text.toString())
            sharedViewModel.setInterestRate(binding.serieInterest.text.toString())
            sharedViewModel.setInstallments(binding.serieInstallments.text.toString())
            sharedViewModel.calculateSerialPayment()


        }

            return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}