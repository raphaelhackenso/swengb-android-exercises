package at.fh.swengb.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import at.fh.swengb.loggingviewsandactivity.LessonRepository.rateLesson
import com.squareup.moshi.JsonClass
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

            LessonRepository.lessonById(lessonId,
                success = { lesson_rating_header.text =  it.name  },
                error = { Log.e("API_CALL", it)})



            rate_lesson.setOnClickListener{
                val myLessonRatingObject = LessonRating(lesson_rating_bar.rating.toDouble(), lesson_feedback.text.toString() )
                LessonRepository.rateLesson( lessonId, myLessonRatingObject,
                    success = {Log.i("API_CALL", "Rating zur lesson hinzu")},
                    error = {Log.e("API_CALL", it)})

                val resultIntent = Intent()
                setResult(Activity.RESULT_OK, resultIntent)
                finish()

            }
        }


    }
}
