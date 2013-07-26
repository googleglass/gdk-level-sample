apk-level-sample
================

Simple APK Glassware acting as a level.

## Getting started

Checkout our documentation to learn how to get started on https://developers.google.com/glass/apk

## Running the APK on Glass

You can use your IDE to compile, install, and run the APK or use
[`adb`](https://developer.android.com/tools/help/adb.html)
on the command line:

    $ adb install -r apk-level-sample.apk
    $ adb shell am start -n com.google.glass.samples.level/.LevelActivity

Note: The Glass screen must be on when you run the APK.
