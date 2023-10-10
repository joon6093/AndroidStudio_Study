import 'package:flutter/material.dart';
import '../bus_screen/bus_info_screen.dart';
import '../gpt_screen/gpt_service_screen.dart';
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
                  builder: (context) => const TrainInfoScreen(),
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
                  builder: (context) => const BikeScreen(),
                ));
              },
              child: const Text('퀵보드 및 자전거 경로'),
            ),
            TextButton(
              onPressed: () {
                Navigator.of(context).push(MaterialPageRoute(
                  builder: (context) => const GptServiceScreen(),
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
