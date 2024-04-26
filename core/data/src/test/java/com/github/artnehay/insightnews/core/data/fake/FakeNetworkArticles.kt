package com.github.artnehay.insightnews.core.data.fake

import com.github.artnehay.insightnews.core.network.model.NetworkArticle
import com.github.artnehay.insightnews.core.network.model.NetworkSourceHeader
import com.github.artnehay.insightnews.core.testing.fake.Author1
import com.github.artnehay.insightnews.core.testing.fake.Author2
import com.github.artnehay.insightnews.core.testing.fake.Content1
import com.github.artnehay.insightnews.core.testing.fake.Content2
import com.github.artnehay.insightnews.core.testing.fake.Description1
import com.github.artnehay.insightnews.core.testing.fake.Description2
import com.github.artnehay.insightnews.core.testing.fake.RemotePublishedAt1
import com.github.artnehay.insightnews.core.testing.fake.RemotePublishedAt2
import com.github.artnehay.insightnews.core.testing.fake.SourceId1
import com.github.artnehay.insightnews.core.testing.fake.SourceId2
import com.github.artnehay.insightnews.core.testing.fake.SourceName1
import com.github.artnehay.insightnews.core.testing.fake.SourceName2
import com.github.artnehay.insightnews.core.testing.fake.Title1
import com.github.artnehay.insightnews.core.testing.fake.Title2
import com.github.artnehay.insightnews.core.testing.fake.Url1
import com.github.artnehay.insightnews.core.testing.fake.Url2
import com.github.artnehay.insightnews.core.testing.fake.UrlToImage1
import com.github.artnehay.insightnews.core.testing.fake.UrlToImage2

val FakeNetworkArticle1 = NetworkArticle(
    networkSourceHeader = NetworkSourceHeader(
        id = SourceId1,
        name = SourceName1,
    ),
    author = Author1,
    title = Title1,
    description = Description1,
    url = Url1,
    urlToImage = UrlToImage1,
    publishedAt = RemotePublishedAt1,
    content = Content1,
)

val FakeNetworkArticle2 = NetworkArticle(
    networkSourceHeader = NetworkSourceHeader(
        id = SourceId2,
        name = SourceName2,
    ),
    author = Author2,
    title = Title2,
    description = Description2,
    url = Url2,
    urlToImage = UrlToImage2,
    publishedAt = RemotePublishedAt2,
    content = Content2,
)