package com.sample.app.fetchrewardscodingexercise.presentation.common

// We can use this to show toast or snackbar when needed
sealed class UiEvent {
    data object PopBackStack: UiEvent()
    data class Navigate(val route: String): UiEvent()
    data class ShowSnackBar(
        val message: String,
        val action: String? = null
    ): UiEvent()
    data class ShowToast(
        val message: String,
        val duration: Int
    ): UiEvent()
    data object ShowFilterMenu: UiEvent()

}
