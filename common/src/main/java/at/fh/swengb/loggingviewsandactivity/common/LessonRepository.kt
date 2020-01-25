package at.fh.swengb.loggingviewsandactivity

import android.content.Context
import at.fh.swengb.loggingviewsandactivity.common.LessonNote
import at.fh.swengb.loggingviewsandactivity.common.LessonNoteDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object LessonRepository {
    private val lessons: List<Lesson>

    init {
        val lecturerIllis = Lecturer("Sanja Illis")
        val lecturerBloder = Lecturer("Lukas Bloder")
        lessons = listOf(
            Lesson("0",
                "Lecture 0",
                "09.10.2019",
                "Introduction",
                LessonType.LECTURE,
                listOf( lecturerIllis, lecturerBloder ),
                mutableListOf(),
                ""
            ),
            Lesson("1",
                "Lecture 1",
                "09.10.2019",
                "OOP Basics",
                LessonType.LECTURE,
                listOf( lecturerIllis ),
                mutableListOf(),
                ""
            ),
            Lesson("2",
                "Exercise 1",
                "10.10.2019",
                "OOP Basics",
                LessonType.PRACTICAL,
                listOf( lecturerIllis ),
                mutableListOf(),
                ""
            ),
            Lesson("3",
                "Lecture 2",
                "17.10.2019",
                "SCM",
                LessonType.LECTURE,
                listOf( lecturerIllis ),
                mutableListOf(),
                ""
            ),
            Lesson("4",
                "Exercise 2",
                "17.10.2019",
                "SCM",
                LessonType.PRACTICAL,
                listOf( lecturerIllis ),
                mutableListOf(),
                ""
            ),
            Lesson("5",
                "Lecture 3",
                "29.10.2019",
                "Software Design",
                LessonType.LECTURE,
                listOf( lecturerIllis ),
                mutableListOf(),
                ""
            ),
            Lesson("6",
                "Lecture 4",
                "30.10.2019",
                "Android Basics",
                LessonType.LECTURE,
                listOf( lecturerBloder ),
                mutableListOf( ),
                ""
            ),
            Lesson("7",
                "Exercise 4",
                "30.10.2019",
                "Android Basics",
                LessonType.PRACTICAL,
                listOf( lecturerIllis ),
                mutableListOf(),
                ""
            ),
            Lesson("8",
                "Lecture 5",
                "13.11.2019",
                "Recycler View",
                LessonType.LECTURE,
                listOf( lecturerBloder ),
                mutableListOf(),
                ""
            ),
            Lesson("9",
                "Exercise 5",
                "12.11.2019",
                "Android Basics",
                LessonType.PRACTICAL,
                listOf( lecturerBloder ),
                mutableListOf(),
                ""
            )
        )
    }

    fun lessonsList(success: (lessonList: List<Lesson>) -> Unit, error: (errorMessage: String) -> Unit) {
        LessonApi.retrofitService.lessons().enqueue(object: Callback<List<Lesson>> {
            override fun onFailure(call: Call<List<Lesson>>, t: Throwable) {
                error("The call failed")
            }

            override fun onResponse(call: Call<List<Lesson>>, response: Response<List<Lesson>>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    success(responseBody)
                } else {
                    error("Something went wrong")
                }
            }

        })
    }

    fun lessonsList_old(): List<Lesson> {
        return lessons
    }

    fun lessonById_old(id: String): Lesson? {
        return lessons.find { it.id == id }
    }

    fun lessonById(id:String, success: (lesson: Lesson) -> Unit, error: (errorMessage: String) -> Unit){
        LessonApi.retrofitService.lessonByID(id).enqueue(object: Callback<Lesson> {
            override fun onFailure(call: Call<Lesson>, t: Throwable) {
                error("The call failed")
            }

            override fun onResponse(call: Call<Lesson>, response: Response<Lesson>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    success(responseBody)
                } else {
                    error("Something went wrong")
                }
            }

        })

    }

    fun rateLesson_old(id: String, rating: LessonRating) {
        //lessonById(id)?.ratings?.add(rating)
    }

    //unten das Unit: Unit
    fun rateLesson(id: String, rating: LessonRating, success: (Unit: Unit) -> Unit, error: (errorMessage: String) -> Unit){
        LessonApi.retrofitService.rateLesson(id, rating).enqueue(object: Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                error("The call failed")
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    success(responseBody)
                } else {
                    error("Something went wrong")
                }
            }

        })
    }

    fun addLessonNote(context: Context, lessonNote: LessonNote) {
        val db = LessonNoteDatabase.getDatabase(context.applicationContext)
        db.lessonNoteDao.insert(lessonNote)
    }

    fun findLessonNoteById(context: Context, inputID: String):LessonNote? {
        val db = LessonNoteDatabase.getDatabase(context.applicationContext)
        return db.lessonNoteDao.findLessonNoteById(inputID)


    }


}