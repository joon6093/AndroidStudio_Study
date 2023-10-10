import 'package:flutter/material.dart';
import 'package:kumoh_road_demo/auth_screen/login_screen.dart';

import 'firebase_options.dart';
import 'package:firebase_core/firebase_core.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(options: DefaultFirebaseOptions.currentPlatform);
  runApp(const MyFirebaseApp());
}

class MyFirebaseApp extends StatelessWidget {
  const MyFirebaseApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      home: LoginScreen(),
    );
  }
}