package com.sevenpeakssoftware.login_ui

import android.annotation.SuppressLint
import android.icu.util.VersionInfo
import android.os.Build.VERSION
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mixpanel.android.mpmetrics.MixpanelAPI
import com.sevenpeakssoftware.core.navigation.Screen
import org.json.JSONObject
import org.threeten.bp.LocalDateTime

@SuppressLint("UnrememberedMutableState")
@Composable
fun LoginScreen(
    navHostController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val mixpanelApi = viewModel.getMixPanelApi()
    LoginScreenStateless(
        state = LoginScreenState(
            data = LoginScreenState.LoginData(
                data = mutableStateOf(""),
            ),
            delegate = LoginScreenState.LoginDelegate(
                onTapBack = { },
                onClickLogin = {
                    mixpanelApi.identify("thaw.toe@7peakssoftware.com")
                    /**
                     * Event Properties:
                     * Event properties are the attributes associated with a specific event. In this case, the event is a user signup.
                     */
                    val signUpEventProps = JSONObject().apply {
                        put("Login Method","Email")
                        put("Login Date","${LocalDateTime.now()}")
                        put("Login Source","SevenPeaks")
                        put("Login Device","Android")
                    }
                    mixpanelApi.track("User Login",signUpEventProps)

                    /**
                     * User Properties:
                     * User properties are attributes associated with a specific user in your system.
                     */
                    val signUpUserProps = JSONObject().apply {
                        put("email","thaw.toe@7peakssoftware.com")
                        put("name","Thaw Zin Toe")
                        put("gender","male")
                        put("age","27")
                    }
                    mixpanelApi.people.set(signUpUserProps)

                    /**
                     * Super Properties:
                     * Super properties are global properties that are associated with all events and users in your system.
                     */
                    val signUpSuperProps = JSONObject().apply {
                        put("Android Version","${VERSION.BASE_OS}")
                        put("Referral Source","Seven Peaks")
                        put("Currency","THB")
                        put("Language","English")
                    }
                    mixpanelApi.registerSuperProperties(signUpSuperProps)
                    navHostController.navigate(Screen.Car.route)
                },
                onClickSignUp = {
                    mixpanelApi.identify("thaw.toe@7peakssoftware.com")
                    /**
                     * Event Properties:
                     * Event properties are the attributes associated with a specific event. In this case, the event is a user signup.
                     */
                    val signUpEventProps = JSONObject().apply {
                        put("Signup Method","Email")
                        put("Signup Date","${LocalDateTime.now()}")
                        put("Signup Source","SevenPeaks")
                        put("Signup Device","Android")
                    }
                    mixpanelApi.track("User SignUp",signUpEventProps)

                    /**
                     * User Properties:
                     * User properties are attributes associated with a specific user in your system.
                     */
                    val signUpUserProps = JSONObject().apply {
                        put("email","thaw.toe@7peakssoftware.com")
                        put("name","Thaw Zin Toe")
                        put("gender","male")
                        put("age","27")
                    }
                    mixpanelApi.people.set(signUpUserProps)

                    /**
                     * Super Properties:
                     * Super properties are global properties that are associated with all events and users in your system.
                     */
                    val signUpSuperProps = JSONObject().apply {
                        put("Android Version","${VERSION.SDK_INT}")
                        put("Referral Source","Seven Peaks")
                        put("Currency","THB")
                        put("Language","English")
                    }
                    mixpanelApi.registerSuperProperties(signUpSuperProps)
                    navHostController.navigate(Screen.Car.route)
                }
            ),
        ),
    )
}

@Preview(
    showBackground = true,
)
@Composable
fun LoginScreenStateless(
    @PreviewParameter(LoginPreviewProvider::class)
    state: LoginScreenState,
) {

    var emailText by remember {
        mutableStateOf("")
    }
    var passwordText by remember {
        mutableStateOf("")
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            OutlinedTextField(
                value = emailText,
                onValueChange = {
                    emailText = it
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Email, contentDescription = "emailIcon")
                },
                label = {
                    Text(text = "Email Address")
                },
                placeholder = {
                    Text(text = "Type your email address")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = passwordText,
                onValueChange = {
                    passwordText = it
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Lock, contentDescription = "emailIcon")
                },
                label = {
                    Text(text = "Password")
                },
                placeholder = {
                    Text(text = "Type your password")
                },
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Button(
                    onClick = state.delegate.onClickLogin,
                    shape = RoundedCornerShape(12.dp),
                    elevation = ButtonDefaults.elevation()
                ) {
                    Text(text = "Login")
                }
                Button(
                    onClick = state.delegate.onClickSignUp,
                    shape = RoundedCornerShape(12.dp),
                    elevation = ButtonDefaults.elevation(),
                    modifier = Modifier.padding(start = 20.dp)
                ) {
                    Text(text = "SignUp")
                }
            }
        }
    }
}
