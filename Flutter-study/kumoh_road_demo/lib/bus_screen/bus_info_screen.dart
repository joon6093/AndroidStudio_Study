import 'package:flutter/material.dart';

class BusInfoScreen extends StatefulWidget {
  const BusInfoScreen({super.key});

  @override
  _BusInfoScreenState createState() => _BusInfoScreenState();
}

class _BusInfoScreenState extends State<BusInfoScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('버스 정보'),
      ),
      body: const Center(
        child: Text('버스 정보 화면'),
      ),
    );
  }
}
