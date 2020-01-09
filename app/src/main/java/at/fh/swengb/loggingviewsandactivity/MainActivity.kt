package at.fh.swengb.loggingviewsandactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Creating logging statements
        Log.v("MyActivity", "onCreate")
        Log.d("MyActivity","onCreate")
        Log.i("MyActivity","onCreate")
        Log.w("MyActivity","onCreate")
        Log.e("MyActivity","onCreate")

        // -- The old way with ID
        //val myButton = findViewById<Button>(R.id.share_main)
        //myButton.setOnClickListener()

        // import kotlinx.android.synthetic.main.activity_main.* <- need this to work
        // onClickListener -> click button -> send Text
        // directly access obj with ID
        share_main.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, getString(R.string.shareIntent))
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        open_views.setOnClickListener {
            val intent = Intent(this, ViewsActivity::class.java)
            startActivity(intent)
        }

        open_rating.setOnClickListener {
            val intent = Intent(this, RatingActivity::class.java)
            startActivity(intent)
        }

        open_lessons.setOnClickListener{
            val intent = Intent(this, LessonListActivity::class.java)
            startActivity(intent)
        }

        open_higher_order.setOnClickListener{
            val intent = Intent(this, HigherOrderActivity::class.java)
            startActivity(intent)
        }

    }

    // in views_copy -> the horizontal
    // normal views -> with contraints

    //Create Lifecycle
    override fun onStart() {
        super.onStart()
        Log.w("MyActivity","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.w("MyActivity","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.w("MyActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.w("MyActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w("MyActivity","onDestroy")
    }


}
