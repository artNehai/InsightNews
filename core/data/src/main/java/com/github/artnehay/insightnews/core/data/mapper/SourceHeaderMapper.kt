package com.github.artnehay.insightnews.core.data.mapper

import com.github.artnehay.insightnews.core.domain.model.SourceHeader
import com.github.artnehay.insightnews.core.network.model.SourceHeaderDto

fun SourceHeaderDto.toSourceHeader() = SourceHeader(
    id = this.id ?: "",
    name = this.name ?: "",
)