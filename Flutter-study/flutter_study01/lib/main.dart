import 'package:flutter/material.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});
  Future<int> sum() {
    return Future<int>(() {
      var sum = 0;
      for (int i = 0; i < 5000000000; i++) {
        sum += i;
      }
      return sum;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Future and FutureBuilder'),
        ),
        body: FutureBuilder(
          future: sum(),
          builder: (context, snapshot) {
            //두번째 매개변수에 future 데이터 가 전달
            if (snapshot.hasData) {
              return Center(
                child: Text(
                  '${snapshot.data}',
                  style: const TextStyle(color: Colors.black, fontSize: 30),
                ),
              );
            }
            return const Center(
              child: Text(
                'waiting',
                style: TextStyle(color: Colors.black, fontSize: 30),
              ),
            );
          },
        ),
      ),
    );
  }
}
