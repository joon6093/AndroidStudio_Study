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

class MyHomePage extends StatefulWidget {
  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  TimeOfDay _selectedTime = TimeOfDay.now();
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('제목'),
      ),
      body: Center(
        child: Column(
          children: <Widget>[
            ElevatedButton(
              child: Text('Time'),
              onPressed: () {
                Future<TimeOfDay?> selectedTime = showTimePicker(
                  context: context,
                  initialTime: TimeOfDay.now(), //초기값
                );
                selectedTime.then((date) {
                  setState(() {
                    _selectedTime = date!;
                  });
                });
              },
            ),
            if (_selectedTime != null) //시간이 선택되어 있다면, Text로 출력
              Text('${_selectedTime.hour}시 ${_selectedTime.minute} 분'),
          ],
        ),
      ),
    );
  }
}
