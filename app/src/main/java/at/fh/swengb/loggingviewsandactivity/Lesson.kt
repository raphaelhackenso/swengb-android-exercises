package at.fh.swengb.loggingviewsandactivity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Lesson(var id: String, var name: String, var date: String, var  topic: String, var type: LessonType,
             val lecturers: List<Lecturer>, val ratings: MutableList<LessonRating>, var imageUrl: String) {

    fun ratingAverage():Double{
        var average = ratings.map {it.ratingValue}.average()
        val average2 = ratings.map{rating -> rating.ratingValue}.average()

        if (average.isNaN()){
            average = 0.0
        }

        return average
    }

}