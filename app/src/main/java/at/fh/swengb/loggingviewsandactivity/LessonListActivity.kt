package at.fh.swengb.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_lesson_list.*
import kotlinx.android.synthetic.main.activity_rating.*


class LessonListActivity : AppCompatActivity() {

    companion object {
        val EXTRA_LESSON_ID = "LESSON_ID_EXTRA"
        val ADD_OR_EDIT_RATING_REQUEST = 1
    }

    val lessonAdapter = LessonAdapter(){

        val intent = Intent(this, LessonRatingActivity ::class.java)
        intent.putExtra(EXTRA_LESSON_ID, it.id)
        // Slide 05 Add data
        //startActivity(intent)
        startActivityForResult(intent, ADD_OR_EDIT_RATING_REQUEST)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)

        lesson_recycler_view.layoutManager = LinearLayoutManager(this)
        lesson_recycler_view.adapter = lessonAdapter

        LessonRepository.lessonsList(
            success = {
                // handle success
                lessonAdapter.updateList(it)

            },
            error = {
                // handle error
                Log.e("API_CALL", it)
            }
        )

        //lessonAdapter.updateList(LessonRepository.lessonsList())



        parseJson()
        //Thread.sleep(5000)
        //Log.i("JSON", "bin fertig mit 5 sec warten")

        SleepyAsyncTask().execute()

    }


     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_OR_EDIT_RATING_REQUEST && resultCode == Activity.RESULT_OK) {

            LessonRepository.lessonsList(
                success = {
                    // handle success
                    lessonAdapter.updateList(it)

                },
                error = {
                    // handle error
                    Log.e("API_CALL", it)
                }
            )
        }
    }

    fun parseJson(){

        val json = """
            {
                "id": "1",
                "name": "Lecture 0",
                "date": "09.10.2019",
                "topic": "Introduction",
                "type": "LECTURE",
                "lecturers": [
                    {
                        "name": "Lukas Bloder"
                    },
                    {
                        "name": "Sanja Illes"
                    }
                ],
                "ratings": [],
                    "imageUrl" : "https://picsum.photos/600"
            }
        """

        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter<Lesson>(Lesson::class.java)

        val result = jsonAdapter.fromJson(json)
        Log.i("JSON", result.toString())

    }
}