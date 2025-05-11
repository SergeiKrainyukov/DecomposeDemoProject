package com.example.decomposedemoproject.feature_two.domain

import com.example.decomposedemoproject.feature_two.data.FeatureTwoRepository
import javax.inject.Inject

class GetFeatureTwoDataUseCase @Inject constructor(
    private val featureTwoRepository: FeatureTwoRepository
) {
    suspend fun invoke(id: String) = featureTwoRepository.getFeatureTwoData(id)
}