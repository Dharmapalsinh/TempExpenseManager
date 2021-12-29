package com.dharmapal.expensemanager

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class DatePickerFragment : DialogFragment(),DatePickerDialog.OnDateSetListener{
    val viewmodel:TimestampViewmodel by activityViewModels()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c[Calendar.YEAR]
        val month = c[Calendar.MONTH]
        val day = c[Calendar.DAY_OF_MONTH]

        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(requireActivity(),this, year, month, day)
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        val tempdate=LocalDate.of(p1, p2, p3).toFirebaseTimestamp()
        Log.d("tagged","${p1},$p2,$p3")
        Log.d("tagged","$tempdate")
        viewmodel.date.value=tempdate
    }

    fun LocalDate.toFirebaseTimestamp() = Timestamp(Date.from(atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))


}

class TimestampViewmodel:ViewModel(){
    val date=MutableLiveData<Timestamp>()

}