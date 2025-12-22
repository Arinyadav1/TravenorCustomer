package com.example.travenorcustomer.features.otpVerification

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.travenorcustomer.R
import com.example.travenorcustomer.features.components.TravenorIconButton
import com.example.travenorcustomer.features.components.TravenorTextButton
import com.example.travenorcustomer.features.components.TravenorTextField
import com.example.travenorcustomer.ui.theme.AppColors
import org.koin.androidx.compose.koinViewModel

@Composable
fun VerificationScreen(
    modifier: Modifier = Modifier,
    onNavigationBack: () -> Unit,
    onNavigationToHomeScreen: () -> Unit,
    viewModel: VerificationViewModel = koinViewModel()
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                VerificationEvent.OnNavigationBack -> onNavigationBack()
                VerificationEvent.OnNavigationToHomeScreen -> onNavigationToHomeScreen()
            }
        }
    }

    val focusRequesters = List(state.otpValues.size) { FocusRequester() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TravenorIconButton(
                onClick = {
                    onNavigationBack()
                }
            )
        }

        Spacer(Modifier.height(130.dp))

        Text(
            text = "OTP Verification",
            fontFamily = FontFamily(
                Font(R.font.sf_ui_display_semibold)
            ),
            fontSize = 26.sp,
        )

        Spacer(Modifier.height(15.dp))


        Text(
            text = "Please check your email ${state.email} to see the verification code",
            fontFamily = FontFamily(
                Font(R.font.sf_ui_display_regular)
            ),
            fontSize = 16.sp,
            color = AppColors.lightSub,
            textAlign = TextAlign.Center,
            lineHeight = 20.sp
        )

        Spacer(Modifier.height(40.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            state.otpValues.forEachIndexed { index, value ->
                TravenorTextField(
                    modifier = Modifier.focusRequester(focusRequesters[index]),
                    value = value,
                    onValueChange = { newValue ->
                        if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                            viewModel.onAction(VerificationAction.OnOtpValueChange(index, newValue))

                            if (newValue.isNotEmpty() && index < state.otpValues.lastIndex) {
                                focusRequesters[index + 1].requestFocus()
                            }
                        }
                    },
                )
            }
        }
        if (state.error != null) {
            Text(
                text = state.error,
                color = Color.Red
            )
        }


        Spacer(Modifier.height(25.dp))


        TravenorTextButton(
            onClick = { viewModel.onAction(VerificationAction.OnVerify) },
            btnText = if (state.loading) "Verifying..." else "Verify",
            enabled = state.otpValues[state.otpValues.lastIndex].isNotEmpty()
        )

        Spacer(Modifier.height(15.dp))

        if (state.isShowResend) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        onClick = {
                            viewModel.onAction(VerificationAction.OnResendHandle)
                        }
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = if (state.resendLoading) "Resending code" else "Resend code to",
                    fontFamily = FontFamily(
                        Font(R.font.sf_ui_display_regular)
                    ),
                    fontSize = 14.sp,
                    color = AppColors.lightSub,
                    textAlign = TextAlign.Center
                )
                if (state.countDownTimer != null) {
                    Text(
                        text = state.countDownTimer,
                        fontFamily = FontFamily(
                            Font(R.font.sf_ui_display_regular)
                        ),
                        fontSize = 14.sp,
                        color = AppColors.lightSub,
                    )
                }
            }
        }
    }
}