package com.dharmapal.expensemanager

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.dharmapal.expensemanager.databinding.DialogMonthYearPickerBinding
import java.util.*
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
class MonthYearPickerDialog(val date: Date = Date()) : AppCompatDialogFragment(), Parcelable {

    companion object {
        private const val MAX_YEAR = 2099
    }
    lateinit var  Month:String
    val viewmodel:DateViewmodel by activityViewModels()
    private lateinit var binding: DialogMonthYearPickerBinding

    private var listener: DatePickerDialog.OnDateSetListener? = null

    fun setListener(listener: DatePickerDialog.OnDateSetListener?) {
        this.listener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogMonthYearPickerBinding.inflate(requireActivity().layoutInflater)
        val cal: Calendar = Calendar.getInstance().apply { time = date }

        binding.pickerMonth.run {
            minValue = 0
            maxValue = 11
            value = cal.get(Calendar.MONTH)
            displayedValues = arrayOf("Jan","Feb","Mar","Apr","May","June","July",
                "Aug","Sep","Oct","Nov","Dec")
        }

        binding.pickerYear.run {
            val year = cal.get(Calendar.YEAR)
            minValue = year
            maxValue = year
            value = year
        }

        return AlertDialog.Builder(requireContext())
            .setTitle("Please Select View Month")
            .setView(binding.root)
            .setPositiveButton("Ok") { _, _ ->
                listener?.onDateSet(null, binding.pickerYear.value, binding.pickerMonth.value, 1)
                findNavController().navigate(R.id.action_monthYearPickerDialog3_to_monthExpenseFragment)
                Month=binding.pickerMonth.displayedValues[binding.pickerMonth.value]
                Log.d("tagged","${Month}")
                viewmodel.temp.value=Month
            }
            .setNegativeButton("Cancel") { _, _ -> dialog?.cancel() }
            .create()
    }
}

class DateViewmodel:ViewModel(){
    val temp=MutableLiveData<String>()
}