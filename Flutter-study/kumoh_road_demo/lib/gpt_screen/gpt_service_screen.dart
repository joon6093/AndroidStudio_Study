import 'package:flutter/material.dart';

class GptServiceScreen extends StatefulWidget {
  const GptServiceScreen({super.key});

  @override
  _GptServiceScreenState createState() => _GptServiceScreenState();
}

class _GptServiceScreenState extends State<GptServiceScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('GPT 서비스'),
      ),
      body: const Center(
        child: Text('GPT 서비스 화면'),
      ),
    );
  }
}
