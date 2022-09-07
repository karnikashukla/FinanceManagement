package com.financemanagement.model

data class UserEntryItem(
    val title: String = "",
    val amount: Double = 0.0,
    val entryType: EntryType = EntryType.EXPENSE
)

data class UserEntryList(
    val userEntries: ArrayList<UserEntryItem> = ArrayList()
)

enum class EntryType {
    INCOME,
    EXPENSE
}