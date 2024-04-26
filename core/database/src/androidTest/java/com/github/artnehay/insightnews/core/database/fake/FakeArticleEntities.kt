package com.github.artnehay.insightnews.core.database.fake

import com.github.artnehay.insightnews.core.database.ArticleEntity
import com.github.artnehay.insightnews.core.testing.fake.Author1
import com.github.artnehay.insightnews.core.testing.fake.Author2
import com.github.artnehay.insightnews.core.testing.fake.Content1
import com.github.artnehay.insightnews.core.testing.fake.Content2
import com.github.artnehay.insightnews.core.testing.fake.Description1
import com.github.artnehay.insightnews.core.testing.fake.Description2
import com.github.artnehay.insightnews.core.testing.fake.LocalPublishedAt1
import com.github.artnehay.insightnews.core.testing.fake.LocalPublishedAt2
import com.github.artnehay.insightnews.core.testing.fake.SourceId1
import com.github.artnehay.insightnews.core.testing.fake.SourceId2
import com.github.artnehay.insightnews.core.testing.fake.TimeToReadMin1
import com.github.artnehay.insightnews.core.testing.fake.TimeToReadMin2
import com.github.artnehay.insightnews.core.testing.fake.Title1
import com.github.artnehay.insightnews.core.testing.fake.Title2
import com.github.artnehay.insightnews.core.testing.fake.Url1
import com.github.artnehay.insightnews.core.testing.fake.Url2
import com.github.artnehay.insightnews.core.testing.fake.UrlToImage1
import com.github.artnehay.insightnews.core.testing.fake.UrlToImage2

val FakeArticleEntity1 = ArticleEntity(
    sourceId = SourceId1,
    author = Author1,
    title = Title1,
    description = Description1,
    url = Url1,
    urlToImage = UrlToImage1,
    publishedAt = LocalPublishedAt1,
    timeToReadMin = TimeToReadMin1,
    content = Content1,
)

val FakeArticleEntity2 = ArticleEntity(
    sourceId = SourceId2,
    author = Author2,
    title = Title2,
    description = Description2,
    url = Url2,
    urlToImage = UrlToImage2,
    publishedAt = LocalPublishedAt2,
    timeToReadMin = TimeToReadMin2,
    content = Content2,
)