package com.example.travenorcustomer.features.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travenorcustomer.features.details.DetailsScreen
import com.example.travenorcustomer.features.home.HomeScreen
import com.example.travenorcustomer.features.onBoard.OnBoardScreen
import com.example.travenorcustomer.features.otpVerification.VerificationScreen
import com.example.travenorcustomer.features.signIn.SignInScreen
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
object OnBoardScreenRoute

@Serializable
object SignInScreenRoute

@Serializable
object HomeScreenRoute

@Serializable
data class DetailScreenRoute(val destinationId: String)

@Serializable
data class VerificationScreenRoute(val email: String)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppRoot(
    modifier: Modifier = Modifier,
    viewModel: AppRootViewModel = koinViewModel()
) {
    val navController = rememberNavController()

    val session = viewModel.state.collectAsStateWithLifecycle()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = when (session.value) {
                is SessionStatus.Authenticated -> HomeScreenRoute
                is SessionStatus.NotAuthenticated -> OnBoardScreenRoute
                else -> HomeScreenRoute
            }
        ) {
            composable<OnBoardScreenRoute> {
                OnBoardScreen(
                    modifier = modifier,
                    navigateToSignInScreen = navController::navigateToSignInScreen
                )
            }

            composable<SignInScreenRoute> {
                SignInScreen(
                    modifier = modifier.padding(innerPadding),
                    onNavigationBack = navController::popBackStack,
                    onNavigationToVerificationScreen = navController::navigateToVerificationScreen
                )
            }

            composable<VerificationScreenRoute> {
                VerificationScreen(
                    modifier = modifier.padding(innerPadding),
                    onNavigationBack = navController::popBackStack,
                    onNavigationToHomeScreen = navController::navigateToHomeScreen
                )
            }

            composable<HomeScreenRoute> {
                HomeScreen(
                    modifier = modifier.padding(innerPadding),
                    navigateToDetailScreen = navController::navigateToDetailScreen
                )
            }

            composable<DetailScreenRoute> {
                DetailsScreen(
                    modifier = modifier,
                    onNavigationBack = navController::popBackStack
                )
            }
        }
    }
}

fun NavController.navigateToHomeScreen() {
    this.navigate(HomeScreenRoute)
}

fun NavController.navigateToSignInScreen() {
    this.navigate(SignInScreenRoute)
}

fun NavController.navigateToVerificationScreen(email: String) {
    this.navigate(VerificationScreenRoute(email = email))
}

fun NavController.navigateToDetailScreen(destinationId: String) {
    this.navigate(DetailScreenRoute(destinationId = destinationId))
}