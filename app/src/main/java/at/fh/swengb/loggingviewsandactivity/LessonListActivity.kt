package at.fh.swengb.loggingviewsandactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_lesson_list.*
import kotlinx.android.synthetic.main.activity_rating.*

class LessonListActivity : AppCompatActivity() {

    companion object {
        val EXTRA_LESSON_ID = "LESSON_ID_EXTRA"
    }

    val lessonAdapter = LessonAdapter(){

        val intent = Intent(this, LessonRatingActivity ::class.java)
        intent.putExtra(EXTRA_LESSON_ID, it.id)
        // Slide 05 Add data
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)

        lessonAdapter.updateList(LessonRepository.lessonsList())
        lesson_recycler_view.layoutManager = LinearLayoutManager(this)
        lesson_recycler_view.adapter = lessonAdapter
    }
}