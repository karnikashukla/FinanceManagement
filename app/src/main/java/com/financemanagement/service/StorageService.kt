package com.financemanagement.service

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
/*                var entry: UserEntryItem

                for (postSnapshot in dataSnapshot.children) {
                    entry = postSnapshot.getValue<UserEntryItem>()!!
                    entry.let {
                        entries.add(it)
                    }
                }*/
                val entriesMap: HashMap<String, UserEntryItem>? =
                    dataSnapshot.value as HashMap<String, UserEntryItem>?

                entriesMap?.let {
                    entries.addAll(it.values)
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