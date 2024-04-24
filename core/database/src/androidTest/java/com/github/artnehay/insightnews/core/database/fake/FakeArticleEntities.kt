package com.github.artnehay.insightnews.core.database.fake

import com.github.artnehay.insightnews.core.database.ArticleEntity

val FakeArticleEntity1 = ArticleEntity(
    sourceId = "id1",
    author = "Jack Black",
    title = "Is Blender the Future of 3D modeling and VFX?",
    description = "Some description",
    url = "fakeUrl1",
    urlToImage = "fakeUrlToImage1",
    publishedAt = "12-03-2023",
    timeToReadMin = 12,
    content = "Some content",
)

val FakeArticleEntity2 = ArticleEntity(
    sourceId = "id2",
    author = "Dwayne Johns",
    title = "Here is your title, boys",
    description = "Well, I did what I can",
    url = "fakeUrl2",
    urlToImage = "fakeUrlToImage2",
    publishedAt = "01-06-1905",
    timeToReadMin = 7,
    content = "Content? Why do you need this?",
)