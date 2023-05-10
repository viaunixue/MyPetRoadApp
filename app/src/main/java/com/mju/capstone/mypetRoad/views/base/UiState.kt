package com.mju.capstone.mypetRoad.views.base

sealed class UiState<out T>  {
    data class Loading(val data: Boolean): UiState<Nothing>()

    object Uninitialized: UiState<Nothing>()

    data class Success<T>(val data: T?): UiState<T>()

    data class Fail<T>(val data: T?): UiState<T>()

    data class Error(val error: Throwable?): UiState<Nothing>()
}

fun <T> UiState<T>.successOrNull(): T? = if (this is UiState.Success<T>) {
    data
} else {
    null
}

fun <T> UiState<T>.throwableOrNull(): Throwable? = if (this is UiState.Error) {
    error
} else {
    null
}

fun <T> UiState<T>.failOrNull(): T? = if (this is UiState.Fail<T>) {
    data
} else {
    null
}