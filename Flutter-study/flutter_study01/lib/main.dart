import 'package:flutter/material.dart';
import 'first_page.dart';
import 'second_page.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Navigator Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      initialRoute: '/first', //처음 시작하는 화면 정보(
      routes: {
        '/first': (context) => FirstPage(),
        '/second': (context) => SecondPage(),
      },
    );
  }
}