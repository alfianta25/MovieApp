package com.yoga.movieapp.utils.connection

sealed class ConnectionState {
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}