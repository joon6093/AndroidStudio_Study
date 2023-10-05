import 'package:flutter/material.dart';
import 'package:flutter_study01/second_page.dart';
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
        child: const Text('Next Page'),
        onPressed: () async {
          final student1 = Student('Gil-Dong', 30, 20231005);
          final result = await Navigator.push(
            context,
            MaterialPageRoute(builder: (context) => SecondPage(student:student1)),
          );
          print(result);
        },
      ),
    );
  }
}
