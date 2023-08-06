package com.sevenpeakssoftware.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class CarBaseViewModel constructor(
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    var isRefreshingState: MutableStateFlow<Boolean> = MutableStateFlow(false)

    /**
     * lunch IO execution for ViewModel
     */
    fun viewModelScopeIO(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(ioDispatcher, block = block)
}
