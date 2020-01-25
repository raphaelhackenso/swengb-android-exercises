package at.fh.swengb.loggingviewsandactivity.common

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LessonNoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(lessonNote: LessonNote)

    @Query("SELECT * FROM LessonNote where id = :id")
    fun findLessonNoteById(id: String): LessonNote?
}