package com.financemanagement.states

import com.financemanagement.model.UserEntryItem

data class HomeState(
    override val id: String = "",
    val userEntries: List<UserEntryItem> = emptyList(),
    val totalExpense: Double = 0.0,
    val totalIncome: Double = 0.0,
    val screenEvents: ScreenEvents = ScreenEvents.None()
) : State

sealed class ScreenEvents {
    class ShowToast(val text: String) : ScreenEvents()
    class None : ScreenEvents()
}