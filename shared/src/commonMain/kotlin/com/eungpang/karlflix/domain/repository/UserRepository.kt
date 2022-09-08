package com.eungpang.karlflix.domain.repository

import com.eungpang.karlflix.domain.model.AccountInfo
import com.eungpang.karlflix.domain.model.Profile
import com.eungpang.karlflix.domain.model.SignUpUser
import com.eungpang.karlflix.domain.model.User

interface UserRepository {
    suspend fun signup(user: SignUpUser): Result<Unit>

    suspend fun signin(accountInfo: AccountInfo): Result<User>
    suspend fun signin(token: String): Result<User>

    suspend fun addProfile(email: String, profile: Profile): Result<User>
    suspend fun removeProfile(email: String, profile: Profile): Result<User>
}