package at.fh.swengb.loggingviewsandactivity.common

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class LessonNote(@PrimaryKey val id: String, val lessonName: String, var text: String) {


}