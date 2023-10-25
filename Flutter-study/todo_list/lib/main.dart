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

  //할일 추가 메서드
  void _addTodo(Todo todo) {
    setState(() {
      _item.add(todo); //인자로 받은 Todo를 리스트에 추가
      _todoController.text = ''; //TextField 비움
    });
  }

//할일 삭제 메서드
  void _deleteTodo(Todo todo) {
    setState(() {
      _item.remove(todo); //인자로 받은Todo를 리스트에서 삭제
    });
  }

//할일 완료 /미완료 메서드
  void _toggleTodo(Todo todo) {
    setState(() {
      todo.isDone = !todo.isDone;
    });
  }

  //할 일 객체를 ListTitle 형태로 변경하는 함수
  Widget _buildItemWidget(Todo todo) {
    return ListTile(
      onTap:() => _toggleTodo(todo), // 클릭 시 완료/취소/Todo: 클릭 시 완료/취소
      title: Text(
        todo.title, //할 일 제목
        style: todo.isDone
            ? const TextStyle(
                //할 일이 완료된 경우 텍스트 스타일 적용
                decoration: TextDecoration.lineThrough, //취소선 적용
                fontStyle: FontStyle.italic, //이탤릭체 적용
              )
            : null, //할 일이 완료되지 않은 경우 아무 텍스트 스타일도 적용하지 않음
      ),
      trailing: IconButton(
        icon: const Icon(Icons.delete_forever),
        onPressed:() => _deleteTodo(todo), //쓰레기통 아이콘 클릭 시 할일 삭제
      ),
    );
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
                  ElevatedButton( //TextField에 입력된 값을 기반으로 Todo 리스트 추가
                      onPressed:() => _addTodo(Todo(_todoController.text)),
                      child: const Text('추가')
                  ),
                ],
              ),
              Expanded(
                child: ListView(
                  //Todo: 할일 목록 표시
                  children:
                      _item.map((todo) => _buildItemWidget(todo)).toList(),
                ),
              )
            ],
          )),
    );
  }
}
