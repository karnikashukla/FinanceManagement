package com.financemanagement.viewmodels

import androidx.lifecycle.ViewModel
import com.financemanagement.model.EntryType
import com.financemanagement.service.StorageService
import com.financemanagement.states.HomeState
import com.financemanagement.states.ScreenEvents
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {
    val state = MutableStateFlow(HomeState())
    private val storageService = StorageService()

    init {
//        val data = DataUtils().createEntityList()
//        data.forEach(){ item ->
//            storageService.addNewEntry(item)
//        }
        getUserEntries()
    }

    private fun getUserEntries() {
        storageService.entriesDataListener(
            newEntry = { userEntryList ->
//                val userEntriesList = state.value.userEntries
//                userEntriesList.add(userEntry)
                state.update {
                    it.copy(
                        userEntries = userEntryList
                    )
                }
                getTotalIncomeExpense()
            },
            onError = { errorMessage ->
                state.update {
                    it.copy(
                        screenEvents = ScreenEvents.showToast(errorMessage)
                    )
                }
            }
        )
    }

    private fun getTotalIncomeExpense() {
        var totalIncome = 0.0
        var totalExpense = 0.0

        val userEntities = state.value.userEntries

        for (i in userEntities) {
            if (i.entryType == EntryType.INCOME)
                totalIncome += i.amount
            else
                totalExpense += i.amount
        }

        state.update {
            it.copy(
                totalIncome = totalIncome,
                totalExpense = totalExpense
            )
        }

    }
}