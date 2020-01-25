package at.fh.swengb.loggingviewsandactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    companion object {
        val KEY_DARKMODE = "MY_KEY_FOR_DARKMODE_BOOLEAN"
        val KEY_USERNAME = "MY_KEY_FOR_USERNAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        //read DATA

        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        settings_input_username.setText(sharedPreferences.getString("MY_KEY_FOR_USERNAME", ""))
        settings_switch.isChecked = sharedPreferences.getBoolean("MY_KEY_FOR_DARKMODE_BOOLEAN", false)


        settings_btn_save.setOnClickListener(){

            var username: String = settings_input_username.text.toString()
            var darkmode: Boolean = settings_switch.isChecked

            val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
            sharedPreferences.edit().putString(KEY_USERNAME, username).apply()
            sharedPreferences.edit().putBoolean(KEY_DARKMODE, darkmode).apply()


            finish()

        }





    }
}
