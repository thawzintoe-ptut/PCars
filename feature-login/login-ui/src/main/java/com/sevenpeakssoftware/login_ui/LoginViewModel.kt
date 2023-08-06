package com.sevenpeakssoftware.login_ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.mixpanel.android.mpmetrics.MixpanelAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val mixpanelAPI: MixpanelAPI
) : ViewModel() {

    private val _loginStateFlow = MutableStateFlow(LoginUiState.Loading)
    val loginStateFlow: StateFlow<LoginUiState>
        get() = _loginStateFlow.asStateFlow()

    init {
        loadLoginData()
    }

    private fun loadLoginData() {
        viewModelScope.launch(Dispatchers.IO) {
            // Code to load data goes here
        }
    }

    fun getMixPanelApi(): MixpanelAPI{
        return mixpanelAPI
    }
}

sealed interface LoginUiState {
    object Loading : LoginUiState
    data class LoginData(
        val items: List<Any>
    ) : LoginUiState
    data class Error(
        val errorMessage: String
    ) : LoginUiState
}