package com.example.travenorcustomer.features.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.toRoute
import com.example.travenorcustomer.data.DestinationsRepository
import com.example.travenorcustomer.features.BaseViewModel
import com.example.travenorcustomer.features.navigation.DetailScreenRoute
import com.example.travenorcustomer.model.Destination
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class DetailsViewModel(
    private val destinationsRepository: DestinationsRepository,
    val savedStateHandle: SavedStateHandle
) : BaseViewModel<DetailsState, DetailsAction, DetailsEvent>(initialState = DetailsState()) {

    private val route = savedStateHandle.toRoute<DetailScreenRoute>()

    init {
        getDestination(route.destinationId)
    }

    private fun getDestination(id: String) {
        viewModelScope.launch {
            destinationsRepository.getDestination(id)
                .onStart {
                    mutableStateFlow.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                .catch { error ->

                }
                .collect { data ->
                    mutableStateFlow.update {
                        it.copy(
                            destination = data,
                            isLoading = false
                        )
                    }
                }
        }
    }

    override fun handleAction(action: DetailsAction) {

    }

}


data class DetailsState(
    val destination: Destination? = null,
    val isLoading: Boolean = false
)

sealed interface DetailsAction {

}

sealed interface DetailsEvent {

}