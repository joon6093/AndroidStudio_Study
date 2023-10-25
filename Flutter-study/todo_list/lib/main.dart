import 'package:flutter/material.dart';

void main() => runApp(const MyApp());

//할일 클래스
class Todo {
  bool isDone = false; //할일 완료 여부 저장
  String title; //할일 이름 저장
  Todo(this.title); //할일 클래스 생성자
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'To-do list',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: TodoListPage(),
    );
  }
}

class TodoListPage extends StatefulWidget {
  const TodoListPage({Key? key}) : super(key: key);
  @override
  State<TodoListPage> createState() => _TodoListPageState();
}

class _TodoListPageState extends State<TodoListPage> {
  //할일 목록을 저장할 리스트
  final _item = <Todo>[];
  //할일 문자열 조작을 위한 컨트롤러
  var _todoController = TextEditingController();
  @override
  void dispose() {
    _todoController.dispose(); //사용이 끝나면 해제
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('남은할일'),
      ),
      body: Padding(
          padding: const EdgeInsets.all(8.0),
          child: Column(
            children: <Widget>[
              Row(
                children: <Widget>[
                  Expanded(
                    child: TextField(
                      controller: _todoController,
                    ),
                  ),
                  ElevatedButton(onPressed: () {}, child: Text('추가')),
                ],
              ),
              Expanded(
                child: ListView(
                  //Todo: 할일 목록 표시
                  children: <Widget>[],
                ),
              )
            ],
          )),
    );
  }
}
