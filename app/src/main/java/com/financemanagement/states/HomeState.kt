package com.financemanagement.states

import com.financemanagement.model.UserEntryItem

data class HomeState(
    override val id: String = "",
    val userEntries: ArrayList<UserEntryItem> = ArrayList(),
    val totalExpense: Double = 0.0,
    val totalIncome: Double = 0.0
) : State