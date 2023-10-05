import 'package:flutter/material.dart';
import 'package:flutter_study01/student.dart';

class SecondPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final args = ModalRoute.of(context)!.settings.arguments as Student;
    print('inSecondPage:${args.name}');
    return Scaffold(
      appBar: AppBar(
        title: const Text('Second'),
      ),
      body: ElevatedButton(
        child: const Text('Previous Page'),
        onPressed: () {
          final argSec = Student('kumoh', 43, 20190456);
          Navigator.pop(context, argSec);
        },
      ),
    );
  }
}