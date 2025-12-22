package com.example.travenorcustomer.features.signIn

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.example.travenorcustomer.data.AuthenticationRepository
import com.example.travenorcustomer.features.BaseViewModel
import com.example.travenorcustomer.network.BuildConfig
import io.github.jan.supabase.auth.exception.AuthRestException
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(
    val repository: AuthenticationRepository
) : BaseViewModel<SignInState, SignInAction, SignInEvent>(SignInState()) {

    private fun otpGeneration(email: String) {
        val isValidateEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        mutableStateFlow.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            if (!isValidateEmail) {
                mutableStateFlow.update {
                    it.copy(
                        error = "Enter valid email address",
                        isLoading = false
                    )
                }
            } else {
                try {
                    repository.otpGeneration(email = email)

                    mutableStateFlow.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                    sendEvent(
                        SignInEvent.NavigateToVerification(
                            email = email,
                        )
                    )
                } catch (e: AuthRestException) {
                    Log.d("ARINYADAV", e.message.toString())
                    mutableStateFlow.update {
                        it.copy(
                            error = "Unexpected Auth Error",
                            isLoading = false
                        )
                    }
                } catch (e: Throwable) {
                    Log.d("ARINYADAV", e.message.toString())
                    mutableStateFlow.update {
                        it.copy(
                            error = "Unexpected Error",
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    override fun handleAction(action: SignInAction) {
        when (action) {
            is SignInAction.OnEmailChange -> {
                mutableStateFlow.update {
                    it.copy(
                        email = action.email,
                        error = null
                    )
                }
            }

            SignInAction.OnSignIn -> {
                otpGeneration(state.email)
            }

            SignInAction.OnNavigationBack -> {
                sendEvent(SignInEvent.NavigateBack)
            }
        }
    }

}


sealed interface SignInEvent {
    data class NavigateToVerification(val email: String) : SignInEvent
    object NavigateBack : SignInEvent
}

data class SignInState(
    val email: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
)

sealed class SignInAction {
    data class OnEmailChange(val email: String) : SignInAction()
    object OnSignIn : SignInAction()
    object OnNavigationBack : SignInAction()
}
