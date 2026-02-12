package dev.suresh.coreNetwork.model

import kotlinx.serialization.Serializable


@Serializable
data class MovieDTO(
    val id: Int,
    val poster_path: String?,
    val title: String
)