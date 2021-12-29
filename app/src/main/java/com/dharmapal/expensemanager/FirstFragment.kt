package com.dharmapal.expensemanager

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dharmapal.expensemanager.databinding.DialogMonthYearPickerBinding
import com.dharmapal.expensemanager.databinding.FragmentFirstBinding
import java.util.*
import kotlin.collections.ArrayList

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    val navargs by navArgs<FirstFragmentArgs>()
    val adapter=RecentExpenseAdapter()
    val dateviewmodel:DateViewmodel by activityViewModels()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val viewModel:DataViewModel by viewModels()
    private val categoryAdapter=categoryAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        registerForContextMenu(binding.RecentRecycle)
//        val date:Date=Date()
//        val temp:MonthYearPickerDialog = MonthYearPickerDialog(date)
        binding.month.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_monthYearPickerDialog3)
        }
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            binding.fab.visibility=View.GONE
        }
//        val monthPIckerDailog:DatePickerDialog = DatePickerDialog(requireActivity(),
//        AlertDialog.THEME_HOLO_LIGHT,DatePickerDialog.OnDateSetListener(){
//
//            }
//            )
        dateviewmodel.temp.observe(viewLifecycleOwner){
            binding.date.text= it.toString()
            Log.d("tagged","$it")
        }
        binding.RecentRecycle.adapter=adapter
        binding.recycleCategory.adapter=categoryAdapter
        val a1:Category=Category("xyz","Recharge",5000)
        val a2:Category=Category("abc","Food",500)
        val a3:Category=Category("xyz","Other",400)
        val a4:Category=Category("xyz","Shopping",1000)

        val templist:ArrayList<Category> = ArrayList<Category>()
        templist.add(a1)
        templist.add(a2)
        templist.add(a3)
        templist.add(a4)
        categoryAdapter.submitList(templist)

        return binding.root

    }

    override fun onStart() {

        viewModel.data.observe(viewLifecycleOwner){
            if(it==null)return@observe
            val new=it.sortedByDescending {Data->
                Data.DateTime
            }
            adapter.submitList(new)
        }
        super.onStart()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

