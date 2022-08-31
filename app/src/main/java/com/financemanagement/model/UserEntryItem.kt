package com.financemanagement.model

data class UserEntryItem(
    val title: String,
    val amount: Double,
    val entryType: EntryType
)

enum class EntryType(){
    INCOME,
    EXPENSE
}