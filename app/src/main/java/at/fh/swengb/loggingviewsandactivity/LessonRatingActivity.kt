package at.fh.swengb.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import at.fh.swengb.loggingviewsandactivity.LessonRepository.rateLesson
import kotlinx.android.synthetic.main.activity_lesson_rating.*
import kotlinx.android.synthetic.main.activity_main.*

class LessonRatingActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_rating)

        val lessonId = intent.getStringExtra(LessonListActivity.EXTRA_LESSON_ID)

        if (lessonId == null){
            finish()
        } else {
            lesson_rating_header.text=  LessonRepository.lessonById(lessonId)?.name

            rate_lesson.setOnClickListener{
                val myLessonRatingObject = LessonRating(lesson_rating_bar.rating.toDouble(), lesson_feedback.text.toString() )
                rateLesson( lessonId, myLessonRatingObject)

                val resultIntent = Intent()
                setResult(Activity.RESULT_OK, resultIntent)
                finish()

            }
        }


    }
}
