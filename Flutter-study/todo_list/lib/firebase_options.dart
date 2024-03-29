// File generated by FlutterFire CLI.
// ignore_for_file: lines_longer_than_80_chars, avoid_classes_with_only_static_members
import 'package:firebase_core/firebase_core.dart' show FirebaseOptions;
import 'package:flutter/foundation.dart'
    show defaultTargetPlatform, kIsWeb, TargetPlatform;

/// Default [FirebaseOptions] for use with your Firebase apps.
///
/// Example:
/// ```dart
/// import 'firebase_options.dart';
/// // ...
/// await Firebase.initializeApp(
///   options: DefaultFirebaseOptions.currentPlatform,
/// );
/// ```
class DefaultFirebaseOptions {
  static FirebaseOptions get currentPlatform {
    if (kIsWeb) {
      return web;
    }
    switch (defaultTargetPlatform) {
      case TargetPlatform.android:
        return android;
      case TargetPlatform.iOS:
        return ios;
      case TargetPlatform.macOS:
        throw UnsupportedError(
          'DefaultFirebaseOptions have not been configured for macos - '
          'you can reconfigure this by running the FlutterFire CLI again.',
        );
      case TargetPlatform.windows:
        throw UnsupportedError(
          'DefaultFirebaseOptions have not been configured for windows - '
          'you can reconfigure this by running the FlutterFire CLI again.',
        );
      case TargetPlatform.linux:
        throw UnsupportedError(
          'DefaultFirebaseOptions have not been configured for linux - '
          'you can reconfigure this by running the FlutterFire CLI again.',
        );
      default:
        throw UnsupportedError(
          'DefaultFirebaseOptions are not supported for this platform.',
        );
    }
  }

  static const FirebaseOptions web = FirebaseOptions(
    apiKey: 'AIzaSyDfVD-AaIv_ZZFfD_3FY-vYoB9DzA45CyM',
    appId: '1:27595142796:web:6d0bbc20752e3ae3592da9',
    messagingSenderId: '27595142796',
    projectId: 'my-to-do-list-e4b4f',
    authDomain: 'my-to-do-list-e4b4f.firebaseapp.com',
    storageBucket: 'my-to-do-list-e4b4f.appspot.com',
  );

  static const FirebaseOptions android = FirebaseOptions(
    apiKey: 'AIzaSyACTENGmxEcylCs2LQDBIJ0bn8UZL9SmSw',
    appId: '1:27595142796:android:f38f63c64a784afa592da9',
    messagingSenderId: '27595142796',
    projectId: 'my-to-do-list-e4b4f',
    storageBucket: 'my-to-do-list-e4b4f.appspot.com',
  );

  static const FirebaseOptions ios = FirebaseOptions(
    apiKey: 'AIzaSyDJ81XKSsb-ay2pB5yD2gEpLJWJEl4B99U',
    appId: '1:27595142796:ios:a81d716fe7cfd3b0592da9',
    messagingSenderId: '27595142796',
    projectId: 'my-to-do-list-e4b4f',
    storageBucket: 'my-to-do-list-e4b4f.appspot.com',
    iosBundleId: 'com.example.todoList',
  );
}
