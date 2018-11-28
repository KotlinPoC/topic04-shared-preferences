package cs518.samples.sharedpreferences

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.LinearLayout

/**
 * Sample using preferences
 * ref: http://developer.android.com/training/basics/data-storage/shared-preferences.html
 * @author PMCampbell
 * @version 2018
 */
class MainActivity : Activity() {
    private var uidstr: String? = null
    private var passwdstr: String? = null
    private var userID: EditText? = null
    private var passwd: EditText? = null
    private var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // no editor needed we're reading values
        prefs = getPreferences(Context.MODE_PRIVATE)

        uidstr = prefs!!.getString(getString(R.string.uid), "nothing")
        if (uidstr !== "nothing") {
            // activate welcome back message if prefs contains a userid.
            (findViewById(R.id.welcomeback) as TextView).visibility = View.VISIBLE
        }

        // display on UI
        val userIDt = findViewById(R.id.uidsaved) as TextView
        val llsaved = findViewById(R.id.resultlayout) as LinearLayout

        Log.w(TAG, uidstr)
        userIDt.text = uidstr
        llsaved.visibility = View.VISIBLE

    } // onCreate()

    fun saveUserInfo(view: View) {
        // get input data
        userID = findViewById(R.id.uid) as EditText
        passwd = findViewById(R.id.passwd) as EditText

        uidstr = userID!!.text.toString()
        passwdstr = passwd!!.text.toString()
        /*
		 * logically is better to save the preferences when the data are changed
		 */

        // Store values between app instances
        // MODE_PRIVATE: file only accessible by calling app (same UID)

        // done in onCreate()
        // SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        val editor = prefs!!.edit()

        // set the key/value pairs
        editor.putString(getString(R.string.uid), uidstr)

        // don't forget to commit the changes
        editor.commit()
    }

    companion object {
        private val TAG = "SHPREF"
    }

}
