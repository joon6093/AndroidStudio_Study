import 'package:flutter/material.dart';
class Counter extends ChangeNotifier {
  int number = 0;
  void add() {
    number++;
    notifyListeners();
  }
}