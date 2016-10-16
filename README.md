# SoundStreaming

SoundStreaming is an app that makes use of SoundCloud API, which is a social network that is used primarily for the distribution of audio tracks. Basically, this app allows to search for music, tracks or songs and play them in streaming. 

# About the code and technologies used to perform this app

In order to make the code testable, robust and easier to maintain the app used:

- MVP (Model View Presenter) pattern to keep it simple
- RxJava and Retrofit libraries to manage Rest Client
- ButterKnife library to bind views and avoid boilerplate views code
- EventBus library to send data between components and makes code simpler
- Picasso and Circle ImageView libraries to manage images. 
- Mockito library to make unit test and Espresso library to make UI test.
