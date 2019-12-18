package at.fh.swengb.loggingviewsandactivity

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
                mutableListOf()
            ),
            Lesson("1",
                "Lecture 1",
                "09.10.2019",
                "OOP Basics",
                LessonType.LECTURE,
                listOf( lecturerIllis ),
                mutableListOf()
            ),
            Lesson("2",
                "Exercise 1",
                "10.10.2019",
                "OOP Basics",
                LessonType.PRACTICAL,
                listOf( lecturerIllis ),
                mutableListOf()
            ),
            Lesson("3",
                "Lecture 2",
                "17.10.2019",
                "SCM",
                LessonType.LECTURE,
                listOf( lecturerIllis ),
                mutableListOf()
            ),
            Lesson("4",
                "Exercise 2",
                "17.10.2019",
                "SCM",
                LessonType.PRACTICAL,
                listOf( lecturerIllis ),
                mutableListOf()
            ),
            Lesson("5",
                "Lecture 3",
                "29.10.2019",
                "Software Design",
                LessonType.LECTURE,
                listOf( lecturerIllis ),
                mutableListOf()
            ),
            Lesson("6",
                "Lecture 4",
                "30.10.2019",
                "Android Basics",
                LessonType.LECTURE,
                listOf( lecturerBloder ),
                mutableListOf( )
            ),
            Lesson("7",
                "Exercise 4",
                "30.10.2019",
                "Android Basics",
                LessonType.PRACTICAL,
                listOf( lecturerIllis ),
                mutableListOf()
            ),
            Lesson("8",
                "Lecture 5",
                "13.11.2019",
                "Recycler View",
                LessonType.LECTURE,
                listOf( lecturerBloder ),
                mutableListOf()
            ),
            Lesson("9",
                "Exercise 5",
                "12.11.2019",
                "Android Basics",
                LessonType.PRACTICAL,
                listOf( lecturerBloder ),
                mutableListOf()
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

    fun lessonById(id: String): Lesson? {
        return lessons.find { it.id == id }
    }

    fun rateLesson(id: String, rating: LessonRating) {
        lessonById(id)?.ratings?.add(rating)
    }
}