package com.github.artnehay.insightnews.core.data.fake

import com.github.artnehay.insightnews.core.network.model.NetworkArticle
import com.github.artnehay.insightnews.core.network.model.NetworkSourceHeader

val FakeNetworkArticle1 = NetworkArticle(
    networkSourceHeader = NetworkSourceHeader(
        id = "id1",
        name = ""
    ),
    author = "Jack Black",
    title = "Is Blender the Future of 3D modeling and VFX?",
    description = "Some description",
    url = "fakeUrl1",
    urlToImage = "fakeUrlToImage1",
    publishedAt = "2023-03-12T20:05:12Z",
    content = "Some content",
)

val FakeNetworkArticle2 = NetworkArticle(
    networkSourceHeader = NetworkSourceHeader(
        id = "id2",
        name = ""
    ),
    author = "Dwayne Johns",
    title = "Here is your title, boys",
    description = "Well, I did what I can",
    url = "fakeUrl2",
    urlToImage = "fakeUrlToImage2",
    publishedAt = "1905-06-01T11:33:45Z",
    content = "Content? Why do you need this?",
)