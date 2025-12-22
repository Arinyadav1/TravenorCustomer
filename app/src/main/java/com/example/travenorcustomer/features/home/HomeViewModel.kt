package com.example.travenorcustomer.features.home

import com.example.travenorcustomer.data.AuthenticationRepository
import com.example.travenorcustomer.features.BaseViewModel
import kotlinx.coroutines.flow.update

class HomeViewModel(
    val repository: AuthenticationRepository
) : BaseViewModel<HomeState, HomeAction, HomeEvent>(initialState = HomeState()) {

    init {
        mutableStateFlow.update {
            it.copy(
                userName = repository.userInfo()?.email?.substringBefore("@") ?: ""
            )
        }
    }

    override fun handleAction(action: HomeAction) {
        when(action){
            HomeAction.OnNavigateToDetailScreen -> {
                sendEvent(HomeEvent.NavigateToDetailScreen(""))
            }
        }
    }
}

data class HomeState(
    val userName: String = ""
)

sealed class HomeEvent() {
    data class NavigateToDetailScreen(val destinationId : String) : HomeEvent()
}

sealed class HomeAction() {
    data object OnNavigateToDetailScreen : HomeAction()
}