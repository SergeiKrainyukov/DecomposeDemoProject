package com.example.decomposedemoproject.feature_two.data

import kotlinx.coroutines.delay
import javax.inject.Inject

class FeatureTwoRepository @Inject constructor() {
    suspend fun getFeatureTwoData(id: String): Result<String> {
        delay(1500)
        return Result.success("Feature Two")
    }
}