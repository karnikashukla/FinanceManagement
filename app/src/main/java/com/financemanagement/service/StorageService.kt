package com.financemanagement.service

import com.financemanagement.model.EntryType
import com.financemanagement.model.UserEntryItem
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class StorageService {
    fun entriesDataListener(
        newEntry: (ArrayList<UserEntryItem>) -> Unit,
        onError: (String) -> Unit
    ) {
        val database = Firebase.database
        val myRef = database.getReference("passbook_entries")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val entries: ArrayList<UserEntryItem> = ArrayList()

                dataSnapshot.children.forEach {
                    val userEntryItem = UserEntryItem(
                        title = it.child("title").getValue(String::class.java).orEmpty(),
                        entryType = it.child("entryType").getValue(EntryType::class.java)
                            ?: EntryType.UNKNOWN,
                        amount = it.child("amount").getValue(Double::class.java) ?: 0.0,
                    )
                    entries.add(userEntryItem)
                }
                newEntry(entries)
            }

            override fun onCancelled(error: DatabaseError) {
                onError(error.message)
            }
        })
    }

    fun addNewEntry(userEntryItem: UserEntryItem) {
        val database = Firebase.database
        val myRef = database.getReference("passbook_entries")

        myRef.push().setValue(userEntryItem)
    }
}