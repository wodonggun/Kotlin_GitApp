package tech.thdev.Kotlin_github.data

import com.google.gson.annotations.SerializedName

data class License(
        @field:SerializedName("key") val key: String,
        @field:SerializedName("name") val name: String)