package tech.thdev.Kotlin_github.data

import com.google.gson.annotations.SerializedName

data class Repositories(
        @field:SerializedName("total_count") val totalCount: Int,
        @field:SerializedName("incomplete_results") val incompleteResults: Boolean,
        @field:SerializedName("items") val items: List<RepositoriesItem>
)