import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(primarySwatch: Colors.blue),
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _curIndex = 0;

  ListView page1() {
    return ListView(
      children: <Widget>[
        ListTile(
          title: Text('ListView'),
          subtitle: Text('Using ListTile'),
          trailing: Icon(Icons.more_vert),
          onTap: () {},
        ),
        ListTile(
          leading: FlutterLogo(
            size: 50.0,
          ),
          title: Text('Flutter'),
          trailing: Icon(Icons.autorenew),
          onTap: () {},
        ),
        ListTile(
          leading: Icon(
            Icons.account_box,
            size: 50.0,
          ),
          title: Text('Contacts'),
          subtitle: Text('Add Phone Number'),
          trailing: Icon(Icons.add),
          onTap: () {},
        )
      ],
    );
  }

  GridView page2() {
    return GridView.count(
      padding: const EdgeInsets.all(10),
      mainAxisSpacing: 50,
      crossAxisSpacing: 10,
      crossAxisCount: 3,
      children: <Widget>[
        Container(
          color: Colors.red,
        ),
        Container(
          color: Colors.red,
        ),
        Container(
          color: Colors.red,
        ),
        Container(
          color: Colors.red,
        ),
        Container(
          color: Colors.red,
        ),
        Container(
          color: Colors.red,
        ),
      ],
    );
  }

  Container page3() {
    return Container(
      alignment: Alignment.center,
      child: Text(
        'Settings',
        style: TextStyle(
          color: Colors.blue,
          fontSize: 50,
        ),
      ),
    );
  }

  Widget getPage() {
    Widget page;
    switch (_curIndex) {
      case 0:
        page = page1();
        break;
      case 1:
        page = page2();
        break;
      case 2:
        page = page3();
        break;
      default:
        page = page1();
        break;
    }
    return page;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('제목'),
      ),
      body: getPage(),
      bottomNavigationBar: BottomNavigationBar(
        onTap: (index) {
          setState(() {
            _curIndex = index;
          });
        },
        currentIndex: _curIndex,
        selectedItemColor: Colors.blue,
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(
              Icons.home,
            ),
            label: 'Home',
          ),
          BottomNavigationBarItem(
            icon: Icon(
              Icons.search,
            ),
            label: 'Search',
          ),
          BottomNavigationBarItem(
            icon: Icon(
              Icons.settings,
            ),
            label: 'Settings',
          ),
        ],
      ),
    );
  }
}
