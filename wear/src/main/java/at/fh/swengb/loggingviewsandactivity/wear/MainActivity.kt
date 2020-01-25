package at.fh.swengb.loggingviewsandactivity.wear

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.util.Log
import at.fh.swengb.loggingviewsandactivity.common.SharedClass
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val intent = Intent(this, wearLessonAcitivity::class.java)
            startActivity(intent)
        }

        val shareddClass = SharedClass()
        Log.e("NIX", shareddClass.mySharedFunction())




    }
}
