import 'package:flutter/material.dart';
import 'first_page.dart';
import 'second_page.dart';
void main() => runApp(MyApp());
class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Navigator Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const FirstPage(), //첫 페이지를 시작 페이지로 지정
    );
  }}