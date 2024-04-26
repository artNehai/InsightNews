package com.github.artnehay.insightnews.core.data.fake

import com.github.artnehay.insightnews.core.model.Article
import com.github.artnehay.insightnews.core.model.SourceHeader

val FakeArticle = Article(
    source = SourceHeader(id = "", name = ""),
    author = "Jack Black",
    title = "Is Blender the Future of 3D modeling and VFX?",
    description = "Some description",
    url = "fakeUrl",
    urlToImage = "fakeUrlToImage",
    publishedAt = "Mar 12, 2023, 11:05:12 PM",
    timeToReadMin = 0,
    content = "Some content",
)