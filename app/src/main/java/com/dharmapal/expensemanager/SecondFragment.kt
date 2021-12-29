package com.dharmapal.expensemanager

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.dharmapal.expensemanager.databinding.FragmentSecondBinding
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.time.ZoneId

class SecondFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentSecondBinding? = null
    var categories = arrayOf<String?>("Recharge", "Food",
        "Shopping", "Other"
        )
    val viewmodel:TimestampViewmodel by activityViewModels()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val viewModel:SecondViewModel by viewModels()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        viewmodel.date.observe(viewLifecycleOwner){
            binding.txtDatetime.text= it.toLocalDate().toString()
        }
        binding.btnAdd.setOnClickListener {
            val name=binding.txtName.text.toString()
            val category=binding.categorySpinner.selectedItem.toString()
            val amount=Integer.parseInt(binding.txtAmount.text.toString())
            val date=viewmodel.date.value
            viewModel.setdata(name,category,amount,date)
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        binding.txtDatetime.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_datePickerFragment)
        }
        val spin=binding.categorySpinner
        spin.onItemSelectedListener = this
        // Create the instance of ArrayAdapter
        // having the list of courses
        val ad=ArrayAdapter(requireActivity(),R.layout.support_simple_spinner_dropdown_item,categories)
        // set simple layout resource file
        // for each item of spinner

        ad.setDropDownViewResource(
            R.layout.support_simple_spinner_dropdown_item
        )
        spin.adapter=ad

        return binding.root

    }

    fun Timestamp.toLocalDate() : LocalDate = toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

    override fun onStart() {

        super.onStart()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.buttonSecond.setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        Toast.makeText(requireActivity(),"${binding.categorySpinner.selectedItem}",Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}

class SecondViewModel(application: Application):AndroidViewModel(application){
    val db=Firebase.firestore
    fun setdata(_name:String,_category:String,_expense:Int,_Date:Timestamp?){

        val data= hashMapOf(
            "ExpenseName" to _name,
            "category" to _category,
            "expense" to _expense,
            "DateTime" to _Date
        )

        db.collection("Data")
            .add(data)
            .addOnSuccessListener {
                Toast.makeText(getApplication(),"data added!!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(getApplication(),"Data is not added!!", Toast.LENGTH_SHORT).show()
            }
    }
}