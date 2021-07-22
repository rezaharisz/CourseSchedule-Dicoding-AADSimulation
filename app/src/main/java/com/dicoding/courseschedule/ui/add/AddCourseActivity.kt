package com.dicoding.courseschedule.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.util.TimePickerFragment

class AddCourseActivity : AppCompatActivity(), TimePickerFragment.DialogTimeListener {

    private lateinit var addCourseViewModel: AddCourseViewModel
    private lateinit var timeStart: String
    private lateinit var timeEnd: String

    private val edCourseName = findViewById<TextView>(R.id.ed_coursename)
    private val spinnerCourse = findViewById<Spinner>(R.id.spinner_course)
    private val edLecturer = findViewById<TextView>(R.id.ed_lecturer)
    private val edNote = findViewById<TextView>(R.id.ed_note)

    companion object{
        const val STARTTIME = "starttime"
        const val ENDTIME = "endtime"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)
        supportActionBar?.title = getString(R.string.add_course)

        val factory = AddCourseViewModelFactory.createFactory(this)
        addCourseViewModel = ViewModelProvider(this, factory)[AddCourseViewModel::class.java]
        showTime()
        addCourseViewModel.saved
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_insert){
            val courseName = edCourseName.text.toString()
            val dayCourse = spinnerCourse.selectedItemPosition
            val lecturer = edLecturer.text.toString()
            val note = edNote.text.toString()

            addCourseViewModel.insertCourse(courseName, dayCourse, timeStart, timeEnd, lecturer, note)

            return true

        } else{
            super.onOptionsItemSelected(item)
        }
    }

    override fun onDialogTimeSet(tag: String?, hour: Int, minute: Int) {
        val time = "${hour}:${minute}"
        val startTime = findViewById<TextView>(R.id.starttime)
        val endTime = findViewById<TextView>(R.id.endtime)

        if (tag == STARTTIME){
            startTime.text = time
            timeStart = time
        } else{
            endTime.text = time
            timeEnd = time
        }
    }

    private fun showTime(){
        val timePickerFragment = TimePickerFragment()
        val ibStartTime = findViewById<ImageButton>(R.id.ib_starttime)
        val ibEndTime = findViewById<ImageButton>(R.id.ib_endtime)

        ibStartTime.setOnClickListener {
            timePickerFragment.show(supportFragmentManager, STARTTIME)
        }

        ibEndTime.setOnClickListener {
            timePickerFragment.show(supportFragmentManager, ENDTIME)
        }
    }

}