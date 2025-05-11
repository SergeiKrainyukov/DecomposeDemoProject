package com.example.decomposedemoproject.feature_one.domain

import com.example.decomposedemoproject.feature_one.data.FeatureOneRepository
import javax.inject.Inject

class GetFeatureOneDataUseCase @Inject constructor(
    private val featureOneRepository: FeatureOneRepository
) {
    suspend operator fun invoke() = featureOneRepository.getFeatureOneData()
}