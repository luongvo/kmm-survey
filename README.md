# KMM Survey

A Nimble Survey KMM project.

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
