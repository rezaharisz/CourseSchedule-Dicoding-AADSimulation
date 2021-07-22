package com.dicoding.courseschedule.ui.add

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.courseschedule.data.DataRepository

class AddCourseViewModelFactory(private val repository: DataRepository?): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        TODO("Not yet implemented")
    }

    companion object{
        fun createFactory(activity: Activity): AddCourseViewModelFactory{
            val context = activity.applicationContext
                ?: throw IllegalStateException("Not yet attached to Application")

            return AddCourseViewModelFactory(DataRepository.getInstance(context))
        }
    }

}