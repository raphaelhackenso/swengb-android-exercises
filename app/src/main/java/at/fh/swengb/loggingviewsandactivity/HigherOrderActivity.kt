package at.fh.swengb.loggingviewsandactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_rating.*

class HigherOrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_higher_order)

        myHigherOrderFunction( { integerInput -> integerInput + 606  })
        myHigherOrderFunction( fun(integerInput: Int):Int = integerInput + 606 )

        kotlinHigherOrderWithList()

    }

    private fun myHigherOrderFunction(param: (Int) -> Int) {
        if (param(6) == 612) {
            Log.e("HIGHER_ORDER", "Congrats, your first lambda works :)")
        }
    }


    private fun kotlinHigherOrderWithList() {
        val list = LessonRepository.lessonsList_old()


        // Version 1 //

        //val test3 = list.map { it.lecturers.toString() }
        //[at.fh.swengb.loggingviewsandactivity.Lecturer@ba920b5, at.fh.swengb.loggingviewsandactivity.Lecturer@ff8434a]

        //val heldByBloder_1 = list.filter{ it.lecturers.toString().contains("ff8434a") }
        //Log.e("HIGHER_ORDER",heldByBloder_1.toString())


        // Version 2 //


        fun isLecturerBloder(lecturerList: List<Lecturer>): Boolean{
            var isTrue: Boolean = false

            lecturerList.forEach{
                if( it.name.equals("Lukas Bloder") ) isTrue = true else isTrue = false
            }
            return isTrue
        }

        val heldByBloder_2 = list.filter { isLecturerBloder(it.lecturers) }
        //Log.e("HIGHER_ORDER", heldByBloder_2.toString())



        // Version 3 //

        val heldByBloder = list.filter{it.lecturers.any{ lecturer -> lecturer.name.equals("Lukas Bloder")  }}

        Log.e("HIGHER_ORDER",heldByBloder.toString())
        // filter the lesson list, so that you create a list of all lessons held by Lukas Bloder // print the list to logcat
        // notice something weird in the output? -> Lesson@ba920b5, Lesson@ff8434a, Lesson@67162bb,  Lesson@1fe33d8



        val topicMap = list.map { it.topic to listOf<Lesson>(it) }.toMap()
        Log.e("HIGHER_ORDER", topicMap.toString())
        // use Kotlins built-in higher order functions on list in order to get a Map<String, List<Lesson>>
        // with the map Key being the lesson topic // print the resulting map to logcat


        val avgLecture = ( list.filter { it.type.description.equals("Lecture") }.map { it.ratings.map { it.ratingValue }.sum() }.sum() ) / ( list.map { it.ratings.count() }.sum().toDouble() )
        Log.e("HIGHER_ORDER", avgLecture.toString())
        // calculate the average rating of all LECTURES
        // print the result to logcat

    }

}
