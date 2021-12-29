package com.dharmapal.expensemanager

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate

class DataViewModel(application: Application):AndroidViewModel(application) {

    val data=MutableLiveData<List<Data>>(emptyList())
    val db= Firebase.firestore
    init {
        fetchdata()
    }

    fun fetchdata(){
        db.collection("Data")
            .get()
            .addOnSuccessListener {
                val datalist=it.documents.map {
                    val e_name: String = it.get("ExpenseName") as String
                    val e_category: String = it.get("category") as String
                    val expense: Int = (it.get("expense") as Long).toInt()
                    val dateTime = it.get("DateTime") as Timestamp

                    val Data1 = Data(e_name, e_category, expense, dateTime)
                    return@map Data1
                }
                data.value=datalist
            }
            .addOnFailureListener { exception ->
                Toast.makeText(getApplication(),"Data Fetching Failed", Toast.LENGTH_SHORT).show()
                Log.d("tagged","$exception")
            }
    }


}