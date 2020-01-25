package at.fh.swengb.loggingviewsandactivity

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class LessonRating(var ratingValue: Double, var feedback: String) {


}