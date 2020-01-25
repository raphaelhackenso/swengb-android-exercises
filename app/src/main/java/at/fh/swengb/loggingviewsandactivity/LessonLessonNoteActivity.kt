package at.fh.swengb.loggingviewsandactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import at.fh.swengb.loggingviewsandactivity.common.LessonNote
import kotlinx.android.synthetic.main.activity_lesson_lesson_note.*
import kotlinx.android.synthetic.main.activity_rating.*

class LessonLessonNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_lesson_note)


        val lessonId = intent.getStringExtra(LessonRatingActivity.EXTRA_LESSON_NOTE_ID)
        val lessonName = intent.getStringExtra(LessonRatingActivity.EXTRA_LESSON_NOTE_NAME)

        lessonNote_lessonName.text = lessonName


        //read from database
        if(lessonId != null){
            val myStoredLessonNote = LessonRepository.findLessonNoteById(this,lessonId)
            lesson_note_test_databaseOutput.text = myStoredLessonNote?.text
        }




        lessonNote_save.setOnClickListener{

            val myLessonNoteText = lessonNote_input_note.text.toString()

            if(lessonId != null && lessonName != null){

                var myNewLessonNoteObject = LessonNote(lessonId, lessonName, myLessonNoteText)
                LessonRepository.addLessonNote(this, myNewLessonNoteObject)
            }

            finish()
        }

    }
}
