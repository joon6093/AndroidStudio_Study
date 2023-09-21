import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(primarySwatch: Colors.blue),
      home: MyHomePage(),
    );
  }
}

enum Fruit { APPLE, BANANA }

class MyHomePage extends StatefulWidget {
  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  Fruit _fruit = Fruit.APPLE;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('제목'),
      ),
      body: Column(
        children: <Widget>[
          RadioListTile(
            title: Text('Apple'),
            value: Fruit.APPLE,
            groupValue: _fruit,
            onChanged: (value) {
              setState(() {
                _fruit = value as Fruit;
              });
            },
          ),
          RadioListTile(
            title: Text('Banana'),
            value: Fruit.BANANA,
            groupValue: _fruit,
            onChanged: (value) {
              setState(() {
                _fruit = value as Fruit;
              });
            },
          ),
        ],
      ),
    );
  }
}
