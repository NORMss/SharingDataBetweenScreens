package com.norm.mysharingdatabetweenscreens.util

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersistentViewModel1 @Inject constructor(
    private val sessionCache: SessionCache
) : ViewModel() {
    val session get() = sessionCache.getActiveSession()

    fun saveSession() {
        sessionCache.saveSession(
            session = Session(
                user = User(
                    firstName = "Sergey",
                    lastName = "Bezborodov",
                    email = "test@test.com",
                ),
                token = "1234567890",
                expiresAt = 123321
            )
        )
    }
}