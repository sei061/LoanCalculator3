package com.example.loancalculator3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController

class SharedViewModel: ViewModel() {

    private var _loanAmount = MutableLiveData("0")
    val loanAmount: LiveData<String> = _loanAmount

    private var _interestRate = MutableLiveData("0")
    val interestRate: LiveData<String> = _interestRate

    private var _installments = MutableLiveData("0")
    val installments: LiveData<String> = _installments

    var numOfYearList = mutableListOf<String>()
    var debtList = mutableListOf<String>()
    var termList = mutableListOf<String>()
    var interestList = mutableListOf<String>()
    var deductionList = mutableListOf<String>()

    fun setLoanAmount(loanAmount: String) {
        _loanAmount.value = loanAmount
    }

    fun setInterestRate(interestRate: String) {
        _interestRate.value = interestRate
    }

    fun setInstallments(installments: String) {
        _installments.value = installments
    }

    //create a function that will take the loan amount and the interest rate and calculate the paymeent based on installments
    fun calculateAnnuityPayment() {
        val loanAmountDouble = loanAmount.value!!.toDouble()
        val interestRateDouble = interestRate.value!!.toDouble()
        val installmentsDouble = installments.value!!.toDouble()

        val installmentPayment = (loanAmountDouble * (interestRateDouble / 100) * Math.pow(
            1 + (interestRateDouble / 100),
            installmentsDouble
        )) / (Math.pow(1 + (interestRateDouble / 100), installmentsDouble) - 1)

        val installmentPaymentString = String.format("%.2f", installmentPayment)

        _installments.value = installmentPaymentString

        for (i in 1..installmentsDouble.toInt()) {
            val numOfYear = i.toString()
            val debt = String.format(
                "%.2f", (installmentPayment * (Math.pow(
                    1 + (interestRateDouble / 100),
                    i.toDouble()
                ) - 1))
            )
            val term =
                String.format("%.2f", (Math.pow(1 + (interestRateDouble / 100), i.toDouble()) - 1))
            val interest = String.format(
                "%.2f", (installmentPayment * (Math.pow(
                    1 + (interestRateDouble / 100),
                    i.toDouble()
                ) - 1) * (interestRateDouble / 100))
            )
            val deduction = String.format(
                "%.2f", (installmentPayment * (Math.pow(
                    1 + (interestRateDouble / 100),
                    i.toDouble()
                ) - 1) * (interestRateDouble / 100) * 0.1)
            )

            numOfYearList.add(numOfYear)
            debtList.add(debt)
            termList.add(term)
            interestList.add(interest)
            deductionList.add(deduction)
        }
    }
    fun calculateSerialPayment() {
        val loanAmountDouble = loanAmount.value!!.toDouble()
        val interestRateDouble = interestRate.value!!.toDouble()
        val installmentsDouble = installments.value!!.toDouble()

        debtList.add(loanAmountDouble.toString())

        for (i in 0..installmentsDouble.toInt()) {
            deductionList.add(i, (loanAmountDouble/installmentsDouble).toString())
        }

        for (i in 1..installmentsDouble.toInt()) {
            debtList.add(i, (debtList[i-1].toDouble() - (loanAmountDouble / installmentsDouble)).toString())
        }

        for (i in 0..installmentsDouble.toInt()) {
            interestList.add((debtList[i].toDouble() * interestRateDouble).toString())
        }

        for (i in 0..installmentsDouble.toInt()) {
           termList.add(i,i.toString())
        }
    }
}


