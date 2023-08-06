package com.sevenpeakssoftware.login_ui

import com.sevenpeakssoftware.core.ui.ScreenStateCallBack
import com.sevenpeakssoftware.core.ui.ScreenStateData

class LoginScreenState(
    val data: LoginData,
    val delegate: LoginDelegate
) {
    data class LoginData(
        val data: androidx.compose.runtime.State<String> = androidx.compose.runtime.mutableStateOf(""),
    ) : ScreenStateData<LoginScreenState>

    data class LoginDelegate(
        val onTapBack: () -> Unit = {},
        val onClickLogin: () -> Unit = {},
        val onClickSignUp: () -> Unit = {}
    ) : ScreenStateCallBack<LoginScreenState>
}

class LoginPreviewProvider : androidx.compose.ui.tooling.preview.PreviewParameterProvider<LoginScreenState> {
    override val values: Sequence<LoginScreenState>
        get() = sequenceOf(
            LoginScreenState(
                data = LoginScreenState.LoginData(),
                delegate = LoginScreenState.LoginDelegate()
            ),
        )
}