package com.example.travenorcustomer.features.signIn

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
fun SignInScreen(
    modifier: Modifier = Modifier,
    onNavigationBack: () -> Unit,
    onNavigationToVerificationScreen: (String) -> Unit,
    viewModel: SignInViewModel = koinViewModel()
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is SignInEvent.NavigateToVerification ->
                    onNavigationToVerificationScreen(event.email)

                SignInEvent.NavigateBack ->
                    onNavigationBack()
            }
        }
    }

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
            text = "Sign in now",
            fontFamily = FontFamily(
                Font(R.font.sf_ui_display_semibold)
            ),
            fontSize = 26.sp,
        )

        Spacer(Modifier.height(15.dp))


        Text(
            text = "Please sign in to continue our app",
            fontFamily = FontFamily(
                Font(R.font.sf_ui_display_regular)
            ),
            fontSize = 16.sp,
            color = AppColors.lightSub
        )

        Spacer(Modifier.height(60.dp))

        TravenorTextField(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            value = state.email,
            onValueChange = {
                viewModel.onAction(SignInAction.OnEmailChange(it))
            },
            placeholder = "email",
            errorMsg = state.error
        )

        Spacer(Modifier.height(25.dp))

        TravenorTextButton(
            onClick = {
                viewModel.onAction(SignInAction.OnSignIn)
            },
            btnText = if (state.isLoading) "Signing..." else "Sign In"
        )
    }

}