package com.example.decomposedemoproject.feature_one.data

import kotlinx.coroutines.delay
import javax.inject.Inject

class FeatureOneRepository @Inject constructor() {
    suspend fun getFeatureOneData(): Result<String> {
        delay(1000)
        return Result.success("Feature One")
    }
}