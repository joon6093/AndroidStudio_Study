import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';

import '../model/model_movie.dart';
import 'detail_screen.dart';

class SearchScreen extends StatefulWidget {
  const SearchScreen({super.key});

  @override
  _SearchScreenState createState() => _SearchScreenState();
}

class _SearchScreenState extends State<SearchScreen> {
  final TextEditingController _filter = TextEditingController();
  FocusNode focusNode = FocusNode();
  String _searchText = "";

  _SearchScreenState() {
    _filter.addListener(() {
      setState(() {
        _searchText = _filter.text;
      });
    });
  }

  Widget _buildBody(BuildContext context) {
    return StreamBuilder<QuerySnapshot>(
      stream: FirebaseFirestore.instance.collection('movie').snapshots(),
      builder: (context, snapshot) {
        if (!snapshot.hasData) return const LinearProgressIndicator();
        return _buildList(context, snapshot.data?.docs ?? []);
      },
    );
  }

  Widget _buildList(BuildContext context, List<DocumentSnapshot> snapshot) {
    List<DocumentSnapshot> searchResults = [];
    for (DocumentSnapshot d in snapshot) {
      if (Movie.fromSnapshot(d).toString().contains(_searchText)) {
        searchResults.add(d);
      }
    }
    return Expanded(
      child: ListView(
        children: searchResults
            .map((data) => _buildListItem(context, data))
            .toList(),
      ),
    );
  }

  Widget _buildListItem(BuildContext context, DocumentSnapshot data) {
    final movie = Movie.fromSnapshot(data);
    return InkWell(
      child: Column(
        children: [
          Image.network(movie.poster),
          const SizedBox(height: 8), // 이미지와 제목 사이에 간격을 줍니다.
          Text(
            movie.title,
            style: const TextStyle(
              fontSize: 16, // 제목 텍스트의 크기를 설정합니다.
              fontWeight: FontWeight.bold, // 제목을 굵게 표시합니다.
            ),
          ),
        ],
      ),
      onTap: () {
        Navigator.of(context).push(MaterialPageRoute<Null>(
            fullscreenDialog: true,
            builder: (BuildContext context) {
              return DetailScreen(movie: movie);
            }));
      },
    );
  }


  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        const Padding(
          padding: EdgeInsets.all(30),
        ),
        Container(
          color: Colors.black,
          padding: const EdgeInsets.fromLTRB(5, 10, 5, 10),
          child: Row(
            children: <Widget>[
              Expanded(
                flex: 6,
                child: TextField(
                  focusNode: focusNode,
                  style: const TextStyle(
                    fontSize: 15,
                  ),
                  autofocus: true,
                  controller: _filter,
                  decoration: InputDecoration(
                    filled: true,
                    fillColor: Colors.white12,
                    prefixIcon: const Icon(
                      Icons.search,
                      color: Colors.white60,
                      size: 20,
                    ),
                    suffixIcon: focusNode.hasFocus
                        ? IconButton(
                      icon: const Icon(
                        Icons.cancel,
                        size: 20,
                        color: Colors.white60,
                      ),
                      onPressed: () {
                        setState(() {
                          _filter.clear();
                          _searchText = "";
                        });
                      },
                    )
                        : Container(),
                    hintText: '검색',
                    labelStyle: const TextStyle(color: Colors.white),
                    focusedBorder: const OutlineInputBorder(
                        borderSide: BorderSide(color: Colors.transparent),
                        borderRadius: BorderRadius.all(Radius.circular(10))),
                    enabledBorder: const OutlineInputBorder(
                        borderSide: BorderSide(color: Colors.transparent),
                        borderRadius: BorderRadius.all(Radius.circular(10))),
                    border: const OutlineInputBorder(
                        borderSide: BorderSide(color: Colors.transparent),
                        borderRadius: BorderRadius.all(Radius.circular(10))),
                  ),
                ),
              ),
              focusNode.hasFocus
                  ? Expanded(
                child: ElevatedButton(
                  style: ElevatedButton.styleFrom(
                    backgroundColor: Colors.black, // 버튼의 배경색을 검정색으로 설정
                  ),
                  child: const Text(
                    '취소',
                    style: TextStyle(
                      fontSize: 13, // 텍스트 크기를 줄임
                    ),
                  ),
                  onPressed: () {
                    setState(() {
                      _filter.clear();
                      _searchText = "";
                      focusNode.unfocus();
                    });
                  },
                )
              )
                  : Expanded(
                flex: 0,
                child: Container(),
              )
            ],
          ),
        ),
        _buildBody(context)
      ],
    );
  }
}