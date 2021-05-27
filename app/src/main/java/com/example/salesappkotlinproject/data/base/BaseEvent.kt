package com.example.salesappkotlinproject.data.base

import com.example.salesappkotlinproject.data.model.SoldProduct

sealed class BaseEvent {
    class Error(message: String) : BaseEvent()
    class Success<T>(result: T) : BaseEvent()
    class Loading(state: Boolean) : BaseEvent()
}

sealed class SoldProductEvent : BaseEvent() {
    class SoldProductGot(val array: MutableList<SoldProduct>) : SoldProductEvent()
}