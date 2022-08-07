# Github Repository Tracker
This project lists number of closed pull requests of repository details mentioned in `PullRequestActivity`.
To fetch the closed requests from your repository, edit following contacts:
```kotlin
companion object {
        private const val DEFAULT_ORG_NAME = "square"
        private const val DEFAULT_REPO_NAME = "retrofit"
    }

```

## Screenshots

<img src="https://github.com/mohitrajput987/media-repository/blob/master/kotlin/github-tracker.png" width="40%" alt="user input">

## Components Used
- MVVM architecture
- Data & View binding
- Paging library
- Live Data
- Kotlin coroutines
- Hilt
- Coroutine unit tests
