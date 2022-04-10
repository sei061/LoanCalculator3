package com.example.loancalculator3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.loancalculator3.databinding.SerieFragmentBinding

class SettingsFragment : PreferenceFragmentCompat() {

    private var _binding: SerieFragmentBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()


    private fun settings(){
        val sharedPreference = context?.let { PreferenceManager.getDefaultSharedPreferences(it) }

    }
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

    }
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
            return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}