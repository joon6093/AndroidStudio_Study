import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'student.dart';
import 'counter.dart';

class FirstPage extends StatefulWidget{
  const FirstPage({super.key});

  @override
  State<FirstPage> createState() => _FirstStatefulPage();
}

class _FirstStatefulPage extends State<FirstPage>{
  var cnt;
  @override
  Widget build(BuildContext context){
    cnt = Provider.of<Counter>(context, listen:true);
    return Scaffold(
      appBar: AppBar(title: Text('First'),),
      body: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Center(
              child: ElevatedButton(
                child: const Text('Next Page'),
                onPressed: () async {
                  final student1 = Student('Hyeon-Rak',24,20190622);
                  final result = await Navigator.pushNamed(context, '/second',
                    arguments: Student('Hyeon-Rak',24,20190622),
                  );
                  if (result is Student) {
                    print('in FirstPage: ${result.name}');
                  } else {
                    print('No student data returned');
                  }
                },
              ),
            ),
            Text('${cnt.number}'),
          ]
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: (){ cnt.add(); },
        child: Icon(Icons.add),
        backgroundColor: Colors.blue,
      ),
    );
  }
}