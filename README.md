
![img_search](https://github.com/gurkandoner7/githubservices/assets/150553508/fbb470cd-ff09-4c07-9e39-f47ec7642126)
![img_favorites](https://github.com/gurkandoner7/githubservices/assets/150553508/a9d88270-e666-48be-86fc-532b7eb063da)
![img_detail](https://github.com/gurkandoner7/githubservices/assets/150553508/f2db261e-ae00-428c-8efe-53bca0099260)

## About The Project

 You can search for existing users on GitHub, view their profile details, and add their profiles to favorites in this application.

## Architecture
MVVM (Model-View-ViewModel) architecture pattern and view binding is used in the development of this application. The development language of the application is Kotlin.

* Architecture;
    * [View Binding](https://developer.android.com/topic/libraries/view-binding)
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    * [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) 
    * [Navigation](https://developer.android.com/guide/navigation)

* Third parties;
    * [Kotlin Coroutines (Kotlin flows)](https://developer.android.com/kotlin/flow)
    * [Flow](https://developer.android.com/kotlin/flow)
    * [Room](https://developer.android.com/training/data-storage/room)
    * [Retrofit](https://github.com/square/retrofit)
    * [Glide](https://bumptech.github.io/glide/) 
  
## Features
- When the application is opened, the search screen appears. When you perform a search on this screen, a request is sent to the server and the received response is displayed on the screen. The user list of the last search is also cached.
- Users added to favorites are also cached.
- When the user's favorite status is changed in any of the 3 screens of the application (Search, Favorites, Detail), this change is made in the local favorites table and I ensure that it is displayed correctly on other screens as well.

