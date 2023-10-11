import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage>
    with SingleTickerProviderStateMixin {
  //SingleTickerProviderStateMixin 상속 (with 다중 상속 예약어)
  late AnimationController _controller; //late: non-nullable 인스턴스를 나중에 초기화
  late Animation<double> _animation;

  @override
  void initState() {
    super.initState();
    _controller = AnimationController(
      duration: const Duration(seconds: 1), //애니메이션 지속 시간 설정
//vsync에는 TickerProvider를 전달해야함.
//이 클래스의 경우 SingleTickerProviderStateMixin을 상속 받으므로 this로 설정
      vsync: this,
    )..repeat(); //애니메이션 반복 실행
    _animation = CurvedAnimation(
        parent: _controller,
        curve: Curves.easeInOutQuint
    );
  }

  @override
  void dispose() {
    _controller.dispose(); //사용하지 않을 경우 dispose
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('RoationTransition Demo'),
      ),
      body: RotationTransition(
        turns:  _animation, //AnimationController 인스턴스 전달
        alignment: Alignment.center, //회전 중심점.
        child: Center(
          child: IconButton(
            icon: Icon(Icons.sentiment_satisfied),
            iconSize: 200.0,
            onPressed: () {
              //버튼이 눌리면,
              if (_controller.duration?.inSeconds == 1) {
                //현재 설정된 duration이 1초 이면,
                _controller.duration = const Duration(seconds: 3); //3초로 변경
              } else {
                //3초로 설정되어 있으면,
                _controller.duration = const Duration(seconds: 1); //1초로 변경
              }
              _controller.repeat(); //애니메이션 반복
            },
          ),
        ),
      ),
    );
  }
}