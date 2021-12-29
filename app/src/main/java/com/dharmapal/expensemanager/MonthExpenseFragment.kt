package com.dharmapal.expensemanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.dharmapal.expensemanager.databinding.FragmentFirstBinding
import com.dharmapal.expensemanager.databinding.FragmentMonthExpenseBinding

class MonthExpenseFragment : Fragment() {

    private var _binding: FragmentMonthExpenseBinding? = null
    private val binding get() = _binding!!
    val navargs by navArgs<MonthExpenseFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentMonthExpenseBinding.inflate(inflater,container,false)
        binding.Monthname.text=navargs.month.Month
        return binding.root
    }
}