package com.eungpang.karlflix.domain.repository

import com.eungpang.karlflix.domain.model.Plan

interface PlanRepository : ContentRepository<List<Plan>> {
    override suspend fun retrieveContent(): Result<List<Plan>>
}
