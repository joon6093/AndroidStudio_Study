import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'first_page.dart';
import 'second_page.dart';
import 'counter.dart';

void main() => runApp(new MyApp());

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider<Counter>(
      create: (_) => Counter(),
      child: MaterialApp(
        title: 'Navigator Demo',
        theme: ThemeData(primarySwatch: Colors.blue),
        //home: const FirstPage(),
        initialRoute: '/first',
        routes: {
          '/first': (context) => FirstPage(),
          '/second': (context) => SecondPage(),
        },
      ),
    );

  }
}