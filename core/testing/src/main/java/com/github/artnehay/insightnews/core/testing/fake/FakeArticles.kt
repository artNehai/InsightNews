package com.github.artnehay.insightnews.core.testing.fake

import com.github.artnehay.insightnews.core.model.Article
import com.github.artnehay.insightnews.core.model.SourceHeader

val FakeArticle1 = Article(
    source = SourceHeader(
        id = SourceId1,
        name = SourceName1,
    ),
    author = Author1,
    title = Title1,
    description = Description1,
    url = Url1,
    urlToImage = UrlToImage1,
    publishedAt = LocalPublishedAt1,
    timeToReadMin = TimeToReadMin1,
    content = Content1,
)

val FakeArticle2 = Article(
    source = SourceHeader(
        id = SourceId2,
        name = SourceName2,
    ),
    author = Author2,
    title = Title2,
    description = Description2,
    url = Url2,
    urlToImage = UrlToImage2,
    publishedAt = LocalPublishedAt2,
    timeToReadMin = TimeToReadMin2,
    content = Content2,
)