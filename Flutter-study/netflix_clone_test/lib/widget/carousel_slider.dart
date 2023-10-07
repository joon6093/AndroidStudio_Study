import 'package:carousel_slider/carousel_slider.dart';
import 'package:flutter/material.dart';
import '../model/model_movie.dart';
import '../screen/detail_screen.dart';

class CarouselImage extends StatefulWidget {
  final List<Movie> movies;

  const CarouselImage({super.key, required this.movies});

  @override
  _CarouselImageState createState() => _CarouselImageState();
}

class _CarouselImageState extends State<CarouselImage> {
  late List<Movie> movies;
  late List<Widget> images;
  late List<String> keywords;
  late List<bool> likes;
  int _currentPage = 0;
  late String _currentKeyword;

  @override
  void initState() {
    super.initState();
    movies = widget.movies;
    images = movies.map((m) => Image.network(m.poster)).toList();
    keywords = movies.map((m) => m.keyword).toList();
    likes = movies.map((m) => m.like).toList();
    _currentKeyword = keywords[0];
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        Container(
          padding: const EdgeInsets.all(20),
        ),
        CarouselSlider.builder(
          itemCount: images.length,
          itemBuilder: (BuildContext context, int index, int realIndex) {
            return images[index];
          },
          options: CarouselOptions(
            onPageChanged: (index, reason) {
              setState(() {
                _currentPage = index;
                _currentKeyword = keywords[_currentPage];
              });
            },
          ),
        ),
        Container(
          padding: const EdgeInsets.fromLTRB(0, 10, 0, 3),
          child: Text(
            _currentKeyword,
            style: const TextStyle(fontSize: 11),
          ),
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            Column(
              children: <Widget>[
                likes[_currentPage]
                    ? IconButton(
                        icon: const Icon(Icons.check),
                        onPressed: () {
                          setState(() {
                            likes[_currentPage] = !likes[_currentPage];
                            movies[_currentPage].reference.update(
                                {'like': likes[_currentPage]});
                          });
                        },
                      )
                    : IconButton(
                        icon: const Icon(Icons.add),
                        onPressed: () {
                          setState(() {
                            likes[_currentPage] = !likes[_currentPage];
                            movies[_currentPage].reference.update(
                                {'like': likes[_currentPage]});
                          });
                        },
                      ),
                const Text(
                  '찜하기',
                  style: TextStyle(fontSize: 11),
                )
              ],
            ),
            Container(
              padding: const EdgeInsets.only(right: 10),
              child: ElevatedButton(
                style: ElevatedButton.styleFrom(
                  backgroundColor: Colors.white,
                ),
                onPressed: () {},
                child: const Row(
                  children: <Widget>[
                    Icon(
                      Icons.play_arrow,
                      color: Colors.black,
                    ),
                    Padding(
                      padding: EdgeInsets.all(3),
                    ),
                    Text(
                      '재생',
                      style: TextStyle(color: Colors.black),
                    )
                  ],
                ),
              ),
            ),
            Container(
              padding: const EdgeInsets.only(right: 10),
              child: Column(
                children: <Widget>[
                  IconButton(
                    icon: const Icon(Icons.info),
                    onPressed: () {
                      Navigator.of(context).push(MaterialPageRoute<void>(
                          fullscreenDialog: true,
                          builder: (BuildContext context) {
                            return DetailScreen(
                              movie: movies[_currentPage],
                            );
                          }));
                    },
                  ),
                  const Text(
                    '정보',
                    style: TextStyle(fontSize: 11),
                  )
                ],
              ),
            ),
          ],
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: makeIndicator(movies.length, _currentPage),
        )
      ],
    );
  }
}

List<Widget> makeIndicator(int movieCount, int currentPage) {
  List<Widget> results = [];
  for (var i = 0; i < movieCount; i++) {
    results.add(Container(
      width: 8,
      height: 8,
      margin: const EdgeInsets.symmetric(vertical: 10.0, horizontal: 2.0),
      decoration: BoxDecoration(
        shape: BoxShape.circle,
        color: currentPage == i
            ? const Color.fromRGBO(255, 255, 255, 0.9)
            : const Color.fromRGBO(255, 255, 255, 0.4),
      ),
    ));
  }

  return results;
}
