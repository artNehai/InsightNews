Insight News android app - Your Gateway to Global News
=====================================
The goal of this project is to develop a cutting edge news app by leveraging the latest Android development practices and technologies.

![explore_feature_screenshot](https://github.com/artNehai/InsightNews/assets/61160804/1b6ac3bd-4fef-4d07-8ec9-3173734ad238)

> This app is in work progress and will soon be enhanced with the new features and technologies.

## Features
<ul>
  <li><strong>News Feed</strong>: Receive news articles based on your preferences</li>
  <li><strong>Save Articles</strong>: Bookmark interesting articles to read later.</li>
  <li><strong>Dark Mode Support</strong>: Enjoy reading news in low-light conditions with our dark mode feature.</li>
  <li><strong>Smooth Navigation</strong>: Intuitive navigation using Jetpack Navigation for seamless user experience.</li>
</ul>

## Architecture
The architecture is built around
[Android Architecture Components](https://developer.android.com/topic/libraries/architecture/)
and follows the recommendations laid out in the
[Guide to App Architecture](https://developer.android.com/jetpack/docs/guide). Logic is kept away
from Activities and moved to
[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)s.

## Modules Overview
<table>
  <tr>
   <td><strong>Name</strong>
   </td>
   <td><strong>Description</strong>
   </td>
   <td><strong>Key classes</strong>
   </td>
  </tr>
  <tr>
   <td><code>app</code></td>
   <td>Main entry point of the application.</td>
   <td>
     <code>InsightNewsApp, MainActivity</code> <br>
     App-level controlled navigation via <code>InsightNavHost,</code> <code>NavigationDestination</code>
   </td>
  </tr>
  <tr>
   <td><code>core:data</code></td>
   <td>Android library for the data layer.</td>
   <td>
     <code>ArticlesRepository</code>
   </td>
  </tr>
  <tr>
   <td><code>core:database</code></td>
   <td>Local database storage using Room.</td>
   <td>
     <code>NiaDatabase,</code> <br> 
     <code>Dao</code> classes
   </td>
  </tr>
  <tr>
   <td><code>core:model</code></td>
   <td>Model classes used throughout the app.</td>
   <td>
     <code>Article</code> <code>Source</code> <code>SourceHeader</code>
   </td>
  </tr>
  <tr>
   <td><code>core:network</code></td>
   <td>Making network requests and handling responses from a remote data source.</td>
   <td>
     <code>NewsApiRemoteDataSource</code>
   </td>
  </tr>
  <tr>
   <td><code>core:ui</code></td>
   <td>Contains reusable UI components and utilities.</td>
   <td>
     <code>ArticleCard</code> <code>ErrorScreen</code> <code>NoticeIcon</code>
   </td>
  </tr>
  <tr>
   <td><code>feature:explore</code></td>
   <td>Displays ExploreScreen</td>
   <td>
     <code>ExploreScreen</code> <code>ExploreViewModel</code>
   </td>
  </tr>
  <tr>
   <td><code>feature:saved</code></td>
   <td>Displays SavedScreen</td>
   <td>
     <code>SavedScreen</code>
   </td>
  </tr>
</table>

## Tech Stack
| Tech Stack | Implementation Description |
| --- | --- |
| [Jetpack Compose](https://developer.android.com/jetpack/compose) | Implement reactive UI using composable functions | 
| [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) | Act as state holder in UI layer |
| [Retrofit](https://square.github.io/retrofit/) | Fetch the latest information from the NewsApi rest server |
| [Coil](https://github.com/coil-kt/coil) | Load images from URL |
| [Room Database](https://developer.android.com/training/data-storage/room) | Save the article data locally |
| [Compose Navigation](https://developer.android.com/jetpack/compose/navigation) | Navigate to different screens in the app |
| [Scaffold](https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#scaffold) | Implement top, bottom and snack bars based on material design layout |
| [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) | Implement asynchronous flow in the app |
