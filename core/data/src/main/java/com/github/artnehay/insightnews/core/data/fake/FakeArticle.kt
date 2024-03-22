package com.github.artnehay.insightnews.core.data.fake

import com.github.artnehay.insightnews.core.model.Article
import com.github.artnehay.insightnews.core.model.SourceHeader

val FakeArticle = Article(
    source = SourceHeader(id = "", name = "UX Collective"),
    author = "Jack Black",
    title = "Is Blender the Future of 3D modeling and VFX?",
    description = "Some description",
    url = "fakeUrl",
    urlToImage = "fakeUrlToImage",
    publishedAt = "12-03-2023",
    content = "Some content"
)