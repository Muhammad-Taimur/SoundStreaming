# Sound Streaming App

![Screen] (https://raw.githubusercontent.com/ReguloSarmiento/SoundStreaming/2e0e0beda8ffe7166b82dee1df2476f18e9c0062/poster.png)

SoundStreaming is an app that fetches music/tracks data using [soundcloud.com] (https://soundcloud.com/) API.

## Features:
* Search music either by artist or song
* Over 120 million music/tracks from different artists
* Shuffle and repeat mode
* Play music/tracks on background
* Forward and backward jump during playback
* Download tracks that are authorized by their authors

## Used libraries:
* [RxJava] (https://github.com/ReactiveX/RxAndroid) and [Retrofit] (http://square.github.io/retrofit/) libraries to manage Rest Client
* [ButterKnife] (http://jakewharton.github.io/butterknife/) library to bind views and avoid boilerplate views code
* [EventBus] (https://github.com/greenrobot/EventBus) library to send data between components and makes code simpler
* [Picasso] (http://square.github.io/picasso/) and [Circle ImageView] (https://github.com/hdodenhof/CircleImageView) libraries to manage images
* [Mockito] (http://site.mockito.org/) library to make unit test and [Espresso] (https://google.github.io/android-testing-support-library/docs/espresso/) library to make UI test.

## Design pattern
MVP (Model View Presenter) pattern to keep it simple and make the code testable, robust and easier to maintain

## Build from the source:

In order to build the app you must provide your own API key fom soundcloud.com.
Open local.properties file and paste your key instead of ***YOUR_API_KEY*** text in this line:
```
soundcloudapi="YOUR_API_KEY"
```

## License:
```
Copyright 2017, Regulo Sarmiento

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
