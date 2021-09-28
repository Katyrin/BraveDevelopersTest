package com.katyrin.bravedeveloperstest.model.entities

import com.katyrin.bravedeveloperstest.model.entities.NamedApiResource

data class NamedApiResourceList(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<NamedApiResource>
)
