package com.eungpang.karlflix.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String,
    val plan: String,
    val profiles: List<Profile>
)

@Serializable
data class Profile(
    val name: String,
    val profileImageUrl: String,
)

@Serializable
data class AccountInfo(
    val email: String,
    val password: String,
)

@Serializable
data class SignUpUser(
    val accountInfo: AccountInfo,
    val plan: String
)

@Serializable
data class Plan(
    val name: String, // Basic, Standard, Premium
    val videoQuality: String,
    val monthlyPrice: String,
    val resolution: String,
) {
    enum class Types {
        Basic, Standard, Premium
    }
}
