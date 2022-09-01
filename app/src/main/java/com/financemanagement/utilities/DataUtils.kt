package com.financemanagement.utilities

import com.financemanagement.model.EntryType
import com.financemanagement.model.UserEntryItem
import kotlin.random.Random

class DataUtils {
    private fun postRandomEntry(): UserEntryItem {
        return UserEntryItem(
            amount = (1..10000).random().toDouble(),
            title = "this is title ${(1..20).random()}",
            entryType = if (Random.nextBoolean())
                EntryType.INCOME
            else
                EntryType.EXPENSE
        )
    }

    fun createEntityList() : ArrayList<UserEntryItem>{
        val userEntities:ArrayList<UserEntryItem> = ArrayList()

        for (i in 1..10) {
            userEntities.add(postRandomEntry())
        }
        return userEntities
    }

}