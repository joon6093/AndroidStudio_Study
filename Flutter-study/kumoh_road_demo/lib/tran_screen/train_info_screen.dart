import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class TrainInfoScreen extends StatefulWidget {
  const TrainInfoScreen({super.key});

  @override
  _TrainInfoScreenState createState() => _TrainInfoScreenState();
}

class _TrainInfoScreenState extends State<TrainInfoScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('기차정보'),
      ),
      body: const Center(
        child: Text('기차정보 화면'),
      ),
    );
  }
}