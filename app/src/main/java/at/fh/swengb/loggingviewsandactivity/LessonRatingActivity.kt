package at.fh.swengb.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_lesson_rating.*


class LessonRatingActivity : AppCompatActivity() {


        var lessonName: String? = null


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            R.id.share -> {


                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, lessonName?: getString(R.string.defaultText))
                    type = "text/plain"
                }


                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
                true

            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_rating)

        val lessonId = intent.getStringExtra(LessonListActivity.EXTRA_LESSON_ID)

        if (lessonId == null){
            finish()
        } else {

            LessonRepository.lessonById(lessonId,
                success = { lesson_rating_header.text =  it.name
                            lessonName = it.name
                    Glide
                        .with(imageView_rating)
                        .load(it.imageUrl)
                        .into(imageView_rating)

                    //TODO den Feedback dingsata unten dazu
                    val tempRatingList: List<LessonRating> = it.ratings.filter { it.feedback != "" }
                    if( tempRatingList.first() != null){
                        textView_feedback_output.text = tempRatingList.first().feedback
                    }


                },
                error = { Log.e("API_CALL", it)})



            rate_lesson.setOnClickListener{
                val myLessonRatingObject = LessonRating(lesson_rating_bar.rating.toDouble(), lesson_feedback.text.toString() )
                LessonRepository.rateLesson( lessonId, myLessonRatingObject,
                    success = {Log.i("API_CALL", getString(R.string.addedRating))},
                    error = {Log.e("API_CALL", it)})

                val resultIntent = Intent()
                setResult(Activity.RESULT_OK, resultIntent)
                finish()

            }
        }


    }
}
