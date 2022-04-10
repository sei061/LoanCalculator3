package com.example.loancalculator3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import kotlin.math.pow
import kotlin.math.roundToInt

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

    fun calculateAnnuityPayment() {
        val loanAmountDouble = loanAmount.value!!.toDouble()
        var interestRateDouble = interestRate.value!!.toDouble()
        val installmentsDouble = installments.value!!.toDouble()

        var installmentPayment = loanAmountDouble * (interestRateDouble / 100) / 12 * (1 + (interestRateDouble / 100) / 12)
            .pow(installmentsDouble) / ((1 + (interestRateDouble / 100) / 12).pow(installmentsDouble) - 1)

        var remainingDebt = loanAmountDouble
        var deduction = loanAmountDouble - interestRateDouble

        debtList.add(loanAmountDouble.toString())

        for(i in 1..loanAmount.value!!.toInt()) {
            numOfYearList.add(i.toString())
            termList.add(installmentPayment.roundToInt().toString())
            interestList.add((interestRateDouble.roundToInt()).toString())
            deductionList.add(deduction.roundToInt().toString())
            debtList.add((loanAmountDouble - deduction).roundToInt().toString())
            remainingDebt -= deduction
            interestRateDouble =
                remainingDebt * (1.0 +( interestRateDouble / 100)) - remainingDebt
            deduction = loanAmountDouble - interestRateDouble

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
            interestList.add((debtList[i].toDouble() * (interestRateDouble / 100)).toString())
        }

        for (i in 0..installmentsDouble.toInt()) {
           termList.add(i,i.toString())
        }
        for (i in 0..installmentsDouble.toInt()) {
            numOfYearList.add(i,(i/12).toString())
        }

    }
    fun clean(){
    debtList.clear()
    numOfYearList.clear()
    interestList.clear()
    termList.clear()
    deductionList.clear()
    }
}


