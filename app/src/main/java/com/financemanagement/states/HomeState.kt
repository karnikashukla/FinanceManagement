package com.financemanagement.states

import com.financemanagement.model.UserEntryItem

data class HomeState(
    override val id: String = "",
    val userEntries: List<UserEntryItem> = emptyList(),
    val totalExpense: Double = 0.0,
    val totalIncome: Double = 0.0,
    val screenEvents: ScreenEvents = ScreenEvents.none()
) : State

sealed class ScreenEvents {
    class showToast(val text: String) : ScreenEvents()
    class none : ScreenEvents()
}