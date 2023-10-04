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
  final controller = PageController(
    initialPage: 0,
  );
  ListView page1()
  {
    return ListView(
      children: <Widget>[
        ListTile(
          title: Text('ListView'),
          subtitle: Text('Using ListTile'),
          trailing: Icon(Icons.more_vert),
          onTap:(){},
        ),
        ListTile(
          leading: FlutterLogo(size: 50.0,),
          title: Text('Flutter'),
          trailing: Icon(Icons.autorenew),
          onTap:(){},
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
  GridView page2()
  {
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

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('제목'),
      ),
      body: PageView(
        controller: controller,
        children: <Widget>[
          page1(),
          page2(),
        ],
      ),
    );
  }
}
