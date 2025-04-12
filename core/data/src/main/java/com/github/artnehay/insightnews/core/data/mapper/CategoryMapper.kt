package com.github.artnehay.insightnews.core.data.mapper

import com.github.artnehay.insightnews.core.domain.model.Category
import com.github.artnehay.insightnews.core.network.model.CategoryDto

fun Category.toCategoryDto(): CategoryDto {
    return CategoryDto.entries.find { it.name == this.name } ?: CategoryDto.All
}