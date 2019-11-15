package at.fh.swengb.loggingviewsandactivity

class Lesson(var id: String, var name: String, var date: String, var  topic: String, var type: LessonType,
             lecturers: List<Lecturer>, ratings: List<LessonRating>) {

    fun ratingAverage():Double{
        return 0.0
    }

}