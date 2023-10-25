import 'package:flutter/material.dart';
import 'package:firebase_core/firebase_core.dart';
import 'firebase_options.dart';
import 'package:cloud_firestore/cloud_firestore.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(
    options: DefaultFirebaseOptions.currentPlatform,
  );
  runApp(const MyApp());
}

//할일 클래스
class Todo {
  bool isDone; //할 일 완료 여부 저장
  String title; //할일 이름 저장
  Todo(this.title, {this.isDone = false});
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
      FirebaseFirestore.instance.collection('todo').add(
          {'title': todo.title, 'isDone': todo.isDone}
      );
      _todoController.text = ''; //TextField 비움
    });
  }

//할 일 삭제 메서드
  void _deleteTodo(DocumentSnapshot doc)
  {
    FirebaseFirestore.instance.collection('todo').doc(doc.id).delete();
  }

//할 일 완료/미완료 메서드
  void _toggleTodo(DocumentSnapshot doc)
  {
    FirebaseFirestore.instance.collection('todo').doc(doc.id).update({
      'isDone': !doc['isDone'],
    });
  }

  //할 일 객체를 ListTile 형태로 변경하는 메서드
  Widget _buildItemWidget(DocumentSnapshot doc) {
    final todo = Todo(doc['title'], isDone: doc['isDone']);
    return ListTile(
      onTap: () => _toggleTodo(doc), //클릭 시 완료/취소
      title: Text(
        todo.title, //할 일 제목
        style: todo.isDone
            ? const TextStyle(
                //할 일이 완료된 경우 텍스트 스타일 적용
                decoration: TextDecoration.lineThrough, //취소선 적용
                fontStyle: FontStyle.italic, //이탤릭체 적용
              )
            : null, //할 일이 완료 되지 않은 경우 아무 스타일도 적용하지 않음
      ),
      trailing: IconButton(
        icon: const Icon(Icons.delete_forever),
        onPressed: () => _deleteTodo(doc), //쓰레기통 아이콘 클릭 시 할일 삭제
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
                  ElevatedButton(
                      //TextField에 입력된 값을 기반으로 Todo 리스트 추가
                      onPressed: () => _addTodo(Todo(_todoController.text)),
                      child: const Text('추가')),
                ],
              ),
              StreamBuilder<QuerySnapshot>(
                  //todo 컬렉션에 있는 모든 문서를 스트림으로 획득
                  //스트림은 데이터가 변경되었을 때 반응하여 화면을 다시 그려 줌.
                  stream:
                      FirebaseFirestore.instance.collection('todo').snapshots(),
                  builder: (context, snapshot) {
                    if (!snapshot.hasData) {
                      return CircularProgressIndicator();
                    }
                    final documents = snapshot.data!.docs;
                    return Expanded(
                      child: ListView(
                        //Todo: 할 일 목록 표시
                        children: documents
                            .map((doc) => _buildItemWidget(doc))
                            .toList(),
                      ),
                    );
                  }),
            ],
          )),
    );
  }
}
