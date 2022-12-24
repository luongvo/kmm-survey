![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/luongvo/kmm-survey/review_pull_request.yml)
![GitHub top language](https://img.shields.io/github/languages/top/luongvo/kmm-survey)
[![codecov](https://codecov.io/gh/luongvo/kmm-survey/branch/develop/graph/badge.svg?token=ZGBHJ5WKWR)](https://codecov.io/gh/luongvo/kmm-survey)
[![Firebase App Distribution](https://img.shields.io/badge/Firebase-Android-green)](https://appdistribution.firebase.dev/i/11223fc5713bc511)
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

# KMM Survey

A Nimble Survey KMM project.

## Technologies

- [BuildKonfig](https://github.com/yshrsmz/BuildKonfig) embedding values from gradle file into BuildKonfig.
- [Koin](https://github.com/InsertKoinIO/koin) as lightweight DI framework.
- Networking
  - [Ktor](https://ktor.io/docs/getting-started-ktor-client-multiplatform-mobile.html#ktor-dependencies) as networking
    client.
  - [kotlin-serialization](https://github.com/Kotlin/kotlinx.serialization) for data serialization.
  - [jsonapi-kotlin](https://github.com/nimblehq/jsonapi-kotlin) for [JSON-API](https://jsonapi.org/) parsing.
- Logging with [Napier](https://github.com/AAkira/Napier).
- Settings & Storage
  - [multiplatform-settings](https://github.com/russhwolf/multiplatform-settings) for saving simple key-value data.
    - Android: use [androidx-security-crypto](https://developer.android.com/jetpack/androidx/releases/security).
    - iOS: use [KeychainSettings](https://github.com/russhwolf/multiplatform-settings#platform-constructors).
- Testing
  - [mockative](https://github.com/mockative/mockative) for mockking shared module.
  - [Kotest](https://github.com/kotest/kotest) for additional assertions, property testing and data driven testing.
  - [Turbine](https://github.com/cashapp/turbine) as testing library for kotlinx.coroutines Flow.
- Code coverage by [Kover](https://github.com/Kotlin/kotlinx-kover).
- Code analysis with [Lint](https://developer.android.com/studio/write/lint)
  and [Detekt](https://github.com/detekt/detekt).
- Annotation processing with [KSP](https://kotlinlang.org/docs/ksp-multiplatform.html).

### Android

- [Jetpack Compose](https://developer.android.com/jetpack/compose).
- [Koin](https://github.com/InsertKoinIO/koin).
- Logging with [Timber](https://github.com/JakeWharton/timber).
- Testing: [MockK](https://github.com/mockk/mockk), [Kotest](https://github.com/kotest/kotest), and [Turbine](https://github.com/cashapp/turbine).
- Distributing to [Firebase](https://appdistribution.firebase.dev/i/11223fc5713bc511).

### iOS

- TBD

## Get Started

### Usage

Clone the repository

`git clone git@github.com:luongvo/kmm-survey.git`

### Environments

- Build types
  - `debug`
  - `release`

- Environment
  - `staging`: local development and testing distribution.
  - `production`: production deployment.

## Linter and static code analysis

- Lint:

```
$ ./gradlew lint
```

Report is located at: `./android/build/reports/` and `./shared/build/reports/`

- Detekt

```
$ ./gradlew detekt
```

Report is located at: `./build/reports/detekt`

## Testing

- Run unit testing:

```
$ ./gradlew android:testStagingDebugUnitTest
$ ./gradlew shared:testDebugUnitTest
```

- Run unit testing with coverage:

```
$ ./gradlew koverMergedHtmlReport
```

Report is located at: `./build/reports/kover/`

## License

This project is Copyright (c) 2014 and onwards Nimble. It is free software and may be redistributed under the terms
specified in the [LICENSE] file.

[LICENSE]: /LICENSE

## About

<a href="https://nimblehq.co/">
  <picture>
    <source media="(prefers-color-scheme: dark)" srcset="https://assets.nimblehq.co/logo/dark/logo-dark-text-160.png">
    <img alt="Nimble logo" src="https://assets.nimblehq.co/logo/light/logo-light-text-160.png">
  </picture>
</a>

This project is maintained and funded by Nimble.

We ❤️ open source and do our part in sharing our work with the community!
See [our other projects][community] or [hire our team][hire] to help build your product.

Want to join? [Check out our Jobs][jobs]!

[community]: https://github.com/nimblehq
[hire]: https://nimblehq.co/
[jobs]: https://jobs.nimblehq.co/
