import 'package:flutter/material.dart';
import 'package:flutter_study01/student.dart';
class SecondPage extends StatelessWidget {
  final Student student;
  const SecondPage({Key? key, required this.student}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    print('name: ${student.name}, age: ${student.age}, stdID: ${student.stdID}');
    return Scaffold(
      appBar: AppBar(
        title: const Text('Second'),
      ),
      body: ElevatedButton(
        child: Text('Previous Page'),
        onPressed: () {
          Navigator.pop(context, 'ACK');
        },
      ),
    );
  }
}