package com.eungpang.karlflix.data.repository

import com.eungpang.karlflix.domain.SharedPreferences
import com.eungpang.karlflix.domain.model.AccountInfo
import com.eungpang.karlflix.domain.model.Profile
import com.eungpang.karlflix.domain.model.SignUpUser
import com.eungpang.karlflix.domain.model.User
import com.eungpang.karlflix.domain.repository.UserRepository
import com.russhwolf.settings.get
import kotlinx.coroutines.delay
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UserRepositoryImpl(
    private val sharedPreferences: SharedPreferences,
): UserRepository {

    companion object {
        private const val DELIMITER = "||"

        private const val PREF_KEY_TOKEN = "access_token"
        private const val PREF_KEY_PROFILE = "profile$DELIMITER"
        private const val PREF_KEY_SIGNUP_USER = "signUpUser$DELIMITER"
        private const val PREF_KEY_LAST_LOGGEDIN_SIGNUP_USER = "lastloggedinSignUpUser$DELIMITER"
    }

    override suspend fun signup(user: SignUpUser): Result<Unit> {
        delay(100)

        val key = "$PREF_KEY_SIGNUP_USER${user.accountInfo.email}"
        val signUpUserStringInSharedPref = sharedPreferences.get<String>(key)
        if (signUpUserStringInSharedPref != null) {
            return Result.failure(RuntimeException("The user(${user.accountInfo.email}) has already been registered to the service."))
        }

        val jsonString = Json.encodeToString(user)
        sharedPreferences.putString(key, jsonString)

        return Result.success(Unit)
    }

    override suspend fun signin(accountInfo: AccountInfo): Result<User> {
        delay(100)

        val key = "$PREF_KEY_SIGNUP_USER${accountInfo.email}"
        val signUpUserStringInSharedPref = sharedPreferences.get<String>(key)
            ?: return Result.failure(RuntimeException("The user(${accountInfo.email}) doesn't have an account on our service."))

        val profileKey = "$PREF_KEY_PROFILE${accountInfo.email}"
        val profileString = sharedPreferences.get<String>(profileKey)

        val signUpUser = Json.decodeFromString<SignUpUser>(signUpUserStringInSharedPref)

        val profiles = if (profileString == null) {
            emptyList<Profile>()
        } else {
            Json.decodeFromString(profileString)
        }

        sharedPreferences.putString(PREF_KEY_TOKEN, getToken())

        sharedPreferences.putString(PREF_KEY_LAST_LOGGEDIN_SIGNUP_USER, signUpUserStringInSharedPref)
        val user = User(
            email = accountInfo.email,
            plan = signUpUser.plan,
            profiles = profiles,
        )

        return Result.success(user)
    }

    override suspend fun signin(token: String): Result<User> {
        delay(100)

        if (isValidToken(token).not()) {
            return Result.failure(RuntimeException("The token($token) is not valid."))
        }

        val lastSignInUserStringInSharedPref = sharedPreferences.get<String>(PREF_KEY_LAST_LOGGEDIN_SIGNUP_USER)
            ?: return Result.failure(RuntimeException("The session has been expired."))

        val signUpUser = Json.decodeFromString<SignUpUser>(lastSignInUserStringInSharedPref)

        return signin(signUpUser.accountInfo)
    }

    override suspend fun addProfile(email: String, profile: Profile): Result<User> {
        val signUpUserPrefKey = "$PREF_KEY_SIGNUP_USER${email}"
        val profileKey = "$PREF_KEY_PROFILE$email"
        val profileString = sharedPreferences.get<String>(profileKey)
        val profiles = if (profileString == null) {
            mutableListOf<Profile>()
        } else {
            Json.decodeFromString(profileString)
        }

        val updatedProfileString = Json.encodeToString(profiles.add(profile))
        sharedPreferences.putString(profileKey, updatedProfileString)

        val signUpUserStringInSharedPref = sharedPreferences.get<String>(signUpUserPrefKey)
            ?: return Result.failure(RuntimeException("The user(${email}) doesn't have an account on our service."))
        val signUpUser = Json.decodeFromString<SignUpUser>(signUpUserStringInSharedPref)

        val user = User(
            email = signUpUser.accountInfo.email,
            plan = signUpUser.plan,
            profiles = profiles,
        )

        return Result.success(user)
    }

    override suspend fun removeProfile(email: String, profile: Profile): Result<User> {
        val signUpUserPrefKey = "$PREF_KEY_SIGNUP_USER${email}"
        val profileKey = "$PREF_KEY_PROFILE$email"
        val profileString = sharedPreferences.get<String>(profileKey)
        val profiles = if (profileString == null) {
            mutableListOf<Profile>()
        } else {
            Json.decodeFromString(profileString)
        }

        val updatedProfileString = Json.encodeToString(profiles.remove(profile))
        sharedPreferences.putString(profileKey, updatedProfileString)

        val signUpUserStringInSharedPref = sharedPreferences.get<String>(signUpUserPrefKey)
            ?: return Result.failure(RuntimeException("The user(${email}) doesn't have an account on our service."))
        val signUpUser = Json.decodeFromString<SignUpUser>(signUpUserStringInSharedPref)

        val user = User(
            email = signUpUser.accountInfo.email,
            plan = signUpUser.plan,
            profiles = profiles,
        )

        return Result.success(user)
    }

    private suspend fun isValidToken(token: String) : Boolean {
        val savedToken = sharedPreferences.get<String>(PREF_KEY_TOKEN) ?: return false

        return (token == savedToken)
    }

    private suspend fun getToken(): String {
        val alphaNumericList = ("a".toInt().."Z".toInt()).toMutableSet().apply {
            addAll((0..9).toList())
        }
        val token = StringBuilder().apply {
            repeat(4) {
                repeat(4) {
                    val ch = alphaNumericList.random()
                    append(ch)
                }

                if (it != 3) append("-")
            }
        }

        return token.toString()
    }
}
