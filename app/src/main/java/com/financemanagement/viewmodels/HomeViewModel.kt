package com.financemanagement.viewmodels

import androidx.lifecycle.ViewModel
import com.financemanagement.model.EntryType
import com.financemanagement.states.HomeState
import com.financemanagement.utilities.DataUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel(){
    val state = MutableStateFlow(HomeState())

    init {
        state.update {
            it.copy(
                userEntries = DataUtils().createEntityList()
            )
        }

        getTotalIncomeExpense()
    }

    private fun getTotalIncomeExpense() {
        var totalIncome = 0.0
        var totalExpense = 0.0

        val userEntities = state.value.userEntries

        for (i in userEntities.indices) {
            if (userEntities[i].entryType == EntryType.INCOME)
                totalIncome += userEntities[i].amount
            else
                totalExpense += userEntities[i].amount
        }

        state.update {
            it.copy(
                totalIncome = totalIncome,
                totalExpense = totalExpense
            )
        }

    }
}