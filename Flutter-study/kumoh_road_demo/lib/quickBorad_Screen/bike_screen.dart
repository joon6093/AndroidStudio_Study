import 'package:flutter/material.dart';

class BikeScreen extends StatefulWidget {
  const BikeScreen({super.key});

  @override
  _BikeScreenState createState() =>
      _BikeScreenState();
}
class _BikeScreenState extends State<BikeScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('퀵보드 및 자전거 경로'),
      ),
      body: const Center(
        child: Text('퀵보드 및 자전거 경로 화면'),
      ),
    );
  }
}
