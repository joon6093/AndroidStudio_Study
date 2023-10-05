import 'package:flutter/material.dart';
import 'package:flutter_study01/student.dart';
class FirstPage extends StatelessWidget {
  const FirstPage({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('First'),
      ),
      body: ElevatedButton(
        child:  const Text('Next Page'),
        onPressed: () async {
          final result = await Navigator.pushNamed(
            context,
            '/second',
            arguments: Student('Seong-Hyoen', 19, 20190456),
          ) as Student;
          print('in FirstPage ${result.name}');
        },
      ),
    );
  }
}