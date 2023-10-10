import 'package:flutter/material.dart';
import '../bus_screen/bus_info_screen.dart';
import '../quickBorad_Screen/bike_screen.dart';
import '../tran_screen/train_info_screen.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            TextButton(
              onPressed: () {
                Navigator.of(context).push(MaterialPageRoute(
                  builder: (context) => TrainInfoScreen(),
                ));
              },
              child: const Text('기차정보'),
            ),
            TextButton(
              onPressed: () {
                Navigator.of(context).push(MaterialPageRoute(
                  builder: (context) => const BusInfoScreen(),
                ));
              },
              child: const Text('버스 정보'),
            ),
            TextButton(
              onPressed: () {
                Navigator.of(context).push(MaterialPageRoute(
                  builder: (context) => BikeScreen(),
                ));
              },
              child: const Text('퀵보드 및 자전거 경로'),
            ),
            TextButton(
              onPressed: () {
                Navigator.of(context).push(MaterialPageRoute(
                  builder: (context) => GptServiceScreen(),
                ));
              },
              child: const Text('GPT 서비스'),
            ),
          ],
        ),
      ),
    );
  }
}

class GptServiceScreen extends StatefulWidget {
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
