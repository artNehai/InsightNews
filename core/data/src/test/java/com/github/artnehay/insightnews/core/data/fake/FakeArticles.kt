package com.github.artnehay.insightnews.core.data.fake

import com.github.artnehay.insightnews.core.model.Article
import com.github.artnehay.insightnews.core.model.SourceHeader

val FakeArticle1 = Article(
    source = SourceHeader(
        id = "id1",
        name = ""
    ),
    author = "Jack Black",
    title = "Is Blender the Future of 3D modeling and VFX?",
    description = "Some description",
    url = "fakeUrl1",
    urlToImage = "fakeUrlToImage1",
    publishedAt = "Mar 12, 2023, 11:05:12 PM",
    timeToReadMin = 0,
    content = "Some content",
)

val FakeArticle2 = Article(
    source = SourceHeader(
        id = "id2",
        name = ""
    ),
    author = "Dwayne Johns",
    title = "Here is your title, boys",
    description = "Well, I did what I can",
    url = "fakeUrl2",
    urlToImage = "fakeUrlToImage2",
    publishedAt = "Jun 1, 1905, 1:23:45 PM",
    timeToReadMin = 0,
    content = "Content? Why do you need this?",
)