package com.financemanagement.viewmodels

import androidx.lifecycle.ViewModel
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
    }
}