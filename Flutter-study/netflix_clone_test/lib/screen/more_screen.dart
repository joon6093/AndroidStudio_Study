// more_screen.dart
import 'package:flutter/material.dart';

class MoreScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Center(
        child: Column(
          children: <Widget>[
            Container(
              padding: EdgeInsets.only(top: 50),
              child: CircleAvatar(
                radius: 100,
                backgroundImage: AssetImage('images/profile_image.png'),
              ),
            ),
            Container(
              padding: EdgeInsets.only(top: 15),
              child: Text(
                '송제용',
                style: TextStyle(
                    fontWeight: FontWeight.bold,
                    fontSize: 25,
                    color: Colors.white),
              ),
            ),
            Container(
              padding: EdgeInsets.all(15),
              width: 80,
              height: 5,
              color: Colors.red,
            ),
            Container(
              padding: EdgeInsets.all(10),
              child: Text(
                'https://github.com/joon6093',
                style: TextStyle(
                    fontWeight: FontWeight.bold,
                    fontSize: 25,
                    color: Colors.white),
              ),
            ),
            Container(
              padding: EdgeInsets.all(10),
              child: ElevatedButton(
                onPressed: () {},
                style: ElevatedButton.styleFrom(
                  backgroundColor: Colors.red, // 버튼의 배경 색상을 설정합니다.
                ),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    Icon(
                      Icons.edit,
                      color: Colors.white,
                    ),
                    SizedBox(
                      width: 10,
                    ),
                    Text(
                      '프로필 수정하기',
                      style: TextStyle(color: Colors.white),
                    ),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}