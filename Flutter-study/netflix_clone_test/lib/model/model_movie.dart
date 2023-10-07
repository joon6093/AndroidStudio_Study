// model_movie.dart
import 'package:cloud_firestore/cloud_firestore.dart';

class Movie {
  final String title;
  final String keyword;
  final String poster;
  final bool like;
  final DocumentReference reference;

  Movie.fromMap(Map<String, dynamic> map, {required this.reference})
      : title = map['title'],
        keyword = map['keyword'],
        poster = map['poster'],
        like = map['like'];

  Movie.fromSnapshot(DocumentSnapshot snapshot)
      : title = snapshot['title'],
        keyword = snapshot['keyword'],
        poster = snapshot['poster'],
        like = snapshot['like'],
        reference = snapshot.reference;

  @override
  String toString() => "Movie<$title:$keyword>";
}