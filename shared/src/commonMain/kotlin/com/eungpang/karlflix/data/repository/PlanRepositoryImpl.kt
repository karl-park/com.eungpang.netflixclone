package com.eungpang.karlflix.data.repository

import com.eungpang.karlflix.domain.model.Plan
import com.eungpang.karlflix.domain.repository.PlanRepository
import kotlinx.coroutines.delay

class PlanRepositoryImpl(

): PlanRepository {
    private val mockPlans = listOf(
        Plan(
            name = Plan.Types.Basic.name,
            videoQuality = "Good",
            monthlyPrice = "$12.98",
            resolution = "480p"
        ),
        Plan(
            name = Plan.Types.Standard.name,
            videoQuality = "Better",
            monthlyPrice = "$17.48",
            resolution = "1080p"
        ),
        Plan(
            name = Plan.Types.Premium.name,
            videoQuality = "Best",
            monthlyPrice = "$21.98",
            resolution = "4K+HDR"
        )
    )

    override suspend fun retrieveContent(): Result<List<Plan>> {
        // mocking the network delay
        delay(100L)
        return Result.success(mockPlans)
    }
}